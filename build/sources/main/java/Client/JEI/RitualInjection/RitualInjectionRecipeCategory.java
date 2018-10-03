package Client.JEI.RitualInjection;

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

public class RitualInjectionRecipeCategory implements IRecipeCategory {
	public static final String UID = "experienceapple.ritualinjection";
	public final IGuiHelper guiHelper;
	private IDrawable background;

	public RitualInjectionRecipeCategory(IGuiHelper guiHelper) {
		this.guiHelper = guiHelper;
		this.background = guiHelper.createBlankDrawable(116, 54);
	}

	@Override
	public String getUid() {
		return UID;
	}

	@Override
	public String getTitle() {
		return I18n.format("ea.jei.ritualinjection");
	}

	@Override
	public String getModName() {
		return EAMain.MOD_NAME;
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper, IIngredients ingredients) {
		if (!(recipeWrapper instanceof RitualInjectionRecipeWrapper))
			return;
		recipeLayout.getItemStacks().init(0, true, 18, 18);
		recipeLayout.getItemStacks().set(0, ingredients.getInputs(ItemStack.class).get(0).get(0));

		ItemStack RP = new ItemStack(ItemRegister.ritualAssembler);
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setInteger("ritual", 4);
		RP.setTagCompound(nbt);

		recipeLayout.getItemStacks().init(1, true, 47, 18);
		recipeLayout.getItemStacks().set(1, RP);

		recipeLayout.getItemStacks().init(2, false, 76, 18);
		recipeLayout.getItemStacks().set(2, ingredients.getOutputs(ItemStack.class).get(0).get(0));
	}

	@Override
	public IDrawable getBackground() {
		return background;
	}

}
