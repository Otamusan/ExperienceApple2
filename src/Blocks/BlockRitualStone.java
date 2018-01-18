package Blocks;

import java.util.ArrayList;
import java.util.List;

import ExperienceApple.ITooltip;
import Rituals.RitualStones;
import net.minecraft.block.material.Material;

public class BlockRitualStone extends BlockRitual implements ITooltip {

	public BlockRitualStone(Material materialIn, int particleAmount, RitualStones tier) {
		super(materialIn, particleAmount, tier);
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
