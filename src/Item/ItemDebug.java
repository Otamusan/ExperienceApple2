package Item;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;

public class ItemDebug extends ItemSword implements IExperienceRepair {

	private ExperienceRepair experienceRepair;

	public ItemDebug() {
		super(ToolMaterial.DIAMOND);
		this.experienceRepair = new ExperienceRepair(60, 20);
	}

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entity, int itemSlot, boolean isSelected) {
		this.experienceRepair.onUpdate(stack, worldIn, entity, itemSlot, isSelected);
	}

}
