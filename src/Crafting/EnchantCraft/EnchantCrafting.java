package Crafting.EnchantCraft;

import javax.annotation.Nonnull;

import ExperienceApple.Register.ItemRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

public class EnchantCrafting implements IRecipe {
	ItemStack tool;
	World worldIn;
	ItemStack enchPearl;

	@Override
	public boolean matches(@Nonnull InventoryCrafting inv, @Nonnull World world) {
		int toolAmount = 0;
		int enchPearlAmount = 0;
		worldIn = world;
		for (int count = 0; count < inv.getSizeInventory(); count++) {
			ItemStack stack = inv.getStackInSlot(count);
			if (!(stack == null)) {

				if (EnchantRegister.canEnchant.contains(stack.getItem())) {
					toolAmount++;
					tool = stack;
				}

				if (stack.getItem() == ItemRegister.enchantmentPearl) {
					enchPearlAmount++;
					enchPearl = stack;
				}

			}
		}
		if (toolAmount == 1 && enchPearlAmount == 1) {
			return true;
		}
		return false;
	}

	@Nonnull
	@Override
	public ItemStack getCraftingResult(@Nonnull InventoryCrafting inv) {
		ItemStack newTool = tool.copy();

		;

		newTool.addEnchantment(
				Enchantment.getEnchantmentByID(enchPearl.getEnchantmentTagList().getCompoundTagAt(0).getInteger("id")),
				enchPearl.getEnchantmentTagList().getCompoundTagAt(0).getInteger("lvl"));
		return newTool;
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

	@Nonnull
	@Override
	public ItemStack[] getRemainingItems(@Nonnull InventoryCrafting inv) {
		return ForgeHooks.defaultRecipeGetRemainingItems(inv);
	}
}
