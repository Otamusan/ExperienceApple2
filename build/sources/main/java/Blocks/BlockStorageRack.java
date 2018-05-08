package Blocks;

import java.util.ArrayList;
import java.util.List;

import ExperienceApple.ITooltip;
import TileEntity.TileStorageRack;
import Util.InventoryUtil;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockStorageRack extends BlockContainer implements ITileEntityProvider, ITooltip, ILeftClick {
	// public class BlockStorageRack extends Block implements ITooltip {

	public BlockStorageRack(Material mate) {
		super(mate);
	}

	public boolean hasTileEntity(int metadata) {
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileStorageRack();
	}

	@Override
	public void onLeftClick(EntityPlayer player, BlockPos pos, World world, EnumFacing facing) {
		TileStorageRack storageRack = (TileStorageRack) world.getTileEntity(pos);
		if (storageRack.conteinitem.isEmpty())
			return;
		if (player.isSneaking()) {
			for (int i = 0; i < 64; i++) {
				InventoryUtil.transferItemsOut(storageRack, player.inventory);
			}
		} else {
			InventoryUtil.transferItemsOut(storageRack, player.inventory);
		}
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			EnumFacing side, float hitX, float hitY, float hitZ) {
		TileStorageRack storageRack = (TileStorageRack) world.getTileEntity(pos);

		ItemStack heldItem = player.getHeldItem(hand);
		if (heldItem.isEmpty()) {
			if (storageRack.getItemStack().isEmpty()) {
				IInventory inventory = player.inventory;
				for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
					ItemStack outitem = InventoryUtil.putStackInInventoryAllSlots(inventory, storageRack,
							inventory.getStackInSlot(i), side);
					inventory.setInventorySlotContents(i, outitem);
				}
			}
		} else {
			if (storageRack.canItemChange()) {
				storageRack.setItemStack(heldItem);
			}
			ItemStack outitem = InventoryUtil.putStackInInventoryAllSlots(player.inventory, storageRack, heldItem,
					side);
			player.setHeldItem(hand, outitem);
		}
		return true;
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		TileEntity tileentity = worldIn.getTileEntity(pos);

		InventoryHelper.dropInventoryItems(worldIn, pos, (IInventory) tileentity);
		worldIn.updateComparatorOutputLevel(pos, this);
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
