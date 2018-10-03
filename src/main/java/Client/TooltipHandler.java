package Client;

import ExperienceApple.Register.BlockRegister;
import ExperienceApple.Register.ItemRegister;
import net.minecraft.block.Block;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class TooltipHandler {

	@SubscribeEvent
	public void onItemTooltip(ItemTooltipEvent event) {
		ItemStack itemStack = event.getItemStack();
		if (itemStack.getItem() instanceof ItemBlock) {
			Block block = ((ItemBlock) itemStack.getItem()).getBlock();
			if (!BlockRegister.descriptionlist.contains(block))
				return;
			if (GuiScreen.isShiftKeyDown()) {
				event.getToolTip().add("§5" + I18n.format(block.getUnlocalizedName() + ".tooltipdescription"));
			} else {
				event.getToolTip().add("<Press Shift>");
			}
		} else {
			Item item = itemStack.getItem();
			if (!ItemRegister.descriptionlist.contains(item))
				return;
			if (GuiScreen.isShiftKeyDown()) {
				event.getToolTip().add("§5" + I18n.format(item.getUnlocalizedName() + ".tooltipdescription"));
			} else {
				event.getToolTip().add("<Press Shift>");
			}
		}

	}
}