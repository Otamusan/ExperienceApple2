package Client;

import ExperienceApple.Register.ItemRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;

public class EACraftingEvent {

	@SubscribeEvent
	public void onItemCrafted(ItemCraftedEvent event) {
		IInventory inv = event.craftMatrix;
		ItemStack stack = event.crafting;
		EntityPlayer player = event.player;
		if (hasREA(inv)) {
			player.world.playSound(player.posX, player.posY, player.posZ, SoundEvents.ENTITY_PLAYER_LEVELUP,
					SoundCategory.PLAYERS, 1, 20, true);
		}
	}

	private boolean hasREA(IInventory inv) {
		for (int i = 0; i < inv.getSizeInventory(); i++) {
			if (inv.getStackInSlot(i).getItem() == ItemRegister.registeredExperienceApple) {
				return true;
			}
		}
		return false;
	}
}