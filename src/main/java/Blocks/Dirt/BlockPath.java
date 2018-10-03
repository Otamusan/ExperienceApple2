package Blocks.Dirt;

import java.util.Random;

import Client.Particle.EAParticleFunc;
import Client.Particle.EAParticleFuncs;
import Client.Particle.PF;
import Client.Particle.PS;
import Util.Vec.Vec;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockPath extends BlockEADirt {

	public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
		((EntityLivingBase) entityIn)
				.addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation("speed"), 20, 2, true, false));
	}

	@Override
	public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rnd) {
		EAParticleFuncs funcs = new EAParticleFuncs(
				new EAParticleFunc(20, 0, 0, 0, PF.setBlink(0.5f), PF.setColor(1, 1, 1, 0.2f),
						PF.setMotion((1 - rnd.nextDouble() * 2) / 20, 0.05, (1 - rnd.nextDouble() * 2) / 20),
						PF.setGravity(0.005)),
				1, PS.randomSquare(1, 0, 1));
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
