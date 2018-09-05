package Item.Tools;

import java.util.List;
import java.util.Set;

import com.google.common.collect.Sets;

import Item.ExperienceRepair;
import Item.IExperienceRepair;
import Util.ParticleUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLog;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemExperienceIronAxe extends ItemTool implements IExperienceRepair {

	int cooldown = 60;
	int cost = 5;
	public int range = 3;

	private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(new Block[] { Blocks.PLANKS, Blocks.BOOKSHELF,
			Blocks.LOG, Blocks.LOG2, Blocks.CHEST, Blocks.PUMPKIN, Blocks.LIT_PUMPKIN, Blocks.MELON_BLOCK,
			Blocks.LADDER, Blocks.WOODEN_BUTTON, Blocks.WOODEN_PRESSURE_PLATE });

	private ExperienceRepair experienceRepair;

	public ItemExperienceIronAxe(ToolMaterial mate, int cooltime, int cost) {
		super(mate, EFFECTIVE_ON);
		this.attackDamage = mate.getAttackDamage();
		this.attackSpeed = -3.2F;
		this.experienceRepair = new ExperienceRepair(cooltime, cost);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(I18n.format("item.experienceIron.cooltime") + " : " + this.experienceRepair.getCooltime());
		tooltip.add(I18n.format("item.experienceIron.cost") + " : " + this.experienceRepair.getCost());
	}

	@Override
	public float getDestroySpeed(ItemStack stack, IBlockState state) {
		Material material = state.getMaterial();
		return material != Material.WOOD && material != Material.PLANTS && material != Material.VINE
				? super.getDestroySpeed(stack, state) : this.efficiency;
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, IBlockState state, BlockPos pos,
			EntityLivingBase entityLiving) {
		ParticleUtil.blockRemaining(EnumParticleTypes.VILLAGER_HAPPY, world, pos, 5);

		if (!entityLiving.isSneaking())
			return false;
		if (!(world.getBlockState(pos).getBlock() instanceof BlockLog)
				&& !(world.getBlockState(pos).getBlock() instanceof BlockLeaves))
			return false;
		cheinDestruction(pos, world, (EntityPlayer) entityLiving, world.getBlockState(pos).getBlock());
		if (!world.isRemote && state.getBlockHardness(world, pos) != 0.0D) {
			stack.damageItem(1, entityLiving);
		}
		world.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE,
				SoundCategory.PLAYERS, 1, 0, true);

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
		this.experienceRepair.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
		ParticleUtil.verticalCircle(EnumParticleTypes.VILLAGER_HAPPY, entity.getEntityWorld(), entity.posX,
				entity.posY + entity.getMaxFallHeight() / 2, entity.posZ, 1, 12);
		return false;
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
