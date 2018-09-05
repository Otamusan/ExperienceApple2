package Rituals.Rituals.RitualInjection;

import java.util.HashMap;

public class RitualInjectionRegister {
	private HashMap<String, RitualInjection> list = new HashMap<String, RitualInjection>();

	public void register(String name, RitualInjection ritualInjection) {
		list.put(name, ritualInjection);
	}

	public HashMap<String, RitualInjection> getList() {
		return list;
	}

	public void setList(HashMap<String, RitualInjection> list) {
		this.list = list;
	}
}
