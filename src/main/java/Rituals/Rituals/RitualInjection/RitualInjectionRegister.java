package Rituals.Rituals.RitualInjection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

public class RitualInjectionRegister {
	private HashMap<String, RitualInjection> list = new HashMap<String, RitualInjection>();

	public void register(String name, RitualInjection ritualInjection) {
		list.put(name, ritualInjection);
	}

	public List<RitualInjection> getList() {
		List<RitualInjection> list = new ArrayList<>();
		this.list.values().forEach(new Consumer<RitualInjection>() {
			@Override
			public void accept(RitualInjection t) {
				list.add(t);
			}
		});

		return list;
	}

	public HashMap<String, RitualInjection> getMap() {
		return list;
	}

	public void setList(HashMap<String, RitualInjection> list) {
		this.list = list;
	}
}
