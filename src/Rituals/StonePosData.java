package Rituals;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.util.math.BlockPos;

public class StonePosData {
	public Map<BlockPos, RitualStones> map = new HashMap<BlockPos, RitualStones>();

	public void setData(int x, int y, int z, RitualStones stone) {
		map.put(new BlockPos(x, y, z), stone);
	}

	public void setData(BlockPos pos, RitualStones stone) {
		map.put(pos, stone);
	}
}
