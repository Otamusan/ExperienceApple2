package Crafting.RepairCraft;

import java.util.HashMap;

import ExperienceApple.ITooltip;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;

public class RepairRegister {
	public static HashMap<Item, Integer> canRepiar = new HashMap<Item, Integer>();

	public static void register(Item tool, int amount) {
		canRepiar.put(tool, amount);
		if (tool instanceof ITooltip) {
			((ITooltip) tool).addTooltip(I18n.format("ea.repaircraft.description"));
		}
	}
}
