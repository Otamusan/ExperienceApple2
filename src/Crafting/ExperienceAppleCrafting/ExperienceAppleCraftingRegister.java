package Crafting.ExperienceAppleCrafting;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.item.Item;

public class ExperienceAppleCraftingRegister {
	public static Map<Item, ACData> EACRecipe = new HashMap<Item, ACData>();
	public static Map<Item, Integer> EACRecipeCRtoCost = new HashMap<Item, Integer>();

	public static void register(Item RSitem, Item CRitem, int XPCost) {
		EACRecipe.put(RSitem, new ACData(CRitem, XPCost));
		EACRecipeCRtoCost.put(CRitem, XPCost);
	}
}
