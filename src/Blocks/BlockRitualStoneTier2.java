package Blocks;

import java.util.Random;

import ExperienceApple.EAMain;
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

public class BlockRitualStoneTier2 extends BlockGlass {
	public BlockRitualStoneTier2(Material materialIn) {
		super(materialIn, true);
	}

	@Override
	public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rnd) {
		if (EAMain.particle == true)
			return;
		for (int i = 0; i < 4; i++) {
			double rx = pos.getX() + rnd.nextFloat();
			double ry = pos.getY() + rnd.nextFloat();
			double rz = pos.getZ() + rnd.nextFloat();
			world.spawnParticle(EnumParticleTypes.VILLAGER_HAPPY, rx, ry, rz, 0.0D, 0.0D, 0.0D);
		}
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
