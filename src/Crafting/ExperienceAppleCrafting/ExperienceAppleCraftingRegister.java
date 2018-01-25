package Crafting.ExperienceAppleCrafting;

import java.util.HashMap;
import java.util.Map;

import ExperienceApple.ITooltip;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class ExperienceAppleCraftingRegister {
	public static Map<Item, ACData> EACRecipe = new HashMap<Item, ACData>();
	public static Map<Item, Integer> EACRecipeCRtoCost = new HashMap<Item, Integer>();

	public static void register(Item RSitem, Item CRitem, int XPCost) {
		EACRecipe.put(RSitem, new ACData(CRitem, XPCost));
		EACRecipeCRtoCost.put(CRitem, XPCost);
		if (CRitem instanceof ITooltip) {
			((ITooltip) CRitem).addTooltip(I18n.format("ea.eacraft.description"));
			((ITooltip) CRitem).addTooltip(I18n.format("ea.eacraft.expcost") + " : " + XPCost);
			((ITooltip) CRitem).addTooltip(
					I18n.format("ea.eacraft.item") + " : " + I18n.format(RSitem.getUnlocalizedName() + ".name"));
		}
		if (CRitem instanceof ItemBlock) {
			if (((ItemBlock) CRitem).getBlock() instanceof ITooltip) {
				((ITooltip) ((ItemBlock) CRitem).getBlock()).addTooltip(I18n.format("ea.eacraft.description"));
				((ITooltip) ((ItemBlock) CRitem).getBlock())
						.addTooltip(I18n.format("ea.eacraft.expcost") + " : " + XPCost);
				((ITooltip) ((ItemBlock) CRitem).getBlock()).addTooltip(
						I18n.format("ea.eacraft.item") + " : " + I18n.format(RSitem.getUnlocalizedName() + ".name"));
			}
		}
	}
}
