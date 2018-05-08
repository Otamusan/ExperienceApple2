package Blocks;

import TileEntity.TileWEIHopper;
import net.minecraft.block.BlockHopper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockWEIHopper extends BlockHopper {
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileWEIHopper();
	}
}
