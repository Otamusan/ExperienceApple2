package Blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Client.Particle.EAParticleFunc;
import Client.Particle.EAParticleFuncs;
import Client.Particle.PF;
import Client.Particle.PS;
import TileEntity.TileGrowthStone;
import Util.Vec.Vec;
import net.minecraft.block.BlockGlass;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockGrowthStone extends BlockGlass implements ITileEntityProvider {

	private int acceleration;

	public BlockGrowthStone(Material mate, int acceleration) {
		super(mate, true);
		this.setAcceleration(acceleration);
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

	public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rnd) {
		// EAParticleFuncs funcs = new EAParticleFuncs(
		// new EAParticleFunc(40, 0, 0, 0, PF.setBlink(2), PF.setColor(0, 0, 1,
		// 0.7f), PF.setGravity(0.002)), 10,
		// PS.randomSpreadBall(0.05));
		// funcs.spawn(world, new Vec(pos.getX() + 0.5, pos.getY() + 0.5,
		// pos.getZ() + 0.5));
		EAParticleFuncs funcs = new EAParticleFuncs(
				new EAParticleFunc(40, 0, 0, 0, PF.setBlink(2), PF.setColor(0, 0, 1, 0.7f), PF.setGravity(0.002)), 10,
				PS.randomBall(0.3), PS.spread(0.2, Vec.getZero()));
		funcs.spawn(world, new Vec(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5));
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileGrowthStone();
	}

	public List<String> Tooltip = new ArrayList<String>();

	public int getAcceleration() {
		return acceleration;
	}

	public void setAcceleration(int acceleration) {
		this.acceleration = acceleration;
	}
}
