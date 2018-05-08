package Recipes;

import java.util.ArrayList;
import java.util.List;

import ExperienceApple.Register.ItemRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class EnchCraft extends IForgeRegistryEntry.Impl<IRecipe> implements IRecipe {

	private ItemStack tool;

	public EnchCraft(ItemStack tool) {
		this.tool = tool;
	}

	@Override
	public boolean matches(InventoryCrafting inv, World world) {
		int pearlAmount = 0;
		List<ItemStack> pearlList = new ArrayList<>();
		int toolAmount = 0;
		for (int i = 0; i < inv.getSizeInventory(); i++) {
			if (inv.getStackInSlot(i).getItem() == ItemRegister.enchantmentPearl) {
				pearlAmount++;
				pearlList.add(inv.getStackInSlot(i));
			} else if (ItemStack.areItemsEqualIgnoreDurability(tool, inv.getStackInSlot(i))) {
				toolAmount++;
			} else if (!inv.getStackInSlot(i).isEmpty()) {
				return false;
			}
		}
		if (toolAmount == 1) {
			return true;
		}

		return false;
	}

	private static ItemStack setEnchant(IInventory inv, ItemStack itemStack) {
		ItemStack newtool = itemStack.copy();
		for (int i = 0; i < inv.getSizeInventory(); i++) {
			if (inv.getStackInSlot(i).getItem() == ItemRegister.enchantmentPearl) {
				newtool.addEnchantment(
						Enchantment.getEnchantmentByID(
								inv.getStackInSlot(i).getEnchantmentTagList().getCompoundTagAt(0).getInteger("id")),
						inv.getStackInSlot(i).getEnchantmentTagList().getCompoundTagAt(0).getInteger("lvl"));
			}
		}
		return newtool;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting inv) {

		return setEnchant(inv, getRecipeOutput());
	}

	@Override
	public boolean canFit(int width, int height) {
		return true;
	}

	@Override
	public ItemStack getRecipeOutput() {
		return tool;
	}

}
