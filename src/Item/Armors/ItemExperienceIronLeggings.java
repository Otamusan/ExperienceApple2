package Item.Armors;

import ExperienceApple.EAMain;
import Util.ExperienceUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemExperienceIronLeggings extends ItemArmor {

	public static int cooldown = 0;

	public ItemExperienceIronLeggings(ArmorMaterial materialIn, int renderIndexIn,
			EntityEquipmentSlot equipmentSlotIn) {
		super(materialIn, renderIndexIn, equipmentSlotIn);
	}

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entity, int itemSlot, boolean isSelected) {
		if (ExperienceUtil.getExperiencePoints((EntityPlayer) entity) >= 8 && stack.getItemDamage() != 0
				&& cooldown >= 60) {
			for (int n = 0; n < 2; n++) {
				if (!EAMain.particle) {
					entity.getEntityWorld().spawnParticle(EnumParticleTypes.VILLAGER_HAPPY,
							entity.posX + Math.random() - 0.5,
							entity.posY + Math.random() * 2, entity.posZ + Math.random() - 0.5, 0.0D, 0.0D, 0.0D);
				}
			}
			stack.setItemDamage(stack.getItemDamage() - 1);
			EntityPlayer player = (EntityPlayer) entity;
			ExperienceUtil.experiencePull(player, 8, worldIn);
			worldIn.playSound(player, new BlockPos(player), SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP,
					SoundCategory.PLAYERS, 1, 1);
			cooldown = 0;
		} else {
			cooldown++;
		}
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
