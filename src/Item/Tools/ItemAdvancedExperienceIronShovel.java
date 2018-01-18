package Item.Tools;

import java.util.ArrayList;
import java.util.List;

import ExperienceApple.ITooltip;
import Util.ParticleUtil;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemAdvancedExperienceIronShovel extends ItemSpade implements ITooltip {
	public static int cooldown;
	public int range = 3;

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
		if (!entityLiving.isSneaking())
			return false;
		ParticleUtil.blockSurface(EnumParticleTypes.FIREWORKS_SPARK, world, pos, 10);
		ParticleUtil.blockRemaining(EnumParticleTypes.FIREWORKS_SPARK, world, pos, 10);
		for (int ix = -range; ix < range; ix++) {
			for (int iy = -range; iy < range; iy++) {
				for (int iz = -range; iz < range; iz++) {

					BlockPos xpPos = new BlockPos(ix + pos.getX(), iy + pos.getY(), iz + pos.getZ());
					if (world.getBlockState(xpPos) != state)
						continue;
					state.getBlock().harvestBlock(world, (EntityPlayer) entityLiving, xpPos, state,
							world.getTileEntity(xpPos), stack);
					state.getBlock().breakBlock(world, xpPos, state);
					world.setBlockToAir(xpPos);
				}
			}
		}
		return false;
	}

	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if (!isSelected)
			return;
		if (!(entityIn instanceof EntityLivingBase))
			return;
		List<Entity> entities = worldIn.loadedEntityList;
		for (Entity entity : entities) {
			if (entityIn.getDistanceToEntity(entity) < 50 && (entity instanceof EntityLivingBase)) {
				// System.out.println(entity);
				// if (worldIn.isRemote) {
				PotionEffect potioneffect = new PotionEffect(MobEffects.GLOWING, 10, 0, true, false);
				((EntityLivingBase) entity).addPotionEffect(potioneffect);
				// }
			}
		}
		PotionEffect potioneffect = new PotionEffect(MobEffects.INVISIBILITY, 10, 0, true, false);
		((EntityLivingBase) entityIn).addPotionEffect(potioneffect);
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
