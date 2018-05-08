package TileEntity;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.util.Constants;

public class TileRackManager extends TileEntity implements IInventory {

	private List<BlockPos> posrack = new ArrayList<BlockPos>();

	public List<BlockPos> getRackPosList() {
		return posrack;
	}

	public void setRackPosList(List<BlockPos> posrack) {
		this.posrack = posrack;
	}

	public void addRackPosList(BlockPos pos) {

		if (!posrack.contains(pos)) {
			posrack.add(pos);
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		NBTTagList tagList = new NBTTagList();

		for (BlockPos blockPos : posrack) {
			NBTTagCompound Tagpos = new NBTTagCompound();
			Tagpos.setInteger("x", blockPos.getX());
			Tagpos.setInteger("y", blockPos.getY());
			Tagpos.setInteger("z", blockPos.getZ());
			tagList.appendTag(Tagpos);
		}
		compound.setTag("PosRack", tagList);
		return compound;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		List<BlockPos> poslist = new ArrayList<BlockPos>();

		NBTTagList tagList = compound.getTagList("PosRack", Constants.NBT.TAG_COMPOUND);

		for (int i = 0; i < tagList.tagCount(); i++) {
			NBTTagCompound posnbt = tagList.getCompoundTagAt(i);
			BlockPos pos = new BlockPos(posnbt.getInteger("x"), posnbt.getInteger("y"), posnbt.getInteger("z"));
			poslist.add(pos);
		}

		setRackPosList(poslist);

	}

	@Override
	public String getName() {
		return "experienceapple2:storagerack";
	}

	@Override
	public boolean hasCustomName() {
		return false;
	}

	@Override
	public int getSizeInventory() {
		int size = 0;
		List<TileStorageRack> list = getTileList(new ArrayList<TileStorageRack>());
		for (TileStorageRack tile : this.getTileList(list)) {
			size += tile.getSizeInventory();
		}
		return size;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		TileandInt tInt = this.getTileandInt(index);
		if (tInt == null)
			return ItemStack.EMPTY;
		return tInt.storageRack.getStackInSlot(tInt.i);

	}

	@Override
	public ItemStack decrStackSize(int index, int count) {

		TileandInt tInt = this.getTileandInt(index);
		if (tInt == null)
			return ItemStack.EMPTY;
		return tInt.storageRack.decrStackSize(tInt.i, count);
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		TileandInt tInt = this.getTileandInt(index);
		if (tInt == null)
			return ItemStack.EMPTY;
		return tInt.storageRack.removeStackFromSlot(tInt.i);
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		TileandInt tInt = this.getTileandInt(index);
		if (tInt == null)
			return;
		tInt.storageRack.setInventorySlotContents(tInt.i, stack);
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public void openInventory(EntityPlayer player) {
	}

	@Override
	public void closeInventory(EntityPlayer player) {
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {

		TileandInt tInt = this.getTileandInt(index);
		if (tInt == null)
			return false;
		return tInt.storageRack.isItemValidForSlot(tInt.i, stack);
	}

	@Override
	public int getField(int id) {
		return 0;
	}

	@Override
	public void setField(int id, int value) {
	}

	@Override
	public int getFieldCount() {
		return 0;
	}

	@Override
	public void clear() {
	}

	public List<TileStorageRack> getTileList(List<TileStorageRack> list) {
		for (BlockPos pos : this.posrack) {
			if (world.getTileEntity(pos) instanceof TileStorageRack) {
				addlist(list, pos);

			}
		}
		return list;
	}

	public void addlist(List<TileStorageRack> list, BlockPos pos) {
		if (this.world.getTileEntity(pos) != null) {
			if (this.world.getTileEntity(pos) instanceof TileStorageRack) {
				list.add((TileStorageRack) this.world.getTileEntity(pos));
			}
		}
	}

	public TileandInt getTileandInt(int index) {
		List<TileStorageRack> list = this.getTileList(new ArrayList<TileStorageRack>());
		int buffi = index;
		for (TileStorageRack tile : this.getTileList(list)) {
			if (buffi < tile.getSizeInventory()) {
				return new TileandInt(tile, buffi);
			} else {
				buffi -= tile.getSizeInventory();
			}
		}
		return null;
	}

	class TileandInt {
		private TileStorageRack storageRack;
		private int i;

		public TileandInt(TileStorageRack storageRack, int i) {
			this.i = i;
			this.storageRack = storageRack;
		}

		public TileStorageRack getStorageRack() {
			return storageRack;
		}

		public void setStorageRack(TileStorageRack storageRack) {
			this.storageRack = storageRack;
		}

		public int getI() {
			return i;
		}

		public void setI(int i) {
			this.i = i;
		}
	}

	@Override
	public void handleUpdateTag(NBTTagCompound tag) {
		super.handleUpdateTag(tag);
		this.readFromNBT(tag);
	}

	@Override
	@Nullable
	public SPacketUpdateTileEntity getUpdatePacket() {
		int metadata = getBlockMetadata();
		return new SPacketUpdateTileEntity(this.pos, metadata, getUpdateTag());
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		readFromNBT(pkt.getNbtCompound());
	}

	@Override
	public NBTTagCompound getUpdateTag() {
		NBTTagCompound tag = super.getUpdateTag();
		writeToNBT(tag);
		return tag;
	}

	@Override
	public boolean isEmpty() {
		for (BlockPos blockPos : posrack) {
			if (world.getBlockState(blockPos) instanceof TileStorageRack)

				if (!((TileStorageRack) world.getBlockState(blockPos)).isEmpty()) {
					return false;
				}
		}
		return true;
	}

	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
		return true;
	}
}
