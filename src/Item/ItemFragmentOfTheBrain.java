package Item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemFragmentOfTheBrain extends Item {
	@Override

	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn,
			EnumHand hand) {
		playerIn.addExperience(50000);
		ItemStack stack = playerIn.getHeldItem(hand);
		stack.stackSize--;

		return new ActionResult<ItemStack>(EnumActionResult.PASS, stack);
	}

}
