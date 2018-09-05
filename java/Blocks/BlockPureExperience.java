package Blocks;

import javax.annotation.Nullable;

import net.minecraft.block.BlockSlime;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockPureExperience extends BlockSlime {
	public BlockPureExperience() {
		setSoundType(SoundType.SLIME);
	}

	@Override
	public SoundType getSoundType(IBlockState state, World world, BlockPos pos, @Nullable Entity entity) {
		return SoundType.SLIME;
	}

	@Override
	@Nullable
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return NULL_AABB;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean canCollideCheck(IBlockState state, boolean hitIfLiquid) {
		return true;
	}

	@Override
	public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune) {
	}

	@Override
	public boolean isReplaceable(IBlockAccess worldIn, BlockPos pos) {
		return true;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return true;
	}
}
