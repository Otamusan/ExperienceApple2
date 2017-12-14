package Item.Armors;

import java.util.UUID;

import com.google.common.collect.Multimap;

import ExperienceApple.EAMain;
import Util.ExperienceUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemAdvancedExperienceIronBoots extends ItemArmor {

	public static int cooldown = 0;
	private static final UUID[] ARMOR_MODIFIERS = new UUID[] { UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B"),
			UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"),
			UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"),
			UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150") };

	public ItemAdvancedExperienceIronBoots(ArmorMaterial materialIn, int renderIndexIn,
			EntityEquipmentSlot equipmentSlotIn) {
		super(materialIn, renderIndexIn, equipmentSlotIn);
	}

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entity, int itemSlot, boolean isSelected) {
		EntityPlayer player = (EntityPlayer) entity;

		if (ExperienceUtil.getExperiencePoints((EntityPlayer) entity) >= 8 && stack.getItemDamage() != 0
				&& cooldown >= 5) {
			for (int n = 0; n < 2; n++) {
				if (!EAMain.particle) {
					entity.getEntityWorld().spawnParticle(EnumParticleTypes.VILLAGER_HAPPY,
							entity.posX + Math.random() - 0.5,
							entity.posY + Math.random() * 2, entity.posZ + Math.random() - 0.5, 0.0D, 0.0D, 0.0D);
				}
			}
			stack.setItemDamage(stack.getItemDamage() - 1);

			ExperienceUtil.experiencePull(player, 8, worldIn);
			worldIn.playSound(player, new BlockPos(player), SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP,
					SoundCategory.PLAYERS, 1, 1);
			cooldown = 0;
		} else {
			cooldown++;
		}
	}

	@Override
	public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot) {
		Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(equipmentSlot);

		if (equipmentSlot == this.armorType) {
			multimap.put(SharedMonsterAttributes.MAX_HEALTH.getAttributeUnlocalizedName(), new AttributeModifier(
					ARMOR_MODIFIERS[equipmentSlot.getIndex()], "Armor toughness", 80, 0));
		}

		return multimap;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		return true;
	}
}
