package Rituals.Rituals;

import java.util.HashMap;
import java.util.Map;

import Rituals.RitualStones;
import Rituals.StonePosData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Ritual {
	private String name;
	private StonePosData posData;
	private Map<BlockPos, RitualStones> map = new HashMap<BlockPos, RitualStones>();

	public Ritual(StonePosData posData, String name) {
		this.posData = posData;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public StonePosData getPosData() {
		return posData;
	}

	public void setPosData(StonePosData posData) {
		this.posData = posData;
	}

	public void activate(EntityPlayer player, World world, BlockPos pos) {
	}

	public boolean canActivate(EntityPlayer player, World world, BlockPos pos) {
		return false;
	}

	public void setStoneBlock(BlockPos pos, RitualStones stone) {
		map.put(pos, stone);
	}

	public void setStoneBlock(int x, int y, int z, RitualStones stone) {
		map.put(new BlockPos(x, y, z), stone);
	}

	public RitualStones getStoneBlock(BlockPos pos) {
		if (map.containsKey(pos)) {
			return map.get(pos);
		} else {
			return null;
		}
	}

	public void setStoneBlockData(Map<BlockPos, RitualStones> map) {
		this.map = map;
	}

	public Map<BlockPos, RitualStones> getStoneBlockData() {
		return map;
	}
}
