package Rituals.Rituals.RitualCraft;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

public class RitualCraftRegister {
	private HashMap<String, RitualCraft> list = new HashMap<String, RitualCraft>();

	public void register(String name, RitualCraft ritualCraft) {
		list.put(name, ritualCraft);

	}

	public List<RitualCraft> getList() {
		List<RitualCraft> list = new ArrayList<>();
		this.list.values().forEach(new Consumer<RitualCraft>() {
			@Override
			public void accept(RitualCraft t) {
				list.add(t);
			}
		});

		return list;
	}

	public HashMap<String, RitualCraft> getMap() {
		return list;
	}

	public void setList(HashMap<String, RitualCraft> list) {
		this.list = list;
	}
}
