package Item.SpellPapers;

import ExperienceApple.EAMain;
import Util.ExperienceUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemSatietySpellPaper extends Item {
	public final int COST = 50;

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int Slot, boolean isSelected) {
		if (((EntityPlayer) entity).getFoodStats().getFoodLevel() != 20
				&& ExperienceUtil.getExperiencePoints((EntityPlayer) entity) >= COST) {
			ExperienceUtil.experiencePull((EntityPlayer) entity, COST, entity.worldObj);
			((EntityPlayer) entity).getFoodStats().setFoodLevel(20);
			world.playSound((EntityPlayer) entity, new BlockPos(entity), SoundEvents.BLOCK_SLIME_BREAK,
					SoundCategory.PLAYERS, 1, 1);
			if (!EAMain.particle) {
				for (int i = 0; i < 10; i++) {
					entity.worldObj.spawnParticle(EnumParticleTypes.SLIME, entity.posX + Math.random() - 0.5,
							entity.posY + 1.5, entity.posZ + Math.random() - 0.5, 0.0D, 0.0D, 0.0D);
				}
			}
		}
	}
}
