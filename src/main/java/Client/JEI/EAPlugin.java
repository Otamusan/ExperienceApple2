package Client.JEI;

import Client.JEI.EA.EARecipeWrapper;
import Client.JEI.Enchant.EnchantRecipeWrapper;
import Client.JEI.Repair.RepairRecipeWrapper;
import Client.JEI.RitualCraft.RitualCraftRecipeCategory;
import Client.JEI.RitualCraft.RitualCraftRecipeWrapper;
import Client.JEI.RitualInjection.RitualInjectionRecipeCategory;
import Client.JEI.RitualInjection.RitualInjectionRecipeWrapper;
import ExperienceApple.Register.ItemRegister;
import ExperienceApple.Register.RecipeRegister;
import ExperienceApple.Register.RitualRegister;
import Recipes.EACraft;
import Recipes.EnchCraft;
import Recipes.RepairCraft;
import Rituals.Rituals.RitualCraft.RitualCraft;
import Rituals.Rituals.RitualCraft.RitualCraftCore;
import Rituals.Rituals.RitualInjection.RitualInjection;
import Rituals.Rituals.RitualInjection.RitualInjectionCore;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.ISubtypeRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.IModIngredientRegistration;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import mezz.jei.api.recipe.VanillaRecipeCategoryUid;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class EAPlugin implements IModPlugin {
	@JEIPlugin
	@Override
	public void registerItemSubtypes(ISubtypeRegistry subtypeRegistry) {

	}

	@Override
	public void registerIngredients(IModIngredientRegistration registry) {
	}

	@Override
	public void registerCategories(IRecipeCategoryRegistration registry) {

		registry.addRecipeCategories(new RitualInjectionRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
		registry.addRecipeCategories(new RitualCraftRecipeCategory(registry.getJeiHelpers().getGuiHelper()));

	}

	@Override
	public void register(IModRegistry registry) {
		// registry.addRecipeCategoryCraftingItem(new
		// ItemStack(ItemRegister.registeredExperienceApple),
		// EARecipeCategory.UID);
		registry.handleRecipes(EACraft.class, EARecipeWrapper::new, VanillaRecipeCategoryUid.CRAFTING);
		registry.addRecipes(RecipeRegister.eaCraftedItem, VanillaRecipeCategoryUid.CRAFTING);

		registry.handleRecipes(RepairCraft.class, RepairRecipeWrapper::new, VanillaRecipeCategoryUid.CRAFTING);
		registry.addRecipes(RecipeRegister.repaiableItem, VanillaRecipeCategoryUid.CRAFTING);

		registry.handleRecipes(EnchCraft.class, EnchantRecipeWrapper::new, VanillaRecipeCategoryUid.CRAFTING);
		registry.addRecipes(RecipeRegister.enchantableItem, VanillaRecipeCategoryUid.CRAFTING);

		registry.addRecipeCategoryCraftingItem(new ItemStack(ItemRegister.ritualAssembler),
				RitualInjectionRecipeCategory.UID);
		registry.handleRecipes(RitualInjection.class, RitualInjectionRecipeWrapper::new,
				RitualInjectionRecipeCategory.UID);
		registry.addRecipes(((RitualInjectionCore) RitualRegister.ritualInjection).register.getList(),
				RitualInjectionRecipeCategory.UID);

		registry.addRecipeCategoryCraftingItem(new ItemStack(Items.CAULDRON), RitualCraftRecipeCategory.UID);
		registry.handleRecipes(RitualCraft.class, RitualCraftRecipeWrapper::new, RitualCraftRecipeCategory.UID);
		registry.addRecipes(((RitualCraftCore) RitualRegister.ritualCraft).register.getList(),
				RitualCraftRecipeCategory.UID);
	}

}
