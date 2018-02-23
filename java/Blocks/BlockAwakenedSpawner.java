package Blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import ExperienceApple.ITooltip;
import ExperienceApple.Register.BlockRegister;
import TileEntity.TileAwakenedSpawner;
import net.minecraft.block.BlockMobSpawner;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockAwakenedSpawner extends BlockMobSpawner implements ITileEntityProvider, ITooltip {

	@Nullable
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Item.getItemFromBlock(Blocks.MOB_SPAWNER);
	}

	@Override
	public int quantityDropped(Random p_149745_1_) {
		return 1;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileAwakenedSpawner();
	}

	public List<String> Tooltip = new ArrayList<String>();

	@Override
	public List<String> getTooltip() {
		return Tooltip;
	}

	@Nullable
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
		return new ItemStack(BlockRegister.awakenedSpawner);
	}

	@Override
	public void addTooltip(String str) {
		Tooltip.add(str);
	}
}
