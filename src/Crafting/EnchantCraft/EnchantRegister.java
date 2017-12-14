package Crafting.EnchantCraft;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.Item;

public class EnchantRegister {
	public static List<Item> canEnchant = new ArrayList<Item>();

	public static void register(Item tool) {
		canEnchant.add(tool);
	}
}
