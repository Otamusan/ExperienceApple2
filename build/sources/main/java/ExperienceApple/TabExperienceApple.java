package ExperienceApple;

import ExperienceApple.Register.ItemRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class TabExperienceApple extends CreativeTabs {

	public TabExperienceApple(String label) {
		super(label);
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(ItemRegister.experienceApple);
	}
}
