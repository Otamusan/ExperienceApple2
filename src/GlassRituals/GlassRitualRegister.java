package GlassRituals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Util.BlockAndPos;

public class GlassRitualRegister {
	public static Map<String, List<BlockAndPos>> glassritual = new HashMap<String, List<BlockAndPos>>();

	public static void register(String string, List<BlockAndPos> list) {
		glassritual.put(string, list);
	}
}
