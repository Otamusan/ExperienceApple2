package Item.Tools;

import ExperienceApple.EAMain;
import Item.ExperienceRepair;
import Item.IExperienceRepair;
import Util.ParticleUtil;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemExperienceIronSword extends ItemSword implements IExperienceRepair {

	public static int cooldown = 0;
	private ExperienceRepair experienceRepair;

	public ItemExperienceIronSword(ToolMaterial mate, int cooltime, int cost) {
		super(mate);
		this.experienceRepair = new ExperienceRepair(cooltime, cost);
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, IBlockState state, BlockPos pos,
			EntityLivingBase entityLiving) {
		if (!EAMain.particle) {
			ParticleUtil.verticalCircle(EnumParticleTypes.VILLAGER_HAPPY, entityLiving.getEntityWorld(),
					pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 0.5, 12);
		}

		if (!world.isRemote && state.getBlockHardness(world, pos) != 0.0D) {
			stack.damageItem(1, entityLiving);
		}
		return false;
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
		ParticleUtil.verticalCircle(EnumParticleTypes.VILLAGER_HAPPY, entity.getEntityWorld(), entity.posX,
				entity.posY + entity.getMaxFallHeight() / 2, entity.posZ, 1, 12);
		return false;
	}

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entity, int itemSlot, boolean isSelected) {
		this.experienceRepair.onUpdate(stack, worldIn, entity, itemSlot, isSelected);
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
