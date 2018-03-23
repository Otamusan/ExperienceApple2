package Rituals.Rituals;

import java.util.ArrayList;

import Rituals.StonePosData;
import Util.ParticleUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumParticleTypes;
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
					ItemStack newitemstack = setItemStack(inventory, ((EntityItem) entity).getEntityItem());
					ParticleUtil.randomSquare(EnumParticleTypes.SMOKE_LARGE, world, entity.posX, entity.posY,
							entity.posZ, entity.posX, entity.posY, entity.posZ, 10);
					world.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_LAVA_POP,
							SoundCategory.BLOCKS, 0.4f, 1, true);
					if (newitemstack.stackSize <= 0) {
						entity.setDead();
					} else {
						((EntityItem) entity).setEntityItemStack(newitemstack);
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
		for (int i = 0; i < inventory.getSizeInventory(); i++) {
			ItemStack invitem = inventory.getStackInSlot(i);
			if (invitem == null) {
				inventory.setInventorySlotContents(i, itemStack.copy());
				itemStack.stackSize = 0;
				return itemStack;
			} else {
				if (isItemStackEqual(invitem, itemStack)) {
					int maxstack = itemStack.getMaxStackSize();
					if (maxstack - invitem.stackSize != 0) {
						if (itemStack.stackSize > maxstack - invitem.stackSize) {
							itemStack.stackSize -= maxstack - invitem.stackSize;
							invitem.stackSize = maxstack;
						} else {
							invitem.stackSize += itemStack.stackSize;
							itemStack.stackSize = 0;
						}
					}
				}
			}
			if (itemStack.stackSize <= 0)
				return itemStack;
		}
		return itemStack;
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
