package Crafting.EnchantCraft;

import java.util.ArrayList;
import java.util.List;

import ExperienceApple.ITooltip;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;

public class EnchantRegister {
	public static List<Item> canEnchant = new ArrayList<Item>();

	public static void register(Item tool) {
		canEnchant.add(tool);
		if (tool instanceof ITooltip) {
			((ITooltip) tool).addTooltip(I18n.format("ea.eccraft.description"));
		}
	}
}
