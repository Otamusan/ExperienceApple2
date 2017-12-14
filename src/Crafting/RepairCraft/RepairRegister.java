package Crafting.RepairCraft;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.Item;

public class RepairRegister {
	public static List<Item> canRepiar = new ArrayList<Item>();

	public static void register(Item tool) {
		canRepiar.add(tool);
	}
}
