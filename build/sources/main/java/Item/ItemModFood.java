package Item;

import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;

public class ItemModFood extends ItemFood {

	private int speed;

	public ItemModFood(int amount, float saturation, boolean isWolfFood, int speed) {
		super(amount, saturation, isWolfFood);
		this.speed = speed;
	}

	public int getMaxItemUseDuration(ItemStack stack) {
		return speed;
	}

}
