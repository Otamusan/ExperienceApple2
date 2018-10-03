package Item.Tools;

import java.util.List;

import Client.Particle.EAParticleFunc;
import Client.Particle.EAParticleFuncs;
import Client.Particle.PF;
import Client.Particle.PS;
import Item.ExperienceRepair;
import Item.IExperienceRepair;
import Util.Vec.Vec;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemExperienceIronPickaxe extends ItemPickaxe implements IExperienceRepair {

	private ExperienceRepair experienceRepair;
	private int range = 1;

	public ItemExperienceIronPickaxe(ToolMaterial mate, int cooltime, int cost) {
		super(mate);
		this.experienceRepair = new ExperienceRepair(cooltime, cost);
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, IBlockState state, BlockPos pos,
			EntityLivingBase entityLiving) {
		stack.damageItem(1, entityLiving);
		if (!entityLiving.isSneaking())
			return false;
		world.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE,
				SoundCategory.PLAYERS, 1, 0, true);

		if (world.isRemote)
			return false;
		for (

				int ix = -range; ix < range + 1; ix++) {
			for (int iy = -range; iy < range + 1; iy++) {
				for (int iz = -range; iz < range + 1; iz++) {
					BlockPos xpPos = new BlockPos(ix + pos.getX(), iy + pos.getY(), iz + pos.getZ());
					Block block = world.getBlockState(xpPos).getBlock();
					block.onBlockDestroyedByPlayer(world, xpPos, world.getBlockState(xpPos));
					block.dropXpOnBlockBreak(world, pos, block.getExpDrop(world.getBlockState(xpPos), world, xpPos, 0));
					world.destroyBlock(xpPos, true);
					EAParticleFuncs funcs = new EAParticleFuncs(
							new EAParticleFunc(20, 0, 0, 0, PF.setBlink(1), PF.setColor(0, 1, 0, 0.5f)), 3,
							PS.randomSquare(1, 1, 1));
					funcs.spawn(world, new Vec(pos.getX() + ix, pos.getY() + iy, pos.getZ() + iz));
				}
			}
		}
		return false;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add("§3" + I18n.format("item.experienceIron.cooltime") + " : " + this.experienceRepair.getCooltime());
		tooltip.add("§3" + I18n.format("item.experienceIron.cost") + " : " + this.experienceRepair.getCost());

	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
		EAParticleFuncs funcs = new EAParticleFuncs(
				new EAParticleFunc(20, 0, 0, 0, PF.setBlink(1), PF.setColor(0, 1, 0, 0.5f)), 3,
				PS.randomSquare(1, 1, 1));
		funcs.spawn(entity.world, new Vec(entity.posX, entity.posY, entity.posZ));
		return false;
	}

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entity, int itemSlot, boolean isSelected) {
		this.experienceRepair.onUpdate(stack, worldIn, entity, itemSlot, isSelected);
	}

	@Override
	public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
		return false;
	}

	@Override
	public int getItemEnchantability() {
		return 0;
	}

}
