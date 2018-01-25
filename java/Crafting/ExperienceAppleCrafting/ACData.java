package Crafting.ExperienceAppleCrafting;

import net.minecraft.item.Item;

public class ACData {
	public Item CRitem;
	public int XPCost;

	public ACData(Item item, int XP) {
		CRitem = item;
		XPCost = XP;
	}

	public Item getCRitem() {
		return CRitem;
	}

	public int getXPCost() {
		return XPCost;
	}
}
