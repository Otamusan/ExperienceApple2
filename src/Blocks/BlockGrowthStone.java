package Blocks;

import java.util.Random;

import ExperienceApple.EAMain;
import TileEntity.TileGrowthStone;
import net.minecraft.block.BlockGlass;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockGrowthStone extends BlockGlass implements ITileEntityProvider {

	public BlockGrowthStone(Material mate) {
		super(mate, true);
		isBlockContainer = true;
	}

	public boolean hasTileEntity(int metadata) {
		return true;
	}

	@Override
	public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rnd) {
		if (EAMain.particle == true)
			return;
		for (int i = 0; i < 16; i++) {
			double rx = pos.getX() + rnd.nextFloat();
			double ry = pos.getY() + rnd.nextFloat();
			double rz = pos.getZ() + rnd.nextFloat();
			double sx = rnd.nextFloat() * 3 - 1.5;
			double sy = rnd.nextFloat() * 2 - 1.5;
			double sz = rnd.nextFloat() * 3 - 1.5;
			world.spawnParticle(EnumParticleTypes.VILLAGER_HAPPY, rx, ry, rz, sx, sy, sz);

		}
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
		return new TileGrowthStone();
	}
}
