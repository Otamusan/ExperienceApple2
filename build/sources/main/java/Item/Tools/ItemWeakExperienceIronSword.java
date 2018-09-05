package Item.Tools;

import Util.ParticleUtil;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemWeakExperienceIronSword extends ItemSword {
	public ItemWeakExperienceIronSword() {
		super(Item.ToolMaterial.IRON);
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		ParticleUtil.blockRemaining(EnumParticleTypes.VILLAGER_HAPPY, target.world, target.getPosition(), 3);
		stack.damageItem(1, attacker);
		return true;
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, IBlockState state, BlockPos pos,
			EntityLivingBase entityLiving) {
		ParticleUtil.blockRemaining(EnumParticleTypes.VILLAGER_HAPPY, entityLiving.world, pos, 3);

		return false;
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
		ParticleUtil.blockRemaining(EnumParticleTypes.VILLAGER_HAPPY, entity.world, entity.getPosition(), 3);
		return false;
	}
}
