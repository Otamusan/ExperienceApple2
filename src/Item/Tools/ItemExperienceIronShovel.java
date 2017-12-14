package Item.Tools;

import ExperienceApple.EAMain;
import Util.ExperienceUtil;
import Util.ParticleUtil;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemExperienceIronShovel extends ItemSpade {
	public static int cooldown;

	public ItemExperienceIronShovel(ToolMaterial mate) {
		super(mate);
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, IBlockState state, BlockPos pos,
			EntityLivingBase entityLiving) {
		if (!EAMain.particle) {
			ParticleUtil.verticalCircle(EnumParticleTypes.VILLAGER_HAPPY, entityLiving.getEntityWorld(),
					pos.getX() + 0.5,
					pos.getY() + 0.5,
					pos.getZ() + 0.5, 0.5, 12);
		}

		if (!world.isRemote && state.getBlockHardness(world, pos) != 0.0D) {
			stack.damageItem(1, entityLiving);
		}
		return false;
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
		ParticleUtil.verticalCircle(EnumParticleTypes.VILLAGER_HAPPY, entity.getEntityWorld(), entity.posX,
				entity.posY + entity.getMaxFallHeight() / 2,
				entity.posZ, 1, 12);
		return false;
	}

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entity, int itemSlot, boolean isSelected) {
		if (ExperienceUtil.getExperiencePoints((EntityPlayer) entity) >= 8 && stack.getItemDamage() != 0
				&& cooldown >= 60) {
			for (int n = 0; n < 9; n++) {
				if (!EAMain.particle) {
					entity.getEntityWorld().spawnParticle(EnumParticleTypes.VILLAGER_HAPPY,
							entity.posX + Math.random() - 0.5,
							entity.posY + Math.random() * 2, entity.posZ + Math.random() - 0.5, 0.0D, 0.0D, 0.0D);
				}
			}
			stack.setItemDamage(stack.getItemDamage() - 1);
			EntityPlayer player = (EntityPlayer) entity;
			ExperienceUtil.experiencePull(player, 8, worldIn);
			worldIn.playSound(player, new BlockPos(player), SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP,
					SoundCategory.PLAYERS, 1, 1);
			cooldown = 0;
			player.inventory.setInventorySlotContents(itemSlot, stack);
		} else {
			cooldown++;
		}
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
