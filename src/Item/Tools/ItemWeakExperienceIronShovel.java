package Item.Tools;

import ExperienceApple.EAMain;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemWeakExperienceIronShovel extends ItemSpade {
	public ItemWeakExperienceIronShovel() {
		super(Item.ToolMaterial.IRON);
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, IBlockState state, BlockPos pos,
			EntityLivingBase entityLiving) {
		if (!EAMain.particle) {
			for (int n = 0; n < 3; n++) {
				world.spawnParticle(EnumParticleTypes.VILLAGER_HAPPY, pos.getX() + Math.random(),
						pos.getY() + Math.random(), pos.getZ() + Math.random(), 0.0D, 0.0D, 0.0D);
			}
		}

		if (!world.isRemote && state.getBlockHardness(world, pos) != 0.0D) {
			stack.damageItem(1, entityLiving);
		}
		return false;
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
		for (int n = 0; n < 3; n++) {
			if (!EAMain.particle) {
				entity.getEntityWorld().spawnParticle(EnumParticleTypes.VILLAGER_HAPPY,
						entity.posX + Math.random() - 0.5,
						entity.posY + Math.random() * 2, entity.posZ + Math.random() - 0.5, 0.0D, 0.0D, 0.0D);
			}
		}
		return false;
	}

	@Override
	public void onCreated(ItemStack stack, World world, EntityPlayer player) {
		world.playSound(player, new BlockPos(player), SoundEvents.ENTITY_BLAZE_HURT, SoundCategory.PLAYERS, 1, 1);
	}
}
