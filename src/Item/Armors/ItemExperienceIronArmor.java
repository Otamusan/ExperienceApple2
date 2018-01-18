package Item.Armors;

import java.util.ArrayList;
import java.util.List;

import ExperienceApple.ITooltip;
import Item.ExperienceRepair;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemExperienceIronArmor extends ItemArmor implements ITooltip {

	public static int cooldown = 0;
	private ExperienceRepair experienceRepair;

	public ItemExperienceIronArmor(ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn,
			int cooltime, int cost) {
		super(materialIn, renderIndexIn, equipmentSlotIn);
		this.experienceRepair = new ExperienceRepair(cooltime, cost);

	}

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entity, int itemSlot, boolean isSelected) {
		this.experienceRepair.onUpdate(stack, worldIn, entity, itemSlot, isSelected);
	}

	@Override
	public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
		return false;
	}

	@Override
	public int getItemEnchantability() {
		return 0;
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
