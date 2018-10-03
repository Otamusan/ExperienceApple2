package Util;

import javax.annotation.Nullable;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;

public class InventoryUtil {
	public static boolean canCombine(ItemStack stack1, ItemStack stack2) {
		if (stack1.getItem() != stack2.getItem()) {
			return false;
		} else if (stack1.getMetadata() != stack2.getMetadata()) {
			return false;
		} else if (stack1.getCount() > stack1.getMaxStackSize()) {
			return false;
		} else {
			return ItemStack.areItemStackTagsEqual(stack1, stack2);
		}
	}

	private static boolean canInsertItemInSlot(IInventory inventoryIn, ItemStack stack, int index, EnumFacing side) {
		if (!inventoryIn.isItemValidForSlot(index, stack)) {
			return false;
		} else {
			return !(inventoryIn instanceof ISidedInventory)
					|| ((ISidedInventory) inventoryIn).canInsertItem(index, stack, side);
		}
	}

	public static ItemStack insertStack(IInventory source, IInventory destination, ItemStack stack, int index,
			EnumFacing direction) {
		ItemStack itemstack = destination.getStackInSlot(index);

		if (canInsertItemInSlot(destination, stack, index, direction)) {
			boolean flag = false;

			if (itemstack.isEmpty()) {
				destination.setInventorySlotContents(index, stack);
				stack = ItemStack.EMPTY;
				flag = true;
			} else if (canCombine(itemstack, stack)) {
				int i = stack.getMaxStackSize() - itemstack.getCount();
				int j = Math.min(stack.getCount(), i);
				stack.shrink(j);
				itemstack.grow(j);
				flag = j > 0;
			}
			if (flag) {

				destination.markDirty();
			}
		}

		return stack;
	}

	public static ItemStack putStackInInventoryAllSlots(IInventory source, IInventory destination, ItemStack stack,
			@Nullable EnumFacing direction) {
		if (destination instanceof ISidedInventory && direction != null) {
			ISidedInventory isidedinventory = (ISidedInventory) destination;
			int[] aint = isidedinventory.getSlotsForFace(direction);

			for (int k = 0; k < aint.length && !stack.isEmpty(); ++k) {
				stack = insertStack(source, destination, stack, aint[k], direction);
			}
		} else {
			int i = destination.getSizeInventory();

			for (int j = 0; j < i && !stack.isEmpty(); ++j) {
				stack = insertStack(source, destination, stack, j, direction);
			}
		}

		return stack;
	}

	public static void decreaseItem(ItemStack itemStack, IInventory inventory, int amount) {
		for (int i = 0; i < inventory.getSizeInventory(); i++) {
			if (inventory.getStackInSlot(i) == null)
				continue;
			if (!inventory.getStackInSlot(i).isItemEqual(itemStack))
				continue;
			if (inventory.getStackInSlot(i).getCount() == 0)
				continue;

			if (inventory.getStackInSlot(i).getCount() >= amount) {

				inventory.getStackInSlot(i).shrink(amount);
				return;
			} else {

				amount -= inventory.getStackInSlot(i).getCount();
				inventory.setInventorySlotContents(i, null);
			}
		}
	}

	public static boolean canExtractItemFromSlot(IInventory inventoryIn, ItemStack stack, int index, EnumFacing side) {
		return !(inventoryIn instanceof ISidedInventory)
				|| ((ISidedInventory) inventoryIn).canExtractItem(index, stack, side);
	}

	public static boolean pullItemFromSlot(IInventory hopper, IInventory inventoryIn, int index, EnumFacing direction) {
		ItemStack itemstack = inventoryIn.getStackInSlot(index);

		if (!itemstack.isEmpty() && canExtractItemFromSlot(inventoryIn, itemstack, index, direction)) {
			ItemStack itemstack1 = itemstack.copy();
			ItemStack itemstack2 = putStackInInventoryAllSlots(inventoryIn, hopper, inventoryIn.decrStackSize(index, 1),
					(EnumFacing) null);

			if (itemstack2.isEmpty()) {
				inventoryIn.markDirty();
				return true;
			}

			inventoryIn.setInventorySlotContents(index, itemstack1);
		}

		return false;
	}

	public static void pullItemFromInventory(IInventory hopper, IInventory inventoryIn, int amount,
			EnumFacing direction) {
		for (int i = 0; i < amount; i++) {
			for (int j = 0; j < inventoryIn.getSizeInventory(); j++) {
				if (pullItemFromSlot(hopper, inventoryIn, j, (EnumFacing) null))
					break;
			}
		}
	}

	public static boolean transferItemsOut(IInventory pullinventory, IInventory putinventory) {
		IInventory iinventory = putinventory;

		if (iinventory == null) {
			return false;
		} else {

			if (isInventoryFull(iinventory, null)) {
				return false;
			} else {
				for (int i = 0; i < pullinventory.getSizeInventory(); ++i) {
					if (!pullinventory.getStackInSlot(i).isEmpty()) {
						ItemStack itemstack = pullinventory.getStackInSlot(i).copy();
						ItemStack itemstack1 = putStackInInventoryAllSlots(pullinventory, putinventory,
								pullinventory.decrStackSize(i, 1), (EnumFacing) null);

						if (itemstack1.isEmpty()) {
							iinventory.markDirty();
							return true;
						}

						pullinventory.setInventorySlotContents(i, itemstack);
					}
				}

				return false;
			}
		}
	}

	static public boolean isInventoryFull(IInventory inventoryIn, EnumFacing side) {
		if (inventoryIn instanceof ISidedInventory) {
			ISidedInventory isidedinventory = (ISidedInventory) inventoryIn;
			int[] aint = isidedinventory.getSlotsForFace(side);

			for (int k : aint) {
				ItemStack itemstack1 = isidedinventory.getStackInSlot(k);

				if (itemstack1.isEmpty() || itemstack1.getCount() != itemstack1.getMaxStackSize()) {
					return false;
				}
			}
		} else {
			int i = inventoryIn.getSizeInventory();

			for (int j = 0; j < i; ++j) {
				ItemStack itemstack = inventoryIn.getStackInSlot(j);

				if (itemstack.isEmpty() || itemstack.getCount() != itemstack.getMaxStackSize()) {
					return false;
				}
			}
		}

		return true;
	}
}
