package Rituals.Rituals.RitualInjection;

import net.minecraft.block.Block;

public class RitualInjection {

	private Block materialBlock;
	private Block craftedBlock;

	public RitualInjection(Block materialBlock, Block craftedBlock) {
		this.materialBlock = materialBlock;
		this.craftedBlock = craftedBlock;
	}

	public Block getMaterialBlock() {
		return materialBlock;
	}

	public void setMaterialBlock(Block materialBlock) {
		this.materialBlock = materialBlock;
	}

	public Block getCraftedBlock() {
		return craftedBlock;
	}

	public void setCraftedBlock(Block craftedBlock) {
		this.craftedBlock = craftedBlock;
	}

}
