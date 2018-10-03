package Client.JEI.RitualCraft;

import java.util.ArrayList;
import java.util.List;

import Rituals.Rituals.RitualCraft.RitualCraft;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;

public class RitualCraftRecipeWrapper implements IRecipeWrapper {
	private final List<List<ItemStack>> input;
	private final ItemStack output;

	public RitualCraftRecipeWrapper(RitualCraft ri) {
		input = new ArrayList<>();
		input.add(ri.getItemlist());
		output = ri.getItem();
	}

	@Override
	public void getIngredients(IIngredients ingredients) {
		ingredients.setInputLists(ItemStack.class, input);
		ingredients.setOutput(ItemStack.class, output);
	}
}
