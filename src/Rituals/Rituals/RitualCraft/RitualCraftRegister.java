package Rituals.Rituals.RitualCraft;

import java.util.HashMap;

public class RitualCraftRegister {
	private static HashMap<String, RitualCraft> list = new HashMap<String, RitualCraft>();

	public static void register(String name, RitualCraft ritualCraft) {
		list.put(name, ritualCraft);
	}

	public static HashMap<String, RitualCraft> getList() {
		return list;
	}

	public static void setList(HashMap<String, RitualCraft> list) {
		RitualCraftRegister.list = list;
	}
}
