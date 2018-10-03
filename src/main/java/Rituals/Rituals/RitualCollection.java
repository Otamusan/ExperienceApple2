package Rituals.Rituals;

import java.util.ArrayList;
import java.util.Random;

import Client.Particle.EAParticleFunc;
import Client.Particle.EAParticleFuncs;
import Client.Particle.PF;
import Client.Particle.PS;
import Rituals.StonePosData;
import Util.InventoryUtil;
import Util.Vec.Vec;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RitualCollection extends Ritual {

	public RitualCollection(StonePosData posData, String name) {
		super(posData, name);
	}

	@Override
	public void activate(EntityPlayer player, World world, BlockPos pos) {
		TileEntity tileEntity = world.getTileEntity(pos.up());
		IInventory inventory = (IInventory) tileEntity;
		ArrayList<Entity> list = new ArrayList<Entity>();
		list.addAll(world.getLoadedEntityList());
		for (Entity entity : list) {
			if (entity.getDistanceSq(pos.add(.5, .5, .5)) <= 40) {
				if (entity instanceof EntityItem) {
					ItemStack newitemstack = setItemStack(inventory, ((EntityItem) entity).getItem());

					// ParticleUtil.randomSquare(EnumParticleTypes.SMOKE_LARGE,
					// world, entity.posX, entity.posY,
					// entity.posZ, entity.posX, entity.posY, entity.posZ, 10);

					Random rnd = new Random();

					EAParticleFuncs funcs = new EAParticleFuncs(
							new EAParticleFunc(40, -0.25, -0.25, -0.25, PF.setScattering(1.5, 1),
									PF.setColor(rnd.nextFloat(), rnd.nextFloat(), rnd.nextFloat(), 0),
									PF.setMotion(0, rnd.nextDouble() / 50, 0), PF.setDeceleration(2)),
							5, PS.randomSquare(0.5, 0.5, 0.5));
					funcs.spawn(world, new Vec(entity.posX, entity.posY, entity.posZ));

					world.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_LAVA_POP,
							SoundCategory.BLOCKS, 0.4f, 1, true);
					if (newitemstack == null) {
						entity.setDead();
					} else {
						((EntityItem) entity).setItem(newitemstack);
					}
				} else if (entity instanceof EntityXPOrb) {
					EntityXPOrb xpOrb = (EntityXPOrb) entity;
					player.addExperience(xpOrb.xpValue);
					xpOrb.setDead();
				}
			}
		}
	}

	@Override
	public boolean canActivate(EntityPlayer player, World world, BlockPos pos) {
		TileEntity tileEntity = world.getTileEntity(pos.up());
		if (tileEntity == null)
			return false;
		if (!(tileEntity instanceof IInventory))
			return false;
		return true;
	}

	public static ItemStack setItemStack(IInventory inventory, ItemStack itemStack) {

		return InventoryUtil.putStackInInventoryAllSlots(inventory, inventory, itemStack, EnumFacing.DOWN);
	}

	public static boolean isItemStackEqual(ItemStack stackA, ItemStack stackB) {
		if (stackA.getItem() != stackB.getItem())
			return false;
		if (stackA.getItemDamage() != stackB.getItemDamage())
			return false;
		if (!ItemStack.areItemStackTagsEqual(stackA, stackB))
			return false;
		return true;
	}
}
