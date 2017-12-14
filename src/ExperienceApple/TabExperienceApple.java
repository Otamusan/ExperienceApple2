package ExperienceApple;

import ExperienceApple.Register.ItemRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class TabExperienceApple extends CreativeTabs {

	public TabExperienceApple(String label) {
		super(label);
	}

	@Override
	public Item getTabIconItem() {
		return ItemRegister.experienceApple;
	}

}
