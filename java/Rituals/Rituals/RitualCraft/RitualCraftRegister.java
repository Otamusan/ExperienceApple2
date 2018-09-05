package Rituals.Rituals.RitualCraft;

import java.util.HashMap;

public class RitualCraftRegister {
	private HashMap<String, RitualCraft> list = new HashMap<String, RitualCraft>();

	public void register(String name, RitualCraft ritualCraft) {
		list.put(name, ritualCraft);

	}

	public HashMap<String, RitualCraft> getList() {
		return list;
	}

	public void setList(HashMap<String, RitualCraft> list) {
		this.list = list;
	}
}
