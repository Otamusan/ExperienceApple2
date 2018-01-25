package Crafting.RepairCraft;

import javax.annotation.Nonnull;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public class RepairCrafting implements IRecipe {
	ItemStack Oitem;
	World worldIn;
	EntityPlayer player;

	@Override
	public boolean matches(@Nonnull InventoryCrafting inv, @Nonnull World world) {
		int EAFlag = 0;
		worldIn = world;
		for (int count = 0; count < inv.getSizeInventory(); count++) {
			ItemStack stack = inv.getStackInSlot(count);
			if (!(stack == null)) {
				EAFlag = EAFlag + 1;
				Oitem = stack;
			}
		}

		if (EAFlag == 1) {
			if (RepairRegister.canRepiar.contains(Oitem.getItem())) {
				return true;
			}
		}
		return false;
	}

	@Nonnull
	@Override
	public ItemStack getCraftingResult(@Nonnull InventoryCrafting inv) {
		if (Oitem.getItemDamage() > 1) {
			return new ItemStack(Oitem.getItem(), 1, Oitem.getItemDamage() - 1);
		} else {
			return new ItemStack(Oitem.getItem(), 1, 0);
		}
	}

	@Override
	public int getRecipeSize() {
		return 9;
	}

	@Nonnull
	@Override
	public ItemStack getRecipeOutput() {
		return null;
	}

	@Override
	public ItemStack[] getRemainingItems(InventoryCrafting inv) {
		ItemStack[] aitemstack = new ItemStack[inv.getSizeInventory()];

		for (int i = 0; i < aitemstack.length; ++i) {
			ItemStack itemstack = inv.getStackInSlot(i);
			aitemstack[i] = net.minecraftforge.common.ForgeHooks.getContainerItem(itemstack);
		}

		return aitemstack;
	}
}
