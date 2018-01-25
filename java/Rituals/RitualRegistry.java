package Rituals;

import java.util.ArrayList;

import Rituals.Rituals.Ritual;

public class RitualRegistry {
	public static ArrayList<Ritual> list = new ArrayList<Ritual>();

	public static void register(Ritual ritual) {
		list.add(ritual);
	}
}
