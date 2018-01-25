package Rituals.Rituals.RitualCraft;

import java.util.HashMap;

import ExperienceApple.ITooltip;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class RitualCraftRegister {
	private static HashMap<String, RitualCraft> list = new HashMap<String, RitualCraft>();

	public static void register(String name, RitualCraft ritualCraft) {
		list.put(name, ritualCraft);
		System.out.println(ritualCraft.item);
		if (ritualCraft.item.getItem() instanceof ITooltip) {
			((ITooltip) ritualCraft.item.getItem()).addTooltip(I18n.format("ea.ritual.ritualCraft.item.description"));
			String string = "";
			for (ItemStack itemStack : ritualCraft.itemlist) {
				string = string + I18n.format(itemStack.getUnlocalizedName() + ".name") + "x" + itemStack.stackSize
						+ ", ";
			}
			((ITooltip) ritualCraft.item.getItem()).addTooltip(string);
		}
		if (ritualCraft.item.getItem() instanceof ItemBlock) {
			if (((ItemBlock) ritualCraft.item.getItem()).getBlock() instanceof ITooltip) {
				((ITooltip) ((ItemBlock) ritualCraft.item.getItem()).getBlock())
						.addTooltip(I18n.format("ea.ritual.ritualCraft.item.description"));
				String string = "";
				for (ItemStack itemStack : ritualCraft.itemlist) {
					string = string + I18n.format(itemStack.getUnlocalizedName() + ".name") + "x" + itemStack.stackSize
							+ ", ";
				}
				((ITooltip) ((ItemBlock) ritualCraft.item.getItem()).getBlock()).addTooltip(string);
			}
		}

	}

	public static HashMap<String, RitualCraft> getList() {
		return list;
	}

	public static void setList(HashMap<String, RitualCraft> list) {
		RitualCraftRegister.list = list;
	}
}
