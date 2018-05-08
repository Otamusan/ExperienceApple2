package TileEntity;

import javax.annotation.Nullable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;

public class TileStorageRack extends TileEntity implements ITickable, IInventory {
	public NonNullList<ItemStack> chestContents = NonNullList.<ItemStack>withSize(1024 * 8, ItemStack.EMPTY);
	public ItemStack conteinitem = ItemStack.EMPTY;

	public int getSizeInventory() {
		return this.chestContents.size();
	}

	@Nullable
	public ItemStack getStackInSlot(int index) {
		markDirty();

		return this.chestContents.get(index);
	}

	@Nullable
	public ItemStack decrStackSize(int index, int count) {
		ItemStack itemstack = ItemStackHelper.getAndSplit(this.chestContents, index, count);
		return itemstack;
	}

	@Nullable
	public ItemStack removeStackFromSlot(int index) {
		return ItemStackHelper.getAndRemove(this.chestContents, index);
	}

	public void setInventorySlotContents(int index, @Nullable ItemStack stack) {
		this.chestContents.set(index, stack);
		setItemStack(stack);
		if (stack != null && stack.getCount() > this.getInventoryStackLimit()) {
			stack.setCount(this.getInventoryStackLimit());
		}
	}

	public String getName() {
		return "experienceapple2:storageRack";
	}

	public boolean hasCustomName() {
		return false;
	}

	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.chestContents = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);

		ItemStackHelper.loadAllItems(compound, this.chestContents);
		this.conteinitem = new ItemStack(compound.getCompoundTag("ConteinItem"));

	}

	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);

		ItemStackHelper.saveAllItems(compound, this.chestContents);
		NBTTagCompound nbttagcompound = new NBTTagCompound();
		this.conteinitem.writeToNBT(nbttagcompound);
		compound.setTag("ConteinItem", nbttagcompound);
		return compound;
	}

	public int getInventoryStackLimit() {
		return 64;
	}

	public void update() {
		for (EntityPlayer player : world.playerEntities) {
			if (player instanceof EntityPlayerMP) {
				EntityPlayerMP entityPlayer = (EntityPlayerMP) player;
				entityPlayer.connection.sendPacket(this.getUpdatePacket());
			}
		}

	}

	public boolean isItemValidForSlot(int index, ItemStack stack) {
		if (conteinitem.isEmpty()) {
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
		for (int i = 0; i < this.chestContents.size(); ++i) {
			this.chestContents.set(i, ItemStack.EMPTY);
		}
	}

	public void openInventory(EntityPlayer player) {

	}

	public void closeInventory(EntityPlayer player) {

	}

	public int getItemAmount() {
		if (conteinitem.isEmpty())
			return 0;
		int amount = 0;

		for (int i = 0; i < this.chestContents.size(); i++) {
			if (!this.chestContents.get(i).isEmpty()) {
				amount += this.chestContents.get(i).getCount();
			}
		}
		return amount;
	}

	public void setItemStack(ItemStack itemStack) {
		if (itemStack.isEmpty()) {
			conteinitem = ItemStack.EMPTY;
		} else {
			conteinitem = itemStack.copy();
		}
	}

	public ItemStack getItemStack() {
		return conteinitem.copy();
	}

	public boolean canItemChange() {
		if (getItemAmount() == 0)
			return true;
		else
			return false;
	}

	@Override
	@Nullable
	public SPacketUpdateTileEntity getUpdatePacket() {
		NBTTagCompound nbtTagCompound = new NBTTagCompound();
		writeToNBT(nbtTagCompound);
		int metadata = getBlockMetadata();
		return new SPacketUpdateTileEntity(this.pos, metadata, nbtTagCompound);
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		readFromNBT(pkt.getNbtCompound());
	}

	@Override
	public NBTTagCompound getUpdateTag() {
		NBTTagCompound nbtTagCompound = new NBTTagCompound();
		writeToNBT(nbtTagCompound);
		return nbtTagCompound;
	}

	@Override
	public void handleUpdateTag(NBTTagCompound tag) {
		this.readFromNBT(tag);
	}

	@Override
	public boolean isEmpty() {
		return getItemAmount() == 0;
	}

	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
		return true;
	}
}