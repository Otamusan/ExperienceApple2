package Rituals.Rituals.RitualDehydration;

import net.minecraft.item.ItemStack;

public class RitualDehydrationRecipe {
	private ItemStack source;
	private ItemStack item;

	public RitualDehydrationRecipe(ItemStack source, ItemStack item) {
		this.source = source;
		this.item = item;
	}

	public ItemStack getSource() {
		return source;
	}

	public ItemStack getItem() {
		return item;
	}

}
