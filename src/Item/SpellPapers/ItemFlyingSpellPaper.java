package Item.SpellPapers;

import java.util.ArrayList;
import java.util.List;

import ExperienceApple.EAMain;
import ExperienceApple.ITooltip;
import Util.ExperienceUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class ItemFlyingSpellPaper extends Item implements ITooltip {
	public final int COST = 5;

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int Slot, boolean isSelected) {
		if (entity instanceof EntityPlayer) {
			if (((EntityPlayer) entity).capabilities.isFlying) {
				if (!EAMain.particle) {
					entity.worldObj.spawnParticle(EnumParticleTypes.FIREWORKS_SPARK, entity.posX + Math.random() - 0.5,
							entity.posY, entity.posZ + Math.random() - 0.5, 0.0D, 0.0D, 0.0D);
				}
				if (ExperienceUtil.getExperiencePoints((EntityPlayer) entity) >= COST) {
					ExperienceUtil.experiencePull((EntityPlayer) entity, COST, world);
					((EntityPlayer) entity).capabilities.allowFlying = true;
					((EntityPlayer) entity).sendPlayerAbilities();

				} else {
					((EntityPlayer) entity).capabilities.allowFlying = false;
					((EntityPlayer) entity).capabilities.isFlying = false;
					((EntityPlayer) entity).sendPlayerAbilities();

				}
			}
		}
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn,
			EnumHand hand) {

		return new ActionResult<ItemStack>(EnumActionResult.PASS, itemStackIn);
	}

	public List<String> Tooltip = new ArrayList<String>();

	@Override
	public List<String> getTooltip() {
		return Tooltip;
	}

	@Override
	public void addTooltip(String str) {
		Tooltip.add(str);
	}
}
