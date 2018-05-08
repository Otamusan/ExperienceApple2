package Recipes;

import ExperienceApple.Register.ItemRegister;
import Item.ItemRegisteredExperienceApple;
import Util.ExperienceUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class EACraft extends IForgeRegistryEntry.Impl<IRecipe> implements IRecipe {

	private ItemStack material;
	private int EXPamount;
	private ItemStack output;
	private ItemStack rea;
	private World world;

	public EACraft(ItemStack material, int EXPamount, ItemStack output) {
		this.material = material;
		this.EXPamount = EXPamount;
		this.output = output;
	}

	@Override
	public boolean matches(InventoryCrafting inv, World world) {
		int materialAmount = 0;
		int eaAmount = 0;
		this.world = world;
		for (int i = 0; i < inv.getSizeInventory(); i++) {
			ItemStack itemStack = inv.getStackInSlot(i);
			if (itemStack.isItemEqual(material)) {
				materialAmount++;
			} else if (itemStack.getItem() == ItemRegister.registeredExperienceApple) {
				if (ItemRegisteredExperienceApple.getPlayer(world, itemStack) != null) {
					EntityPlayer player = ItemRegisteredExperienceApple.getPlayer(world, itemStack);
					if (ExperienceUtil.getExperiencePoints(player) >= EXPamount) {
						eaAmount++;
						rea = itemStack;
					}
				}
			} else if (!itemStack.isEmpty()) {
				return false;
			}
		}

		if (materialAmount == 1 && eaAmount == 1) {
			return true;
		}
		return false;
	}

	@Override
	public NonNullList<ItemStack> getRemainingItems(InventoryCrafting inv) {
		NonNullList<ItemStack> nonNullList = NonNullList.withSize(inv.getSizeInventory(), ItemStack.EMPTY);
		for (int i = 0; i < inv.getSizeInventory(); i++) {
			ItemStack itemStack = inv.getStackInSlot(i);
			if (itemStack.getItem() == ItemRegister.registeredExperienceApple) {
				nonNullList.set(i, itemStack.copy());
			}
		}

		if (matches(inv, world)) {
			EntityPlayer player = ItemRegisteredExperienceApple.getPlayer(world, rea);

			ExperienceUtil.experiencePull(player, EXPamount, world);
		}
		return nonNullList;
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
		return output.copy();
	}

}
