package Blocks;

import java.util.ArrayList;
import java.util.List;

import ExperienceApple.ITooltip;
import ExperienceApple.Register.ItemRegister;
import TileEntity.TileRackManager;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockRackManager extends BlockContainer implements ITileEntityProvider, ITooltip {
	// public class BlockStorageRack extends Block implements ITooltip {

	public BlockRackManager(Material mate) {
		super(mate);
	}

	public boolean hasTileEntity(int metadata) {
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileRackManager();
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			EnumFacing side, float hitX, float hitY, float hitZ) {
		TileRackManager rackManager = (TileRackManager) world.getTileEntity(pos);
		ItemStack heldItem = player.getHeldItem(hand);
		if (heldItem.isEmpty()) {

			return false;
		}

		if (heldItem.getItem() == ItemRegister.warpStone) {
			if (heldItem.getTagCompound() != null) {
				double x = heldItem.getTagCompound().getDouble("setX");
				double y = heldItem.getTagCompound().getDouble("setY");
				double z = heldItem.getTagCompound().getDouble("setZ");

				BlockPos pos2 = new BlockPos(x, y, z);

				rackManager.addRackPosList(pos2);

				world.playSound(x, y, z, SoundEvents.BLOCK_ANVIL_USE, SoundCategory.PLAYERS, 1, 1, true);
				return true;

			}

		}
		return false;
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		super.breakBlock(worldIn, pos, state);
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

	public List<String> Tooltip = new ArrayList<String>();

	@Override
	public List<String> getTooltip() {
		return Tooltip;
	}

	@Override
	public EnumBlockRenderType getRenderType(IBlockState iBlockState) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	public void addTooltip(String str) {
		Tooltip.add(str);
	}

}
