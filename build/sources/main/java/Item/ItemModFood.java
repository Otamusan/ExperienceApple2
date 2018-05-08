package Item;

import java.util.ArrayList;
import java.util.List;

import ExperienceApple.ITooltip;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;

public class ItemModFood extends ItemFood implements ITooltip {

	private int speed;

	public ItemModFood(int amount, float saturation, boolean isWolfFood, int speed) {
		super(amount, saturation, isWolfFood);
		this.speed = speed;
	}

	private List<String> Tooltip = new ArrayList<String>();

	@Override
	public List<String> getTooltip() {
		return Tooltip;
	}

	public int getMaxItemUseDuration(ItemStack stack) {
		return 4;
	}

	@Override
	public void addTooltip(String str) {
		this.Tooltip.add(str);
	}
}
