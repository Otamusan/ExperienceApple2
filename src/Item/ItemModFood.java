package Item;

import java.util.ArrayList;
import java.util.List;

import ExperienceApple.ITooltip;
import net.minecraft.item.ItemFood;

public class ItemModFood extends ItemFood implements ITooltip {

	public ItemModFood(int amount, float saturation, boolean isWolfFood) {
		super(amount, saturation, isWolfFood);
	}

	private List<String> Tooltip = new ArrayList<String>();

	@Override
	public List<String> getTooltip() {
		return Tooltip;
	}

	@Override
	public void addTooltip(String str) {
		this.Tooltip.add(str);
	}
}
