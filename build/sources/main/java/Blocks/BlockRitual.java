package Blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ExperienceApple.ITooltip;
import Rituals.EnumRitualStones;
import Util.ParticleUtil;
import net.minecraft.block.Block;
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

public class BlockRitual extends Block implements ITooltip {
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

		ParticleUtil.blockRemaining(EnumParticleTypes.VILLAGER_HAPPY, world, pos, particleAmount);

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

	public List<String> Tooltip = new ArrayList<String>();

	@Override
	public List<String> getTooltip() {
		return Tooltip;
	}

	@Override
	public void addTooltip(String str) {
		Tooltip.add(str);
	}
}
