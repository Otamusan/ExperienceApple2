package Item.Armors;

import java.util.List;

import Item.ExperienceRepair;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemExperienceIronArmor extends ItemArmor {

	public static int cooldown = 0;
	private ExperienceRepair experienceRepair;

	public ItemExperienceIronArmor(ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn,
			int cooltime, int cost) {
		super(materialIn, renderIndexIn, equipmentSlotIn);
		this.experienceRepair = new ExperienceRepair(cooltime, cost);

	}

	@SideOnly(Side.CLIENT)

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(I18n.format("item.experienceIron.cooltime") + " : " + this.experienceRepair.getCooltime());
		tooltip.add(I18n.format("item.experienceIron.cost") + " : " + this.experienceRepair.getCost());
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

}
