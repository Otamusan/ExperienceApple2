package Rituals.Rituals.RitualCraft;

import java.util.HashMap;

import ExperienceApple.ITooltip;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class RitualCraftRegister {
	private HashMap<String, RitualCraft> list = new HashMap<String, RitualCraft>();

	public void register(String name, RitualCraft ritualCraft) {
		list.put(name, ritualCraft);
		if (ritualCraft.item.getItem() instanceof ITooltip) {
			((ITooltip) ritualCraft.item.getItem()).addTooltip(I18n.format("ea.ritual.ritualCraft.item.description"));
			String string = "";
			for (ItemStack itemStack : ritualCraft.itemlist) {
				string = string + I18n.format(itemStack.getUnlocalizedName() + ".name") + "x" + itemStack.getCount()
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
					string = string + I18n.format(itemStack.getUnlocalizedName() + ".name") + "x" + itemStack.getCount()
							+ ", ";
				}
				((ITooltip) ((ItemBlock) ritualCraft.item.getItem()).getBlock()).addTooltip(string);
			}
		}

	}

	public HashMap<String, RitualCraft> getList() {
		return list;
	}

	public void setList(HashMap<String, RitualCraft> list) {
		this.list = list;
	}
}
