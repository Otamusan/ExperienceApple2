package Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ExperienceApple.ITooltip;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemEnchantmentPearl extends Item implements ITooltip {
	@Override
	public boolean hasEffect(ItemStack itemstack) {
		return true;
	}

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if (!stack.isItemEnchanted()) {
			Enchantment enchantment = Enchantment.REGISTRY.getRandomObject(new Random());
			int levelmax = enchantment.getMaxLevel();
			int level = new Random().nextInt(levelmax) + 1;
			stack.addEnchantment(enchantment, level);
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
