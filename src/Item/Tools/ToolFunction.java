package Item.Tools;

import java.util.HashSet;
import java.util.Set;

import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public class ToolFunction {
	private boolean isPickAxe;
	private boolean isAxe;
	private boolean isShovel;
	private boolean isSword;

	public ToolFunction(boolean isPickaxe, boolean isAxe, boolean isShovel, boolean isSword) {
		this.isPickAxe = isPickaxe;
		this.isAxe = isAxe;
		this.isShovel = isShovel;
		this.isSword = isSword;
	}

	public boolean isPickAxe() {
		return isPickAxe;
	}

	public void setPickAxe(boolean isPickAxe) {
		this.isPickAxe = isPickAxe;
	}

	public boolean isAxe() {
		return isAxe;
	}

	public void setAxe(boolean isAxe) {
		this.isAxe = isAxe;
	}

	public boolean isShovel() {
		return isShovel;
	}

	public void setShovel(boolean isShovel) {
		this.isShovel = isShovel;
	}

	public boolean isSword() {
		return isSword;
	}

	public void setSword(boolean isSword) {
		this.isSword = isSword;
	}

	public Set<Block> getEffectiveBlocks() {
		Set<Block> blocks = new HashSet<Block>();
		if (isAxe) {
			blocks.addAll(Sets.newHashSet(new Block[] { Blocks.PLANKS, Blocks.BOOKSHELF, Blocks.LOG, Blocks.LOG2,
					Blocks.CHEST, Blocks.PUMPKIN, Blocks.LIT_PUMPKIN, Blocks.MELON_BLOCK, Blocks.LADDER,
					Blocks.WOODEN_BUTTON, Blocks.WOODEN_PRESSURE_PLATE }));
		}
		if (isPickAxe) {
			blocks.addAll(Sets.newHashSet(new Block[] { Blocks.ACTIVATOR_RAIL, Blocks.COAL_ORE, Blocks.COBBLESTONE,
					Blocks.DETECTOR_RAIL, Blocks.DIAMOND_BLOCK, Blocks.DIAMOND_ORE, Blocks.DOUBLE_STONE_SLAB,
					Blocks.GOLDEN_RAIL, Blocks.GOLD_BLOCK, Blocks.GOLD_ORE, Blocks.ICE, Blocks.IRON_BLOCK,
					Blocks.IRON_ORE, Blocks.LAPIS_BLOCK, Blocks.LAPIS_ORE, Blocks.LIT_REDSTONE_ORE,
					Blocks.MOSSY_COBBLESTONE, Blocks.NETHERRACK, Blocks.PACKED_ICE, Blocks.RAIL, Blocks.REDSTONE_ORE,
					Blocks.SANDSTONE, Blocks.RED_SANDSTONE, Blocks.STONE, Blocks.STONE_SLAB, Blocks.STONE_BUTTON,
					Blocks.STONE_PRESSURE_PLATE }));
		}
		if (isShovel) {
			blocks.addAll(Sets.newHashSet(new Block[] { Blocks.CLAY, Blocks.DIRT, Blocks.FARMLAND, Blocks.GRASS,
					Blocks.GRAVEL, Blocks.MYCELIUM, Blocks.SAND, Blocks.SNOW, Blocks.SNOW_LAYER, Blocks.SOUL_SAND,
					Blocks.GRASS_PATH }));
		}
		if (isSword) {
			blocks.add(Blocks.WEB);
		}
		return blocks;
	}
}
