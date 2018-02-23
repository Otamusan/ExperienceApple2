package Rituals;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.util.math.BlockPos;

public class StonePosData {
	public Map<BlockPos, EnumRitualStones> map = new HashMap<BlockPos, EnumRitualStones>();

	public void setData(int x, int y, int z, EnumRitualStones stone) {
		map.put(new BlockPos(x, y, z), stone);
	}

	public void setData(BlockPos pos, EnumRitualStones stone) {
		map.put(pos, stone);
	}
}
