package Blocks;

import java.util.Random;

import Client.Particle.EAParticleFunc;
import Client.Particle.EAParticleFuncs;
import Client.Particle.PF;
import Client.Particle.PS;
import Rituals.EnumRitualStones;
import Util.Vec.Vec;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockRitual extends Block {
	private int particleAmount;
	private EnumRitualStones tier;

	public BlockRitual(Material materialIn, int particleAmount, EnumRitualStones tier) {
		super(materialIn);
		this.particleAmount = particleAmount;
		this.tier = tier;
	}

	public EnumRitualStones getTier() {
		return tier;
	}

	public void setTier(EnumRitualStones tier) {
		this.tier = tier;
	}

	@Override
	public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rnd) {
		EAParticleFuncs funcs = new EAParticleFuncs(
				new EAParticleFunc(20, 0, 0, 0, PF.setBlink(1), PF.setColor(0, 1, 0, 0.24f * tier.getTier())), 1,
				PS.randomSquare(1, 1, 1));
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
