package Item.Tools;

import Util.ParticleUtil;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemAdvancedExperienceIronShovel extends ItemSpade {
	public static int cooldown;

	public ItemAdvancedExperienceIronShovel(ToolMaterial mate) {
		super(mate);
	}

	@Override
	public void onCreated(ItemStack stack, World world, EntityPlayer player) {
		stack.addEnchantment(Enchantments.EFFICIENCY, 20);
		stack.addEnchantment(Enchantments.UNBREAKING, 5);
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, IBlockState state, BlockPos pos,
			EntityLivingBase entityLiving) {
		ParticleUtil.blockSurface(EnumParticleTypes.FIREWORKS_SPARK, world, pos, 10);
		ParticleUtil.blockRemaining(EnumParticleTypes.FIREWORKS_SPARK, world, pos, 10);
		if (!world.isRemote && state.getBlockHardness(world, pos) != 0.0D) {
			stack.damageItem(1, entityLiving);
		}
		return false;
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
		ParticleUtil.verticalCircle(EnumParticleTypes.FIREWORKS_SPARK, entity.getEntityWorld(), entity.posX,
				entity.posY + entity.getMaxFallHeight() / 2, entity.posZ, 1, 12);
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		return true;
	}
}
