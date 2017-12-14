package Item.Tools;

import com.google.common.collect.Sets;

import ExperienceApple.Register.ItemRegister;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class ItemFeatherSword extends ItemTool {

	public ItemFeatherSword() {
		super(2, 114510, ItemRegister.FToolMaterial, Sets.newHashSet(new Block[] { Blocks.CAKE }));
		this.setMaxDamage(0);
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
		{

			for (EntityLivingBase entitylivingbase : player.worldObj.getEntitiesWithinAABB(EntityLivingBase.class,
					entity.getEntityBoundingBox().expand(1.0D, 0.25D, 1.0D))) {
				if (entitylivingbase != player && entitylivingbase != entity
						&& !player.isOnSameTeam(entitylivingbase)
						&& player.getDistanceSqToEntity(entitylivingbase) < 3000.0D) {

					entitylivingbase.knockBack(player, 0.4F, MathHelper.sin(player.rotationYaw * 0.017453292F),
							(-MathHelper.cos(player.rotationYaw * 0.017453292F)));
					entitylivingbase.attackEntityFrom(DamageSource.causePlayerDamage(player), 8);
					entity.hurtResistantTime = 0;
				}
			}

			player.worldObj.playSound((EntityPlayer) null, player.posX, player.posY, player.posZ,
					SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, player.getSoundCategory(), 1.0F, 1.0F);
			player.spawnSweepParticles();
		}
		return false;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer player,
			EnumHand hand) {

		for (EntityLivingBase entitylivingbase : player.worldObj.getEntitiesWithinAABB(EntityLivingBase.class,
				player.getEntityBoundingBox().expand(1.0D, 0.25D, 1.0D))) {
			if (entitylivingbase != player
					&& !player.isOnSameTeam(entitylivingbase)
					&& player.getDistanceSqToEntity(entitylivingbase) < 3000.0D) {

				entitylivingbase.knockBack(player, 0.4F, MathHelper.sin(player.rotationYaw * 0.017453292F),
						(-MathHelper.cos(player.rotationYaw * 0.017453292F)));
				entitylivingbase.attackEntityFrom(DamageSource.causePlayerDamage(player), 8);
				entitylivingbase.hurtResistantTime = 0;
			}
		}

		player.worldObj.playSound((EntityPlayer) null, player.posX, player.posY, player.posZ,
				SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, player.getSoundCategory(), 1.0F, 1.0F);
		player.spawnSweepParticles();
		return new ActionResult<ItemStack>(EnumActionResult.PASS, player.getHeldItem(hand));

	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		target.hurtResistantTime = 0;
		return false;
	}

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entity, int itemSlot, boolean isSelected) {
		if (isSelected) {
			((EntityLivingBase) entity).addPotionEffect(
					new PotionEffect(Potion.getPotionFromResourceLocation("speed"), 1, 2, false, false));
		}
	}
}
