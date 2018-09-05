package Blocks;

import java.util.Random;

import Util.ParticleUtil;
import net.minecraft.block.BlockGlass;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumParticleTypes;
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
		ParticleUtil.blockRemaining(EnumParticleTypes.ENCHANTMENT_TABLE, world, pos, 9);
		/*
		 * for (int i = 0; i < 10; i++) { ParticleEABase particle = new
		 * ParticleEABase(world, pos.getX() + rnd.nextFloat(), pos.getY() +
		 * rnd.nextFloat(), pos.getZ() + rnd.nextFloat());
		 * particle.setRBGColorF(0, 0.5F, 0); particle.setAlphaF(0.5F);
		 * particle.setmotion(0, -0.01, 0); particle.setMaxAge(20);
		 * Minecraft.getMinecraft().effectRenderer.addEffect(particle); }
		 */

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
