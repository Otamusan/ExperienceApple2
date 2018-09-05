package Item;

import java.util.List;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemFragmentOfTheBrain extends Item {
	private int exp;

	public ItemFragmentOfTheBrain(int exp) {
		this.exp = exp;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(I18n.format("item.fragmentOfTheBrain.amountofExperience") + " : " + this.exp);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand) {
		playerIn.addExperience(exp);
		ItemStack stack = playerIn.getHeldItem(hand);
		stack.shrink(1);

		return new ActionResult<ItemStack>(EnumActionResult.PASS, stack);
	}
}
