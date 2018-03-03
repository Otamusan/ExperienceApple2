package Item.SpellPapers;

import java.util.ArrayList;
import java.util.List;

import ExperienceApple.ITooltip;
import Util.ExperienceUtil;
import Util.ParticleUtil;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemFlyingSpellPaper extends Item implements ITooltip {
	public int COST;

	public ItemFlyingSpellPaper(int cost) {
		this.COST = cost;
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		if (!GuiScreen.isShiftKeyDown())
			return;
		tooltip.add(I18n.format("item.flyingSpellPaper.cost") + " : " + COST);
	}

	/*
	 * @Override public void onUpdate(ItemStack stack, World world, Entity
	 * entity, int Slot, boolean isSelected) { entity.fallDistance = 0; if
	 * (world.isRemote && ExperienceUtil.getExperiencePoints((EntityPlayer)
	 * entity) >= COST) { if (entity.isSneaking()) {
	 * 
	 * ExperienceUtil.experiencePull((EntityPlayer) entity, COST, world); if
	 * (entity.motionY < 0.5) { entity.motionY = entity.motionY + 0.2;
	 * entity.motionX = entity.getForward().xCoord; entity.motionZ =
	 * entity.getForward().zCoord; } } } }
	 */

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int Slot, boolean isSelected) {
		if (entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entity;
			if (player.capabilities.isFlying) {
				ExperienceUtil.experiencePull(player, COST, world);
				ParticleUtil.randomCircle(EnumParticleTypes.FIREWORKS_SPARK, world, entity.posX, entity.posY,
						entity.posZ, 1.5, 1);
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
