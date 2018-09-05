package Item.Tools;

import Util.ParticleUtil;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class ItemMagicBow extends ItemBow {

	public static int chargeTime = 0;

	public ItemMagicBow() {
		super();
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
		// if (!entityLiving.isSneaking()) {
		if (entityLiving instanceof EntityPlayer) {
			EntityPlayer entityplayer = (EntityPlayer) entityLiving;
			boolean flag = entityplayer.capabilities.isCreativeMode
					|| EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) > 0;
			ItemStack itemstack = new ItemStack(Items.ARROW);

			int i = this.getMaxItemUseDuration(stack) - timeLeft;
			i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(stack, worldIn, entityplayer, i, true);
			if (i < 0)
				return;

			if (!(itemstack == null) || flag) {

				float f = getArrowVelocity(i);

				if (f >= 0.1D) {
					boolean flag1 = entityplayer.capabilities.isCreativeMode
							|| (itemstack.getItem() instanceof ItemArrow
									&& ((ItemArrow) itemstack.getItem()).isInfinite(itemstack, stack, entityplayer));

					for (int j = 0; j < i / 5 + 1; j++) {
						ParticleUtil.verticalCircle(EnumParticleTypes.ENCHANTMENT_TABLE, worldIn, entityplayer.posX,
								entityplayer.posY + 0.3 * j, entityplayer.posZ, 0.5 * j + 1, 60);
					}

					if (!worldIn.isRemote) {

						for (int j = 0; j < (i ^ 2) / 1 + 3; j++) {
							EntityTippedArrow entityarrow = (EntityTippedArrow) new ItemArrow().createArrow(worldIn,
									itemstack, entityplayer);
							entityarrow.shoot(entityplayer, entityplayer.rotationPitch, entityplayer.rotationYaw, 0.0F,
									4.0F, i / 2 + 2);

							// if (f == 1.0F) {
							entityarrow.setIsCritical(true);
							// }

							int j1 = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);

							if (j1 > 0) {
								entityarrow.setDamage(entityarrow.getDamage() + j1 * 0.5D + 0.5D);
							}

							int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, stack);

							if (k > 0) {
								entityarrow.setKnockbackStrength(k);
							}

							if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, stack) > 0) {
								entityarrow.setFire(100);
							}

							entityarrow.pickupStatus = EntityArrow.PickupStatus.CREATIVE_ONLY;
							entityarrow.arrowShake = 0;

							worldIn.spawnEntity(entityarrow);
						}

					}

					worldIn.playSound((EntityPlayer) null, entityplayer.posX, entityplayer.posY, entityplayer.posZ,
							SoundEvents.ENTITY_SHULKER_SHOOT, SoundCategory.PLAYERS, 1.0F,
							1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
					if (!flag1 && !entityplayer.capabilities.isCreativeMode) {
						itemstack.shrink(1);

						if (itemstack != null) {
							entityplayer.inventory.deleteStack(itemstack);
						}
					}

					entityplayer.addStat(StatList.getObjectUseStats(this));
				}
			}
		}
	}

	// }

}
