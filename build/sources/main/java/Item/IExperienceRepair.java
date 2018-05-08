package Item;

import ExperienceApple.EAMain;
import Util.ExperienceUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IExperienceRepair {
	int cooltime = 0;
	int cost = 5;

	default public void onUpdate(ItemStack stack, World worldIn, Entity entity, int itemSlot, boolean isSelected) {
		System.out.println(114514);
		NBTTagCompound nbt = stack.getTagCompound();
		if (nbt == null)
			nbt = new NBTTagCompound();
		int cooldown = nbt.getInteger("cooldown");
		if (ExperienceUtil.getExperiencePoints((EntityPlayer) entity) >= 8 && stack.getItemDamage() != 0
				&& cooldown >= cooltime) {
			for (int n = 0; n < 9; n++) {
				if (!EAMain.particle) {
					entity.getEntityWorld().spawnParticle(EnumParticleTypes.VILLAGER_HAPPY,
							entity.posX + Math.random() - 0.5, entity.posY + Math.random() * 2,
							entity.posZ + Math.random() - 0.5, 0.0D, 0.0D, 0.0D);
				}
			}
			stack.setItemDamage(stack.getItemDamage() - 1);
			EntityPlayer player = (EntityPlayer) entity;
			ExperienceUtil.experiencePull(player, cost, worldIn);
			worldIn.playSound(player, new BlockPos(player), SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP,
					SoundCategory.PLAYERS, 1, 1);
			cooldown = 0;
			// player.inventory.setInventorySlotContents(itemSlot, stack);
		} else {
			cooldown++;
		}
		nbt.setInteger("cooldown", cooldown);
		stack.setTagCompound(nbt);
	}
}
