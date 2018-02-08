package Item;

import java.util.ArrayList;
import java.util.List;

import ExperienceApple.ITooltip;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemAshIngot extends Item implements ITooltip {

	private List<String> Tooltip = new ArrayList<String>();

	@Override
	public List<String> getTooltip() {
		return Tooltip;
	}

	@Override
	public void addTooltip(String str) {
		this.Tooltip.add(str);
	}

	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		return true;
	}
}
