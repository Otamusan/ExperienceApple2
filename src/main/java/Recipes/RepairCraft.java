package Recipes;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class RepairCraft extends IForgeRegistryEntry.Impl<IRecipe> implements IRecipe {

	private ItemStack toolData;
	private ItemStack toolReal;
	private int amount;

	public RepairCraft(ItemStack tool, int amount) {
		this.toolData = tool;
		this.amount = amount;
	}

	@Override
	public boolean matches(InventoryCrafting inv, World worldIn) {
		int toolAmount = 0;
		for (int i = 0; i < inv.getSizeInventory(); i++) {
			if (inv.getStackInSlot(i).isItemEqualIgnoreDurability(toolData)) {
				toolAmount++;
				toolReal = inv.getStackInSlot(i);
			} else if (!inv.getStackInSlot(i).isEmpty()) {
				return false;
			}
		}
		if (toolAmount == 1 && toolReal.getItemDamage() != 0) {
			return true;
		}
		return false;
	}

	public ItemStack getToolData() {
		return toolData;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting inv) {
		return getRecipeOutput();
	}

	@Override
	public boolean canFit(int width, int height) {
		return true;
	}

	@Override
	public ItemStack getRecipeOutput() {
		if (toolReal != null) {
			ItemStack stack = toolReal.copy();
			stack.setItemDamage(toolReal.getItemDamage() - amount);
			return stack;
		}
		return toolData.copy();
	}

}
