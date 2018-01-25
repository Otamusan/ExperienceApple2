package Client;

import java.util.List;

import ExperienceApple.ITooltip;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class TooltipHandler {

	@SubscribeEvent
	public void onItemTooltip(ItemTooltipEvent event) {
		ItemStack itemStack = event.getItemStack();

		if (itemStack.getItem() instanceof ITooltip) {
			if (!GuiScreen.isShiftKeyDown())
				return;
			Item item = itemStack.getItem();
			List<String> addtooltip = ((ITooltip) item).getTooltip();
			for (String string : addtooltip) {
				event.getToolTip().add(string);
			}
		}
		if (itemStack.getItem() instanceof ItemBlock) {
			if (((ItemBlock) itemStack.getItem()).getBlock() instanceof ITooltip) {
				if (!GuiScreen.isShiftKeyDown())
					return;
				Item item = itemStack.getItem();
				List<String> addtooltip = ((ITooltip) ((ItemBlock) item).getBlock()).getTooltip();
				for (String string : addtooltip) {
					event.getToolTip().add(string);
				}
			}
		}
	}
}