package Blocks;

import java.util.Random;

import Client.Particle.EAParticleFunc;
import Client.Particle.EAParticleFuncs;
import Client.Particle.PF;
import Client.Particle.PS;
import Util.Vec.Vec;
import net.minecraft.block.BlockGlass;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockCabinetStone extends BlockGlass {
	public BlockCabinetStone(Material materialIn) {
		super(materialIn, true);
	}

	@Override
	public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rnd) {

		EAParticleFuncs funcs = new EAParticleFuncs(
				new EAParticleFuncs(new EAParticleFunc(40, 0, 0, 0, PF.setColor(0.5f, 1, 1, 0.5f),
						PF.setMotion(0, -0.01, 0), PF.setBlink(1)), 1, PS.randomSquare(1, 1, 1)),
				new EAParticleFuncs(new EAParticleFunc(40, 0, 0, 0, PF.setColor(1, 1, 0.5f, 0.5f),
						PF.setMotion(0, -0.01, 0), PF.setBlink(1)), 1, PS.randomSquare(1, 1, 1)));

		funcs.spawn(world, new Vec(pos.getX(), pos.getY(), pos.getZ()));

	}

	@Override
	public int quantityDropped(Random random) {
		return 1;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return new AxisAlignedBB(0.125, 0.125, 0.125, 0.875, 0.875, 0.875);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}

}
