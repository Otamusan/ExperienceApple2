package Client.JEI.Repair;

import java.util.ArrayList;
import java.util.List;

import Recipes.RepairCraft;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;

public class RepairRecipeWrapper implements IRecipeWrapper {
	private final List<List<ItemStack>> input;
	private final List<ItemStack> output;

	public RepairRecipeWrapper(RepairCraft rc) {
		input = new ArrayList<>();
		input.add(new ArrayList<>());

		output = new ArrayList<>();

		ItemStack inp = rc.getToolData().copy();
		ItemStack oup = rc.getToolData().copy();

		inp.setItemDamage(1);
		oup.setItemDamage(0);

		input.get(0).add(inp);
		output.add(oup);

	}

	@Override
	public void getIngredients(IIngredients ingredients) {
		ingredients.setInputLists(ItemStack.class, input);
		ingredients.setOutputs(ItemStack.class, output);
	}

}
