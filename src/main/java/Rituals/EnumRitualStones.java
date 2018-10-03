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

	public int getTier() {

		switch (this) {
		case tier1:
			return 1;
		case tier2:
			return 2;
		case tier3:
			return 3;
		case tier4:
			return 4;
		}
		return 0;
	}
}
