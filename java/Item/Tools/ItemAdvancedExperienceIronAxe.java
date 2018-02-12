package Item.Tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Sets;

import ExperienceApple.ITooltip;
import Util.ParticleUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLog;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemAdvancedExperienceIronAxe extends ItemTool implements ITooltip {

	public static int cooldown = 0;
	public int range = 3;
	private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(new Block[] { Blocks.PLANKS, Blocks.BOOKSHELF,
			Blocks.LOG, Blocks.LOG2, Blocks.CHEST, Blocks.PUMPKIN, Blocks.LIT_PUMPKIN, Blocks.MELON_BLOCK,
			Blocks.LADDER, Blocks.WOODEN_BUTTON, Blocks.WOODEN_PRESSURE_PLATE });

	public ItemAdvancedExperienceIronAxe(ToolMaterial mate) {
		super(mate, EFFECTIVE_ON);
		this.damageVsEntity = mate.getDamageVsEntity();
		this.attackSpeed = -3.2F;
	}

	@Override
	public float getStrVsBlock(ItemStack stack, IBlockState state) {
		Material material = state.getMaterial();
		return material != Material.WOOD && material != Material.PLANTS && material != Material.VINE
				? super.getStrVsBlock(stack, state) : this.efficiencyOnProperMaterial;
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, IBlockState state, BlockPos pos,
			EntityLivingBase entityLiving) {
		if (!entityLiving.isSneaking())
			return false;
		if (!(world.getBlockState(pos).getBlock() instanceof BlockLog)
				&& !(world.getBlockState(pos).getBlock() instanceof BlockLeaves))
			return false;
		ParticleUtil.blockSurface(EnumParticleTypes.FIREWORKS_SPARK, world, pos, 5);
		ParticleUtil.blockRemaining(EnumParticleTypes.FIREWORKS_SPARK, world, pos, 5);
		cheinDestruction(pos, world, (EntityPlayer) entityLiving, world.getBlockState(pos).getBlock());
		return false;
	}

	public static void cheinDestruction(BlockPos pos, World world, EntityPlayer player, Block block) {
		world.destroyBlock(pos, true);
		world.getBlockState(pos).getBlock().onBlockDestroyedByPlayer(world, pos, world.getBlockState(pos));
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		if (world.getBlockState(new BlockPos(x, y, z + 1)).getBlock() == block)
			cheinDestruction(new BlockPos(x, y, z + 1), world, player, block);
		if (world.getBlockState(new BlockPos(x, y, z - 1)).getBlock() == block)
			cheinDestruction(new BlockPos(x, y, z - 1), world, player, block);

		if (world.getBlockState(new BlockPos(x, y + 1, z)).getBlock() == block)
			cheinDestruction(new BlockPos(x, y + 1, z), world, player, block);
		if (world.getBlockState(new BlockPos(x, y + 1, z + 1)).getBlock() == block)
			cheinDestruction(new BlockPos(x, y + 1, z + 1), world, player, block);
		if (world.getBlockState(new BlockPos(x, y + 1, z - 1)).getBlock() == block)
			cheinDestruction(new BlockPos(x, y + 1, z - 1), world, player, block);

		if (world.getBlockState(new BlockPos(x, y - 1, z)).getBlock() == block)
			cheinDestruction(new BlockPos(x, y - 1, z), world, player, block);
		if (world.getBlockState(new BlockPos(x, y - 1, z + 1)).getBlock() == block)
			cheinDestruction(new BlockPos(x, y - 1, z + 1), world, player, block);
		if (world.getBlockState(new BlockPos(x, y - 1, z - 1)).getBlock() == block)
			cheinDestruction(new BlockPos(x, y - 1, z - 1), world, player, block);

		if (world.getBlockState(new BlockPos(x + 1, y, z)).getBlock() == block)
			cheinDestruction(new BlockPos(x + 1, y, z), world, player, block);
		if (world.getBlockState(new BlockPos(x + 1, y, z + 1)).getBlock() == block)
			cheinDestruction(new BlockPos(x + 1, y, z + 1), world, player, block);
		if (world.getBlockState(new BlockPos(x + 1, y, z - 1)).getBlock() == block)
			cheinDestruction(new BlockPos(x + 1, y, z - 1), world, player, block);

		if (world.getBlockState(new BlockPos(x + 1, y + 1, z)).getBlock() == block)
			cheinDestruction(new BlockPos(x + 1, y + 1, z), world, player, block);
		if (world.getBlockState(new BlockPos(x + 1, y + 1, z + 1)).getBlock() == block)
			cheinDestruction(new BlockPos(x + 1, y + 1, z + 1), world, player, block);
		if (world.getBlockState(new BlockPos(x + 1, y + 1, z - 1)).getBlock() == block)
			cheinDestruction(new BlockPos(x + 1, y + 1, z - 1), world, player, block);

		if (world.getBlockState(new BlockPos(x + 1, y - 1, z)).getBlock() == block)
			cheinDestruction(new BlockPos(x + 1, y - 1, z), world, player, block);
		if (world.getBlockState(new BlockPos(x + 1, y - 1, z + 1)).getBlock() == block)
			cheinDestruction(new BlockPos(x + 1, y - 1, z + 1), world, player, block);
		if (world.getBlockState(new BlockPos(x + 1, y - 1, z - 1)).getBlock() == block)
			cheinDestruction(new BlockPos(x + 1, y - 1, z - 1), world, player, block);

		if (world.getBlockState(new BlockPos(x - 1, y, z)).getBlock() == block)
			cheinDestruction(new BlockPos(x - 1, y, z), world, player, block);
		if (world.getBlockState(new BlockPos(x - 1, y, z + 1)).getBlock() == block)
			cheinDestruction(new BlockPos(x - 1, y, z + 1), world, player, block);
		if (world.getBlockState(new BlockPos(x - 1, y, z - 1)).getBlock() == block)
			cheinDestruction(new BlockPos(x - 1, y, z - 1), world, player, block);

		if (world.getBlockState(new BlockPos(x - 1, y + 1, z)).getBlock() == block)
			cheinDestruction(new BlockPos(x - 1, y + 1, z), world, player, block);
		if (world.getBlockState(new BlockPos(x - 1, y + 1, z + 1)).getBlock() == block)
			cheinDestruction(new BlockPos(x - 1, y + 1, z + 1), world, player, block);
		if (world.getBlockState(new BlockPos(x - 1, y + 1, z - 1)).getBlock() == block)
			cheinDestruction(new BlockPos(x - 1, y + 1, z - 1), world, player, block);

		if (world.getBlockState(new BlockPos(x - 1, y - 1, z)).getBlock() == block)
			cheinDestruction(new BlockPos(x - 1, y - 1, z), world, player, block);
		if (world.getBlockState(new BlockPos(x - 1, y - 1, z + 1)).getBlock() == block)
			cheinDestruction(new BlockPos(x - 1, y - 1, z + 1), world, player, block);
		if (world.getBlockState(new BlockPos(x - 1, y - 1, z - 1)).getBlock() == block)
			cheinDestruction(new BlockPos(x - 1, y - 1, z - 1), world, player, block);

		return;
	}

	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if (!isSelected)
			return;
		if (!(entityIn instanceof EntityLivingBase))
			return;
		List<Entity> entities = worldIn.loadedEntityList;
		for (Entity entity : entities) {
			if (entityIn.getDistanceToEntity(entity) < 25 && (entity instanceof EntityLivingBase)
					&& (entityIn != entity)) {
				((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.WITHER, 10, 10, true, true));
				((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.POISON, 10, 10, true, true));
				((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 10, 10, true, true));
				ParticleUtil.blockInjection(EnumParticleTypes.PORTAL, entity.worldObj, new BlockPos(0, 0, 0),
						entity.getPosition(), 5);
			}
		}
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
