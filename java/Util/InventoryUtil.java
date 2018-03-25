package Util;

import javax.annotation.Nullable;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.util.EnumFacing;

public class InventoryUtil {
	public static boolean canCombine(ItemStack stack1, ItemStack stack2) {
		return stack1.getItem() != stack2.getItem() ? false
				: (stack1.getMetadata() != stack2.getMetadata() ? false
						: (stack1.stackSize > stack1.getMaxStackSize() ? false
								: ItemStack.areItemStackTagsEqual(stack1, stack2)));
	}

	public static boolean canInsertItemInSlot(IInventory inventoryIn, ItemStack stack, int index, EnumFacing side) {
		return !inventoryIn.isItemValidForSlot(index, stack) ? false
				: !(inventoryIn instanceof ISidedInventory)
						|| ((ISidedInventory) inventoryIn).canInsertItem(index, stack, side);
	}

	public static ItemStack insertStack(IInventory inventoryIn, ItemStack stack, int index, EnumFacing side) {
		ItemStack itemstack = inventoryIn.getStackInSlot(index);

		if (canInsertItemInSlot(inventoryIn, stack, index, side)) {
			boolean flag = false;

			if (itemstack == null) {
				int max = Math.min(stack.getMaxStackSize(), inventoryIn.getInventoryStackLimit());
				if (max >= stack.stackSize) {
					inventoryIn.setInventorySlotContents(index, stack);
					stack = null;
				} else {
					inventoryIn.setInventorySlotContents(index, stack.splitStack(max));
				}
				flag = true;
			} else if (canCombine(itemstack, stack)) {
				int max = Math.min(stack.getMaxStackSize(), inventoryIn.getInventoryStackLimit());
				if (max > itemstack.stackSize) {
					int i = max - itemstack.stackSize;
					int j = Math.min(stack.stackSize, i);
					stack.stackSize -= j;
					itemstack.stackSize += j;
					flag = j > 0;
				}
			}

			if (flag) {
				if (inventoryIn instanceof TileEntityHopper) {
					TileEntityHopper tileentityhopper = (TileEntityHopper) inventoryIn;

					if (tileentityhopper.mayTransfer()) {
						tileentityhopper.setTransferCooldown(8);
					}

					inventoryIn.markDirty();
				}

				inventoryIn.markDirty();
			}
		}

		return stack;
	}

	public static ItemStack putStackInInventoryAllSlots(IInventory inventoryIn, ItemStack stack,
			@Nullable EnumFacing side) {
		if (inventoryIn instanceof ISidedInventory && side != null) {
			ISidedInventory isidedinventory = (ISidedInventory) inventoryIn;
			int[] aint = isidedinventory.getSlotsForFace(side);

			for (int k = 0; k < aint.length && stack != null && stack.stackSize > 0; ++k) {
				stack = insertStack(inventoryIn, stack, aint[k], side);
			}
		} else {
			int i = inventoryIn.getSizeInventory();

			for (int j = 0; j < i && stack != null && stack.stackSize > 0; ++j) {
				stack = insertStack(inventoryIn, stack, j, side);
			}
		}

		if (stack != null && stack.stackSize == 0) {
			stack = null;
		}

		return stack;
	}

	public static void decreaseItem(ItemStack itemStack, IInventory inventory, int amount) {
		for (int i = 0; i < inventory.getSizeInventory(); i++) {
			if (inventory.getStackInSlot(i) == null)
				continue;
			if (!inventory.getStackInSlot(i).isItemEqual(itemStack))
				continue;
			if (inventory.getStackInSlot(i).stackSize == 0)
				continue;

			if (inventory.getStackInSlot(i).stackSize >= amount) {
				inventory.getStackInSlot(i).stackSize -= amount;
			} else {
				amount -= inventory.getStackInSlot(i).stackSize;
				inventory.setInventorySlotContents(i, null);
			}
		}

	}
}
