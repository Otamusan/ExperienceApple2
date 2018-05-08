package Blocks;

import java.util.ArrayList;
import java.util.List;

import ExperienceApple.ITooltip;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockMod extends Block implements ITooltip {
	public BlockMod(Material mate) {
		super(mate);
	}

	public List<String> Tooltip = new ArrayList<String>();

	@Override
	public List<String> getTooltip() {
		return Tooltip;
	}

	@Override
	public void addTooltip(String str) {
		Tooltip.add(str);
	}
}
