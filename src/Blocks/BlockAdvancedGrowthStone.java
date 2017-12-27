package Blocks;

import java.util.Random;

import TileEntity.TileAdvancedGrowthStone;
import net.minecraft.block.BlockGlass;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockAdvancedGrowthStone extends BlockGlass implements ITileEntityProvider {

	public BlockAdvancedGrowthStone(Material mate) {
		super(mate, true);
		isBlockContainer = true;
	}

	public boolean hasTileEntity(int metadata) {
		return true;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return new AxisAlignedBB(0.125, 0.125, 0.125, 0.875, 0.875, 0.875);
	}

	@Override
	public int quantityDropped(Random p_149745_1_) {
		return 1;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileAdvancedGrowthStone();
	}

}
