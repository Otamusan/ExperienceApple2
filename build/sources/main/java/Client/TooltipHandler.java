package Client;

import java.util.Collection;
import java.util.List;

import ExperienceApple.Register.RecipeRegister;
import ExperienceApple.Register.RitualRegister;
import Recipes.EACraft;
import Rituals.Rituals.RitualCraft.RitualCraft;
import Rituals.Rituals.RitualCraft.RitualCraftCore;
import Rituals.Rituals.RitualInjection.RitualInjection;
import Rituals.Rituals.RitualInjection.RitualInjectionCore;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class TooltipHandler {

	@SubscribeEvent
	public void onItemTooltip(ItemTooltipEvent event) {
		ItemStack itemStack = event.getItemStack();
		if (!GuiScreen.isShiftKeyDown()) {
			return;
		}

		IRecipe eaIRecipe = conteinItemStack(itemStack, RecipeRegister.eaCraftedItem);
		if (eaIRecipe != null) {
			EACraft eaCraft = (EACraft) eaIRecipe;
			event.getToolTip().add(I18n.format("ea.eacraft.expcost") + " : " + eaCraft.getEXPamount());
			event.getToolTip().add(I18n.format("ea.eacraft.item") + " : "
					+ eaCraft.getMaterial().getItem().getItemStackDisplayName(eaCraft.getMaterial()));
			event.getToolTip().add("§b" + I18n.format("ea.eacraft.description"));

		}

		IRecipe repairIrecipe = conteinItemStack(itemStack, RecipeRegister.repaiableItem);
		if (repairIrecipe != null) {
			event.getToolTip().add("§b" + I18n.format("ea.repaircraft.description"));
		}

		IRecipe EchIRecipe = conteinItemStack(itemStack, RecipeRegister.enchantableItem);
		if (EchIRecipe != null) {
			event.getToolTip().add("§b" + I18n.format("ea.eccraft.description"));
		}

		RitualInjection RIRecipe = conteinItemStackInRI(itemStack,
				((RitualInjectionCore) (RitualRegister.ritualInjection)).register.getList().values());

		if (RIRecipe != null) {
			event.getToolTip().add(I18n.format("ea.ritual.injection.block.description") + " : "
					+ I18n.format(RIRecipe.getMaterialBlock().getUnlocalizedName() + ".name"));
		}

		RitualCraft RCIRecipe = conteinItemStackInRC(itemStack,
				((RitualCraftCore) (RitualRegister.ritualCraft)).register.getList().values());
		if (RCIRecipe != null) {
			String string = "";
			for (ItemStack item : RCIRecipe.itemlist) {
				string = string + I18n.format(item.getUnlocalizedName() + ".name") + "x" + item.getCount() + ", ";
			}
			event.getToolTip().add(string);
			event.getToolTip().add("§b" + I18n.format("ea.ritual.ritualCraft.item.description"));

		}

	}

	private IRecipe conteinItemStack(ItemStack itemStack, List<IRecipe> list) {
		for (IRecipe recipe : list) {
			if (recipe.getRecipeOutput().isItemEqualIgnoreDurability(itemStack))
				return recipe;
		}
		return null;
	}

	private RitualInjection conteinItemStackInRI(ItemStack itemStack, Collection<RitualInjection> list) {
		for (RitualInjection recipe : list) {
			if (new ItemStack(recipe.getCraftedBlock()).isItemEqualIgnoreDurability(itemStack))
				return recipe;
		}
		return null;
	}

	private RitualCraft conteinItemStackInRC(ItemStack itemStack, Collection<RitualCraft> list) {
		for (RitualCraft recipe : list) {
			if (recipe.getItem().isItemEqualIgnoreDurability(itemStack))
				return recipe;
		}
		return null;
	}
}