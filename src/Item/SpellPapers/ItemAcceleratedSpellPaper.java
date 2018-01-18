package Item.SpellPapers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ExperienceApple.ITooltip;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemAcceleratedSpellPaper extends Item implements ITooltip {

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int Slot, boolean isSelected) {
		if (new Random().nextFloat() > 0.9) {
			for (int i = 0; i < 10; i++) {
				entity.onUpdate();
				entity.onEntityUpdate();
			}

		}
	}

	public List<String> Tooltip = new ArrayList<String>();

	@Override
	public List<String> getTooltip() {
		return Tooltip;
	}

	@Override
	public void addTooltip(String str) {
		Tooltip.add(str);
	}
}
