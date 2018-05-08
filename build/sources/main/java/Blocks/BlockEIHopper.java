package Blocks;

import TileEntity.TileEIHopper;
import net.minecraft.block.BlockHopper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockEIHopper extends BlockHopper {
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEIHopper();
	}
}
