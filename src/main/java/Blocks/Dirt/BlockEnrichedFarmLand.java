package Blocks.Dirt;

import java.util.Random;

import Client.Particle.EAParticleFunc;
import Client.Particle.EAParticleFuncs;
import Client.Particle.PF;
import Client.Particle.PS;
import Util.Vec.Vec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockEnrichedFarmLand extends BlockEADirt {
	public BlockEnrichedFarmLand() {
		this.setTickRandomly(true);
	}

	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if (worldIn.getBlockState(pos.up()).getBlock() instanceof BlockCrops) {
			IBlockState blockState = worldIn.getBlockState(pos.up());
			Block block = blockState.getBlock();
			BlockCrops crops = (BlockCrops) block;
			if (new Random().nextInt(100) <= 5) {
				block.updateTick(worldIn, pos.up(), blockState, rand);
			}
			if (crops.isMaxAge(blockState)) {
				block.onBlockDestroyedByPlayer(worldIn, pos.up(), worldIn.getBlockState(pos.up()));
				block.dropXpOnBlockBreak(worldIn, pos.up(),
						block.getExpDrop(worldIn.getBlockState(pos.up()), worldIn, pos.up(), 0));
				worldIn.destroyBlock(pos.up(), true);
				worldIn.setBlockState(pos.up(), crops.getDefaultState());
			}
		}
		worldIn.scheduleBlockUpdate(pos, this, 5, 0);
	}

	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.9375D, 1.0D);
	}

	@Override
	public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction,
			net.minecraftforge.common.IPlantable plantable) {
		return true;
	}

	@Override
	public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rnd) {
		EAParticleFuncs funcs = new EAParticleFuncs(new EAParticleFunc(40, 0, 0, 0, PF.setBlink(2),
				PF.setColor(rnd.nextFloat() / 2, 1, rnd.nextFloat() / 2, 0.2f),
				PF.setMotion(0, rnd.nextDouble() / 20 + 0.05, 0)), 1, PS.randomSquare(1, 0, 1));
		funcs.spawn(world, new Vec(pos.getX(), pos.getY() + 1, pos.getZ()));
	}

	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
}