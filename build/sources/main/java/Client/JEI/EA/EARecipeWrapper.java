package Client.JEI.EA;

import java.util.ArrayList;
import java.util.List;

import ExperienceApple.Register.ItemRegister;
import Recipes.EACraft;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.util.Translator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.item.ItemStack;

public class EARecipeWrapper implements IRecipeWrapper {
	private final List<List<ItemStack>> input;
	private final ItemStack output;
	private final int experience;

	public EARecipeWrapper(EACraft ea) {
		input = new ArrayList<>();
		input.add(new ArrayList<ItemStack>() {
			{
				add(ea.getMaterial());
			}
		});
		input.add(new ArrayList<ItemStack>() {
			{
				add(new ItemStack(ItemRegister.registeredExperienceApple));
			}
		});
		output = ea.getRecipeOutput();
		experience = ea.getEXPamount();
	}

	@Override
	public void getIngredients(IIngredients ingredients) {
		ingredients.setInputLists(ItemStack.class, input);
		ingredients.setOutput(ItemStack.class, output);
	}

	private void drawRepairCost(Minecraft minecraft, String text, int mainColor, int recipeWidth) {
		int shadowColor = 0xFF000000 | (mainColor & 0xFCFCFC) >> 2;
		int width = minecraft.fontRenderer.getStringWidth(text);
		int x = recipeWidth - 2 - width;
		int y = 0;

		if (minecraft.fontRenderer.getUnicodeFlag()) {
			Gui.drawRect(x - 2, y - 2, x + width + 2, y + 10, 0xFF000000);
			Gui.drawRect(x - 1, y - 1, x + width + 1, y + 9, 0xFF3B3B3B);
		} else {
			minecraft.fontRenderer.drawString(text, x + 1, y, shadowColor);
			minecraft.fontRenderer.drawString(text, x, y + 1, shadowColor);
			minecraft.fontRenderer.drawString(text, x + 1, y + 1, shadowColor);
		}

		minecraft.fontRenderer.drawString(text, x, y, mainColor);
	}

	@Override
	public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
		String experienceString = Translator.translateToLocalFormatted(experience + "XP");

		drawRepairCost(minecraft, experienceString, 0xFF80FF20, recipeWidth);
	}

}
