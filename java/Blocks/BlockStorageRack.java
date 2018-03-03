package Blocks;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import ExperienceApple.ITooltip;
import TileEntity.TileStorageRack;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockStorageRack extends BlockContainer implements ITileEntityProvider, ITooltip {

	public BlockStorageRack(Material mate) {
		super(mate);
		isBlockContainer = true;
	}

	public boolean hasTileEntity(int metadata) {
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileStorageRack();
	}

	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			@Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		TileStorageRack storageRack = (TileStorageRack) world.getTileEntity(pos);
		// if (!world.isRemote) {
		if (storageRack.getItemStack() != null) {
			player.addChatMessage(new TextComponentTranslation("Item : " + world.isRemote));
			player.addChatMessage(
					new TextComponentTranslation("Item : " + storageRack.getItemStack().getDisplayName()));
			player.addChatMessage(new TextComponentTranslation("Amount : " + storageRack.getItemAmount()));
		}
		// }
		return true;
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
