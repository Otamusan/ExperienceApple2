package Item.Tools;

import java.util.List;

import ExperienceApple.Register.BlockRegister;
import Item.ExperienceRepair;
import Item.IExperienceRepair;
import Util.ParticleUtil;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemExperienceIronShovel extends ItemSpade implements IExperienceRepair {
	private ExperienceRepair experienceRepair;

	public ItemExperienceIronShovel(ToolMaterial mate, int cooltime, int cost) {
		super(mate);
		this.experienceRepair = new ExperienceRepair(cooltime, cost);
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, IBlockState state, BlockPos pos,
			EntityLivingBase entityLiving) {
		ParticleUtil.blockRemaining(EnumParticleTypes.VILLAGER_HAPPY, world, pos, 10);

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

	@SideOnly(Side.CLIENT)

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(I18n.format("item.experienceIron.cooltime") + " : " + this.experienceRepair.getCooltime());
		tooltip.add(I18n.format("item.experienceIron.cost") + " : " + this.experienceRepair.getCost());

	}

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entity, int itemSlot, boolean isSelected) {
		this.experienceRepair.onUpdate(stack, worldIn, entity, itemSlot, isSelected);
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack stack = playerIn.getHeldItem(hand);
		if (!playerIn.canPlayerEdit(pos.offset(facing), facing, stack)) {
			return EnumActionResult.FAIL;
		} else {
			IBlockState iblockstate = worldIn.getBlockState(pos);
			Block block = iblockstate.getBlock();

			if (facing != EnumFacing.DOWN && worldIn.getBlockState(pos.up()).getMaterial() == Material.AIR
					&& ((block == Blocks.GRASS) || (block == Blocks.GRASS_PATH) || (block == Blocks.DIRT))) {
				IBlockState iblockstate1 = BlockRegister.path.getDefaultState();
				worldIn.playSound(playerIn, pos, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0F, 1.0F);

				if (!worldIn.isRemote) {
					worldIn.setBlockState(pos, iblockstate1, 11);
					stack.damageItem(1, playerIn);
				}

				return EnumActionResult.SUCCESS;
			} else if (facing != EnumFacing.DOWN && worldIn.getBlockState(pos.up()).getMaterial() == Material.AIR
					&& block == BlockRegister.path) {
				IBlockState iblockstate1 = BlockRegister.enrichedFarmLand.getDefaultState();
				worldIn.playSound(playerIn, pos, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0F, 1.0F);

				if (!worldIn.isRemote) {
					worldIn.setBlockState(pos, iblockstate1, 11);
					stack.damageItem(5, playerIn);
				}
			} else if (facing != EnumFacing.DOWN && worldIn.getBlockState(pos.up()).getMaterial() == Material.AIR
					&& block == BlockRegister.enrichedFarmLand) {
				IBlockState iblockstate1 = BlockRegister.rottenDirt.getDefaultState();
				worldIn.playSound(playerIn, pos, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0F, 1.0F);

				if (!worldIn.isRemote) {
					worldIn.setBlockState(pos, iblockstate1, 11);
					stack.damageItem(10, playerIn);
				}
			}
		}
		return EnumActionResult.PASS;

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
