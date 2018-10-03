package Client.JEI.RitualCraft;

import ExperienceApple.EAMain;
import ExperienceApple.Register.ItemRegister;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class RitualCraftRecipeCategory implements IRecipeCategory {
	public static final String UID = "experienceapple.ritualcraft";
	public final IGuiHelper guiHelper;
	private IDrawable background;

	public RitualCraftRecipeCategory(IGuiHelper guiHelper) {
		this.guiHelper = guiHelper;
		this.background = guiHelper.createBlankDrawable(116, 54);
	}

	@Override
	public String getUid() {
		return UID;
	}

	@Override
	public String getTitle() {
		return I18n.format("ea.jei.ritualcraft");
	}

	@Override
	public String getModName() {
		return EAMain.MOD_NAME;
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper, IIngredients ingredients) {
		if (!(recipeWrapper instanceof RitualCraftRecipeWrapper))
			return;
		int size = ingredients.getInputs(ItemStack.class).get(0).size();
		for (int i = 0; i < size; i++) {
			recipeLayout.getItemStacks().init(i, true, 18 * (i % 3), 18 * (i / 3));
			recipeLayout.getItemStacks().set(i, ingredients.getInputs(ItemStack.class).get(0).get(i));
		}

		ItemStack RP = new ItemStack(ItemRegister.ritualAssembler);
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setInteger("ritual", 8);
		RP.setTagCompound(nbt);

		recipeLayout.getItemStacks().init(size, true, 72, 18);
		recipeLayout.getItemStacks().set(size, RP);

		recipeLayout.getItemStacks().init(size + 1, false, 94, 18);
		recipeLayout.getItemStacks().set(size + 1, ingredients.getOutputs(ItemStack.class).get(0).get(0));
	}

	@Override
	public IDrawable getBackground() {
		return background;
	}

}
