package Util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class PlayerUtil {
	public static boolean hasPlayerItem(EntityPlayer player, Item item) {

		for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
			if (player.inventory.getStackInSlot(i) == null)
				return false;
			if (player.inventory.getStackInSlot(i).getItem() == item) {
				return true;
			}
		}
		return false;
	}

	public static ItemStack getHasPlayerItem(EntityPlayer player, Item item) {

		for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
			if (player.inventory.getStackInSlot(i) == null)
				return null;
			if (player.inventory.getStackInSlot(i).getItem() == item) {
				return player.inventory.getItemStack();
			}
		}
		return null;
	}
}
