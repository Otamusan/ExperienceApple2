package Item.SpellPapers;

import java.util.ArrayList;
import java.util.List;

import ExperienceApple.EAMain;
import ExperienceApple.ITooltip;
import Util.ExperienceUtil;
import Util.ParticleUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemSatietySpellPaper extends Item implements ITooltip {
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
					ParticleUtil.playerRemaining(EnumParticleTypes.SLIME, entity, 9);
				}
			}
		}
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
