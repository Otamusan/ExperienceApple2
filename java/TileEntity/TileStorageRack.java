package TileEntity;

import javax.annotation.Nullable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public class TileStorageRack extends TileEntity implements ITickable, IInventory {
	public ItemStack[] chestContents = new ItemStack[1024];
	public ItemStack conteinitem;

	public int getSizeInventory() {
		return 1024;
	}

	@Nullable
	public ItemStack getStackInSlot(int index) {
		markDirty();

		return this.chestContents[index];
	}

	@Nullable
	public ItemStack decrStackSize(int index, int count) {
		ItemStack itemstack = ItemStackHelper.getAndSplit(this.chestContents, index, count);

		if (itemstack != null) {
			this.markDirty();
		}

		return itemstack;
	}

	@Nullable
	public ItemStack removeStackFromSlot(int index) {
		return ItemStackHelper.getAndRemove(this.chestContents, index);
	}

	public void setInventorySlotContents(int index, @Nullable ItemStack stack) {
		this.chestContents[index] = stack;
		setItemStack(stack);
		if (stack != null && stack.stackSize > this.getInventoryStackLimit()) {
			stack.stackSize = this.getInventoryStackLimit();
		}

		this.markDirty();
	}

	public String getName() {
		return "storageRack";
	}

	public boolean hasCustomName() {
		return false;
	}

	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.chestContents = new ItemStack[this.getSizeInventory()];

		NBTTagList nbttaglist = compound.getTagList("Items", 10);

		for (int i = 0; i < nbttaglist.tagCount(); ++i) {
			NBTTagCompound nbttagcompound = nbttaglist.getCompoundTagAt(i);
			int j = nbttagcompound.getByte("Slot") & 255;

			if (j >= 0 && j < this.chestContents.length) {
				this.chestContents[j] = ItemStack.loadItemStackFromNBT(nbttagcompound);
			}
		}
		this.conteinitem = ItemStack.loadItemStackFromNBT(compound.getCompoundTag("ConteinItem"));
	}

	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);

		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < this.chestContents.length; ++i) {
			if (this.chestContents[i] != null) {
				NBTTagCompound nbttagcompound = new NBTTagCompound();
				nbttagcompound.setByte("Slot", (byte) i);
				this.chestContents[i].writeToNBT(nbttagcompound);
				nbttaglist.appendTag(nbttagcompound);
			}
		}

		if (this.conteinitem != null) {
			NBTTagCompound nbttagcompound = new NBTTagCompound();
			this.conteinitem.writeToNBT(nbttagcompound);
			compound.setTag("ConteinItem", nbttagcompound);
		}

		compound.setTag("Items", nbttaglist);

		// System.out.println(compound);

		return compound;
	}

	public int getInventoryStackLimit() {
		return 64;
	}

	public boolean isUseableByPlayer(EntityPlayer player) {
		return true;
	}

	public void update() {
		for (EntityPlayer player : worldObj.playerEntities) {
			if (player instanceof EntityPlayerMP) {
				EntityPlayerMP entityPlayer = (EntityPlayerMP) player;
				entityPlayer.connection.sendPacket(this.getUpdatePacket());
			}
		}
	}

	public boolean isItemValidForSlot(int index, ItemStack stack) {
		if (conteinitem == null) {

			return true;

		}
		if (canCombine(stack, conteinitem)) {
			return true;

		} else {
			return false;
		}
	}

	private static boolean canCombine(ItemStack stack1, ItemStack stack2) {
		return stack1.getItem() != stack2.getItem() ? false
				: (stack1.getMetadata() != stack2.getMetadata() ? false
						: ItemStack.areItemStackTagsEqual(stack1, stack2));
	}

	public int getField(int id) {
		return 0;
	}

	public void setField(int id, int value) {
	}

	public int getFieldCount() {
		return 0;
	}

	public void clear() {
		for (int i = 0; i < this.chestContents.length; ++i) {
			this.chestContents[i] = null;
		}
	}

	public void openInventory(EntityPlayer player) {

	}

	public void closeInventory(EntityPlayer player) {

	}

	public int getItemAmount() {
		if (conteinitem == null)
			return 0;
		int amount = 0;

		for (int i = 0; i < this.chestContents.length; i++) {
			if (this.chestContents[i] != null) {
				amount += this.chestContents[i].stackSize;
			}
		}
		return amount;
	}

	public void setItemStack(ItemStack itemStack) {
		if (itemStack == null) {
			conteinitem = null;
		} else {
			conteinitem = itemStack.copy();
		}
	}

	public ItemStack getItemStack() {
		if (conteinitem == null)
			return null;
		return conteinitem.copy();
	}

	public boolean canItemChange() {
		if (getItemAmount() == 0)
			return true;
		else
			return false;
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

}