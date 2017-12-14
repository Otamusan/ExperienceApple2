package Item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemSuperDye extends Item {
	public ItemSuperDye() {
		setContainerItem(this);
	}

	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return false;
	}

	@Override
	public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
		return false;
	}

	@Override
	public int getItemEnchantability() {
		return 0;
	}
}
