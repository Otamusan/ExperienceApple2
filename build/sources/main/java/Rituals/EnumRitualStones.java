package Rituals;

import Blocks.BlockRitual;
import net.minecraft.block.Block;

public enum EnumRitualStones {
	tier1(1), tier2(8), tier3(64), tier4(512);
	public static EnumRitualStones getRitualStones(Block block) {
		if (block instanceof BlockRitual) {
			BlockRitual blockRitual = (BlockRitual) block;
			return blockRitual.getTier();
		}
		return null;
	}

	private final int magnification;

	private EnumRitualStones(final int magnification) {
		this.magnification = magnification;
	}

	public int getMagnification() {
		return this.magnification;
	}
}
