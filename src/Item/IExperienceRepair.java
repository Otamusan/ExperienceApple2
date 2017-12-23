package Item;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface IExperienceRepair {
	public void onUpdate(ItemStack stack, World worldIn, Entity entity, int itemSlot, boolean isSelected);
}
