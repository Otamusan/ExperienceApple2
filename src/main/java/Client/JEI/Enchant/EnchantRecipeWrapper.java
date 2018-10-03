package Client.JEI.Enchant;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import ExperienceApple.Register.ItemRegister;
import Recipes.EnchCraft;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;

public class EnchantRecipeWrapper implements IRecipeWrapper {
	private final List<List<ItemStack>> input;
	private final ItemStack output;

	public EnchantRecipeWrapper(EnchCraft ec) {
		input = new ArrayList<>();

		input.add(new ArrayList<ItemStack>() {
			{
				add(ec.getTool().copy());
			}
		});
		input.add(new ArrayList<ItemStack>());
		Enchantment.REGISTRY.forEach(new Consumer<Enchantment>() {
			public void accept(Enchantment t) {
				ItemStack pearl = new ItemStack(ItemRegister.enchantmentPearl);
				pearl.addEnchantment(t, t.getMaxLevel());
				input.get(1).add(pearl);
			};
		});
		output = ec.getTool();

	}

	@Override
	public void getIngredients(IIngredients ingredients) {
		ingredients.setInputLists(ItemStack.class, input);
		ingredients.setOutput(ItemStack.class, output);
	}
}
