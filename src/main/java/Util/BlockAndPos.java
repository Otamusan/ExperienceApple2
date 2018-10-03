package Util;

import net.minecraft.block.state.IBlockState;

public class BlockAndPos {
	public int x;
	public int y;
	public int z;
	public IBlockState blockstate;

	public BlockAndPos(int X, int Y, int Z, IBlockState BlockState) {
		x = X;
		y = Y;
		z = Z;
		blockstate = BlockState;
	}

}
