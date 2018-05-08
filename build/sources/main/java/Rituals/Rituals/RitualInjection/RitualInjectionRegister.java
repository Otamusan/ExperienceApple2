package Rituals.Rituals.RitualInjection;

import java.util.HashMap;

import ExperienceApple.ITooltip;
import net.minecraft.client.resources.I18n;

public class RitualInjectionRegister {
	private HashMap<String, RitualInjection> list = new HashMap<String, RitualInjection>();

	public void register(String name, RitualInjection ritualInjection) {
		list.put(name, ritualInjection);
		if (ritualInjection.getCraftedBlock() instanceof ITooltip) {
			((ITooltip) ritualInjection.getCraftedBlock())
					.addTooltip(I18n.format("ea.ritual.injection.block.description") + " : "
							+ I18n.format(ritualInjection.getMaterialBlock().getUnlocalizedName() + ".name"));
		}
	}

	public HashMap<String, RitualInjection> getList() {
		return list;
	}

	public void setList(HashMap<String, RitualInjection> list) {
		this.list = list;
	}
}
