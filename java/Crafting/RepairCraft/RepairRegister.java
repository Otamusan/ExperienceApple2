package Crafting.RepairCraft;

import java.util.ArrayList;
import java.util.List;

import ExperienceApple.ITooltip;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;

public class RepairRegister {
	public static List<Item> canRepiar = new ArrayList<Item>();

	public static void register(Item tool) {
		canRepiar.add(tool);
		if (tool instanceof ITooltip) {
			((ITooltip) tool).addTooltip(I18n.format("ea.repaircraft.description"));
		}
	}
}
