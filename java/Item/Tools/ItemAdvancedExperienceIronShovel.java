package Item.Tools;

import java.util.ArrayList;
import java.util.List;

import ExperienceApple.ITooltip;
import ExperienceApple.Register.BlockRegister;
import Util.ParticleUtil;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
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

	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
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
