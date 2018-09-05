package Item.SpellPapers;

import java.util.List;

import Util.ExperienceUtil;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemSpellPapers extends Item {
	private Potion potion;
	protected int cost;
	private int amp;
	public static int count = 0;

	public ItemSpellPapers(Potion potion, int cost, int amp) {
		this.amp = amp;
		this.cost = cost;
		this.potion = potion;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(I18n.format("item.spellPaper.cost") + " : " + cost);
		if (isActive(stack)) {
			tooltip.add(I18n.format("item.spellPaper.state") + " : " + I18n.format("item.spellPaper.enable"));
		} else {
			tooltip.add(I18n.format("item.spellPaper.state") + " : " + I18n.format("item.spellPaper.disable"));
		}
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack stack = playerIn.getHeldItem(handIn);
		if (isActive(stack)) {
			setActive(stack, false);
			if (!worldIn.isRemote) {
				playerIn.sendMessage(new TextComponentTranslation(I18n.format("item.spellPaper.disable")));
			}
		} else {
			setActive(stack, true);
			if (!worldIn.isRemote) {
				playerIn.sendMessage(new TextComponentTranslation(I18n.format("item.spellPaper.enable")));
			}
		}

		return new ActionResult<ItemStack>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
	}

	public static boolean isActive(ItemStack stack) {
		if (stack.getTagCompound() != null) {
			NBTTagCompound nbt = stack.getTagCompound();
			if (nbt.hasKey("isActive")) {
				return nbt.getBoolean("isActive");
			} else {
				return true;
			}
		} else {
			return true;
		}
	}

	public static void setActive(ItemStack stack, boolean isActive) {
		NBTTagCompound nbt = new NBTTagCompound();
		if (stack.getTagCompound() != null) {
			nbt = stack.getTagCompound();
		}

		nbt.setBoolean("isActive", isActive);
		stack.setTagCompound(nbt);
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int Slot, boolean isSelected) {

		count++;
		if (count > 60)
			count = 0;

		if (!isActive(stack))
			return;

		if (entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entity;
			if (ExperienceUtil.getExperiencePoints(player) >= cost) {
				if (count == 0) {
					ExperienceUtil.experiencePull(player, cost, world);
				}
				player.addPotionEffect(new PotionEffect(potion, 1, amp, false, false));
			}
		}
		onUpdate(world, entity);

	}

	public void onUpdate(World world, Entity entity) {
	}
}
