package Client;

import ExperienceApple.Register.RecipeRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;

public class RepairCraftingEvent {

	@SubscribeEvent
	public void onItemCrafted(ItemCraftedEvent event) {
		IInventory inv = event.craftMatrix;
		ItemStack stack = event.crafting;
		EntityPlayer player = event.player;

		if (!gettoolRepairCraft(inv).isEmpty()) {
			System.out.println(2342342);
			player.world.playSound(player.posX, player.posY, player.posZ, SoundEvents.ENTITY_BLAZE_HURT,
					SoundCategory.PLAYERS, 1, 20, true);
		}
	}

	private static ItemStack gettoolRepairCraft(IInventory inv) {
		int itemamount = 0;
		ItemStack itemStack = ItemStack.EMPTY;
		for (int i = 0; i < inv.getSizeInventory(); i++) {
			if (!inv.getStackInSlot(i).isEmpty()) {
				itemamount++;
				itemStack = inv.getStackInSlot(i);
			}
		}
		if (contain(itemStack) && itemamount == 1) {
			return itemStack;
		} else {
			return ItemStack.EMPTY;
		}
	}

	private static boolean contain(ItemStack item) {
		for (IRecipe recipe : RecipeRegister.repaiableItem) {
			ItemStack item1 = recipe.getRecipeOutput();
			if (item1.getItem() == item.getItem()) {
				return true;
			}
		}
		return false;
	}
}