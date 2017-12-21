package ExperienceApple.Register;

import java.util.ArrayList;
import java.util.List;

import GlassRituals.GlassRitualRegister;
import Util.BlockAndPos;

public class RitualRegister {
	public static void init() {
		List<BlockAndPos> list = new ArrayList<BlockAndPos>();
		list.add(new BlockAndPos(-1, 0, 0, BlockRegister.ritualGlassTier1.getDefaultState()));
		list.add(new BlockAndPos(0, 0, -1, BlockRegister.ritualGlassTier1.getDefaultState()));
		list.add(new BlockAndPos(0, 0, 0, BlockRegister.ritualGlassTier1.getDefaultState()));
		list.add(new BlockAndPos(0, 0, 1, BlockRegister.ritualGlassTier1.getDefaultState()));
		list.add(new BlockAndPos(1, 0, 0, BlockRegister.ritualGlassTier1.getDefaultState()));
		.register("Explosion", list);

		List<BlockAndPos> list1 = new ArrayList<BlockAndPos>();
		list1.add(new BlockAndPos(-1, -1, 0, BlockRegister.ritualGlassTier1.getDefaultState()));
		list1.add(new BlockAndPos(0, -1, -1, BlockRegister.ritualGlassTier1.getDefaultState()));
		list1.add(new BlockAndPos(0, -1, 1, BlockRegister.ritualGlassTier1.getDefaultState()));
		list1.add(new BlockAndPos(1, -1, 0, BlockRegister.ritualGlassTier1.getDefaultState()));
		list1.add(new BlockAndPos(0, 0, 0, BlockRegister.ritualGlassTier1.getDefaultState()));
		GlassRitualRegister.register("BlockRemove", list1);

		List<BlockAndPos> list2 = new ArrayList<BlockAndPos>();
		list2.add(new BlockAndPos(-1, 0, -1, BlockRegister.ritualGlassTier1.getDefaultState()));
		list2.add(new BlockAndPos(-1, 0, 1, BlockRegister.ritualGlassTier1.getDefaultState()));
		list2.add(new BlockAndPos(0, 0, 0, BlockRegister.ritualGlassTier1.getDefaultState()));
		list2.add(new BlockAndPos(1, 0, -1, BlockRegister.ritualGlassTier1.getDefaultState()));
		list2.add(new BlockAndPos(1, 0, 1, BlockRegister.ritualGlassTier1.getDefaultState()));
		GlassRitualRegister.register("SetWater", list2);

		List<BlockAndPos> list3 = new ArrayList<BlockAndPos>();
		list2.add(new BlockAndPos(-1, 0, -1, BlockRegister.ritualGlassTier2.getDefaultState()));
		list2.add(new BlockAndPos(-1, 0, 1, BlockRegister.ritualGlassTier2.getDefaultState()));
		list2.add(new BlockAndPos(0, 0, 0, BlockRegister.ritualGlassTier2.getDefaultState()));
		list2.add(new BlockAndPos(1, 0, -1, BlockRegister.ritualGlassTier2.getDefaultState()));
		list2.add(new BlockAndPos(1, 0, 1, BlockRegister.ritualGlassTier2.getDefaultState()));
		GlassRitualRegister.register("SetLava", list3);

	}
}
