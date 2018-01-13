package Util;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemList {
	public List<ItemStack> itemlist;

	public ItemList(List<ItemStack> Citemlist) {
		itemlist = Citemlist;
	}

	public void setNonNBTItemList() {
		for (ItemStack itemStack : itemlist) {
			itemStack.serializeNBT();
		}
	}

	public List<Item> getItemList() {
		List<Item> itemlists = new ArrayList<Item>();
		for (ItemStack itemstack : itemlist) {
			itemlists.add(itemstack.getItem());
		}
		return itemlists;
	}

	public ItemStack getItemStack(Item item) {
		for (ItemStack itemStack : itemlist) {
			if (itemStack.getItem() == item) {
				return itemStack;
			}
		}
		return null;
	}

	public static boolean isItemListEqual(List<ItemStack> list1, List<ItemStack> list2) {
		//if (list1.size() == list2.size()) {
		atta: for (ItemStack itemStack1 : list1) {
			for (ItemStack itemStack2 : list2) {
				if (ItemStack.areItemStacksEqual(itemStack1, itemStack2)) {
					continue atta;
				}
			}
			return false;
		}
		return true;
		//}
		//return false;
	}
}
