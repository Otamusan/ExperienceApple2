package Item;

import java.util.ArrayList;
import java.util.List;

import ExperienceApple.ITooltip;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemFragmentOfTheBrain extends Item implements ITooltip {
	private int exp;

	public ItemFragmentOfTheBrain(int exp) {
		this.exp = exp;
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		if (!GuiScreen.isShiftKeyDown())
			return;
		tooltip.add(I18n.format("item.fragmentOfTheBrain.amountofExperience") + " : " + this.exp);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn,
			EnumHand hand) {
		playerIn.addExperience(exp);
		ItemStack stack = playerIn.getHeldItem(hand);
		stack.stackSize--;

		return new ActionResult<ItemStack>(EnumActionResult.PASS, stack);
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
