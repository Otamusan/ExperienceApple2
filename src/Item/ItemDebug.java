package Item;

import Util.ParticleUtil;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemDebug extends ItemSpade implements IExperienceRepair {

	private ExperienceRepair experienceRepair;

	public ItemDebug() {
		super(ToolMaterial.DIAMOND);
		this.experienceRepair = new ExperienceRepair(60, 20);
	}

	public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos,
			EntityLivingBase entityLiving) {
		ParticleUtil.random(EnumParticleTypes.VILLAGER_HAPPY, worldIn, pos.getX(), pos.getY(), pos.getZ(),
				pos.getX() - 1, pos.getY() - 7, pos.getZ() - 1, 1000);
		return false;
	}

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entity, int itemSlot, boolean isSelected) {
	}

}
