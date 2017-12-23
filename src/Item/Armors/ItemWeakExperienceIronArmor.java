package Item.Armors;

import java.util.Random;

import ExperienceApple.EAMain;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemWeakExperienceIronArmor extends ItemArmor {

	public ItemWeakExperienceIronArmor(ArmorMaterial materialIn, int renderIndexIn,
			EntityEquipmentSlot equipmentSlotIn) {
		super(materialIn, renderIndexIn, equipmentSlotIn);
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
		Random random = new Random();
		if (random.nextInt(50) == 1) {
			if (!EAMain.particle) {
				world.spawnParticle(EnumParticleTypes.VILLAGER_HAPPY, player.posX + Math.random() - 0.5,
						player.posY + Math.random() * 2, player.posZ + Math.random() - 0.5, 0.0D, 0.0D, 0.0D);
			}
		}
	}

	@Override
	public void onCreated(ItemStack stack, World world, EntityPlayer player) {
		world.playSound(player, new BlockPos(player), SoundEvents.ENTITY_BLAZE_HURT, SoundCategory.PLAYERS, 1, 1);
	}
}
