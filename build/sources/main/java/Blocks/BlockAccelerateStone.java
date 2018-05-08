package Blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ExperienceApple.ITooltip;
import TileEntity.TileAccelerateStone;
import net.minecraft.block.BlockGlass;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockAccelerateStone extends BlockGlass implements ITileEntityProvider, ITooltip {

	private int acceleration;

	public BlockAccelerateStone(Material mate, int acceleration) {
		super(mate, true);
		this.setAcceleration(acceleration);
	}

	public boolean hasTileEntity(int metadata) {
		return true;
	}

	public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rnd) {
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
		return new TileAccelerateStone();
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

	public int getAcceleration() {
		return acceleration;
	}

	public void setAcceleration(int acceleration) {
		this.acceleration = acceleration;
	}
}
