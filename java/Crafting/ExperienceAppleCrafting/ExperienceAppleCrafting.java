package Crafting.ExperienceAppleCrafting;

import java.util.UUID;

import javax.annotation.Nonnull;

import ExperienceApple.Register.ItemRegister;
import Util.ExperienceUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ExperienceAppleCrafting implements IRecipe {
	ItemStack Oitem;
	World worldIn;
	EntityPlayer player;

	@Override
	public boolean matches(@Nonnull InventoryCrafting inv, @Nonnull World world) {
		int EAFlag = 0;
		int otherItem = 0;
		worldIn = world;
		for (int count = 0; count < inv.getSizeInventory(); count++) {
			ItemStack stack = inv.getStackInSlot(count);
			if (!(stack == null)) {
				if (stack.getItem() == ItemRegister.registeredExperienceApple) {
					NBTTagCompound nbt = stack.getTagCompound();
					if (nbt != null) {
						if (nbt.getString("registedPlayer") != "") {
							EAFlag = EAFlag + 1;
							player = world.getPlayerEntityByUUID(UUID.fromString(nbt.getString("registedPlayer")));
							if (player == null)
								return false;
						}
					}
				}
				if (stack.getItem() != ItemRegister.registeredExperienceApple) {
					otherItem = otherItem + 1;
					Oitem = stack;
				}
			}
		}
		if (EAFlag == 1 && otherItem == 1) {
			if (ExperienceAppleCraftingRegister.EACRecipe.containsKey(Oitem.getItem())) {
				if (ExperienceAppleCraftingRegister.EACRecipe.get(Oitem.getItem()).XPCost <= ExperienceUtil
						.getExperiencePoints(player)) {
					return true;
				}
			}
		}
		return false;
	}

	@Nonnull
	@Override
	public ItemStack getCraftingResult(@Nonnull InventoryCrafting inv) {
		return new ItemStack(ExperienceAppleCraftingRegister.EACRecipe.get(Oitem.getItem()).getCRitem());
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
		ItemStack[] nonnulllist = new ItemStack[inv.getSizeInventory()];
		EntityPlayer player = null;
		for (int i = 0; i < nonnulllist.length; i++) {
			ItemStack itemstack = inv.getStackInSlot(i);

			if ((itemstack != null) && (itemstack.getItem() == ItemRegister.registeredExperienceApple)) {
				ItemStack EApple = new ItemStack(ItemRegister.registeredExperienceApple);
				NBTTagCompound nbt = itemstack.getTagCompound();
				EApple.setTagCompound(nbt);
				player = worldIn.getPlayerEntityByUUID(UUID.fromString(nbt.getString("registedPlayer")));
				nonnulllist[i] = EApple;
			}
		}
		ExperienceUtil.experiencePull(player,
				ExperienceAppleCraftingRegister.EACRecipe.get(Oitem.getItem()).XPCost, worldIn);
		return nonnulllist;
	}
}
