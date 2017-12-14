package Client;

import ExperienceApple.Register.ItemRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class TooltipHandler {

	@SubscribeEvent
	public void onItemTooltip(ItemTooltipEvent event) {
		ItemStack itemstack = event.getItemStack();
		Item item = event.getItemStack().getItem();
		if (item == ItemRegister.registeredExperienceApple) {
			NBTTagCompound nbt = itemstack.getTagCompound();
			if (nbt != null) {
				if (nbt.getString("registedPlayer") == "") {
					event.getToolTip().add("Registered players : " + "None");
				} else {
					event.getToolTip().add("Registered players : " + nbt.getString("registedPlayer"));
				}
			}
		}
	}
}