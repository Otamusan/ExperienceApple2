package Client.JEI.RitualInjection;

import java.util.ArrayList;
import java.util.List;

import Rituals.Rituals.RitualInjection.RitualInjection;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;

public class RitualInjectionRecipeWrapper implements IRecipeWrapper {
	private final List<List<ItemStack>> input;
	private final ItemStack output;

	public RitualInjectionRecipeWrapper(RitualInjection ri) {
		input = new ArrayList<>();
		input.add(new ArrayList<ItemStack>() {
			{
				add(new ItemStack(ri.getMaterialBlock()));
			}
		});
		output = new ItemStack(ri.getCraftedBlock());
	}

	@Override
	public void getIngredients(IIngredients ingredients) {
		ingredients.setInputLists(ItemStack.class, input);
		ingredients.setOutput(ItemStack.class, output);
	}
}
