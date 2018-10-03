package Item.Tools;

import java.util.Random;

import Client.Particle.EAParticleFunc;
import Client.Particle.EAParticleFuncs;
import Client.Particle.PF;
import Client.Particle.PS;
import Util.Vec.Vec;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class ItemMagicBow extends ItemBow {

	public static int chargeTime = 0;

	public ItemMagicBow() {
		super();
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		playerIn.setActiveHand(handIn);
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
	}

	@Override
	public void onUsingTick(ItemStack stack, EntityLivingBase player, int count) {
		// for (int j = 0; j < 1; j++) {
		// ParticleUtil.Injection(EnumParticleTypes.ENCHANTMENT_TABLE,
		// player.world, player.posX, player.posY + 1.5,
		// player.posZ, 5, 10);
		// }

		EAParticleFuncs funcs = new EAParticleFuncs(
				new EAParticleFunc(10, 0, 0, 0, PF.setBlink(1 + new Random().nextFloat() * 2),
						PF.setColor(1, 1, 1, 0.5f)),
				10, PS.randomBall(7 * new Random().nextDouble() * 2), PS.convergence(0.1, Vec.getZero()));
		funcs.spawn(player.world, new Vec(player.posX, player.posY + 1, player.posZ));
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
		if (entityLiving instanceof EntityPlayer) {
			EntityPlayer entityplayer = (EntityPlayer) entityLiving;

			ItemStack itemstack = new ItemStack(Items.ARROW);

			int i = this.getMaxItemUseDuration(stack) - timeLeft;

			float f = getArrowVelocity(i);

			if (f >= 0.1D) {

				// for (int j = 0; j < i / 5 + 1; j++) {
				// ParticleUtil.verticalCircle(EnumParticleTypes.ENCHANTMENT_TABLE,
				// worldIn, entityplayer.posX,
				// entityplayer.posY + 0.3 * j, entityplayer.posZ, 0.5 * j + 1,
				// 60);
				// }

				if (!worldIn.isRemote) {
					for (int j = 0; j < (i ^ 2) / 1 + 3; j++) {
						EntityArrow entityarrow = (EntityArrow) new ItemArrow().createArrow(worldIn, itemstack,
								entityplayer);
						entityarrow.shoot(entityplayer, entityplayer.rotationPitch, entityplayer.rotationYaw, 0.0F,
								4.0F, i / 2 + 2);

						entityarrow.setIsCritical(true);

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
						entityarrow.arrowShake = 999;

						worldIn.spawnEntity(entityarrow);
					}

				}

				worldIn.playSound((EntityPlayer) null, entityplayer.posX, entityplayer.posY, entityplayer.posZ,
						SoundEvents.ENTITY_SHULKER_SHOOT, SoundCategory.PLAYERS, 1.0F,
						1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

				// entityplayer.addStat(StatList.getObjectUseStats(this));
			}
		}

	}

}
