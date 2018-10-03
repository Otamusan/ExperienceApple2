package Item.Tools;

import Client.Particle.EAParticleFunc;
import Client.Particle.EAParticleFuncs;
import Client.Particle.PF;
import Client.Particle.PS;
import ExperienceApple.Register.BlockRegister;
import Util.Vec.Vec;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemAdvancedExperienceIronShovel extends ItemSpade {
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
		EAParticleFuncs funcs = new EAParticleFuncs(
				new EAParticleFunc(20, 0, 0, 0, PF.setBlink(1), PF.setColor(1, 1, 1, 0.7f), PF.setGravity(0.02)), 10,
				PS.randomBall(0.3), PS.spread(0.5, Vec.getZero()));
		funcs.spawn(world, new Vec(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5));
		if (!world.isRemote && state.getBlockHardness(world, pos) != 0.0D) {
			stack.damageItem(1, entityLiving);
		}

		return false;
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
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
		EAParticleFuncs funcs = new EAParticleFuncs(
				new EAParticleFunc(20, 0, 0, 0, PF.setBlink(1), PF.setColor(1, 1, 1, 0.7f), PF.setGravity(0.02)), 10,
				PS.randomBall(0.3), PS.spread(0.5, Vec.getZero()));
		funcs.spawn(entity.world, new Vec(entity.posX, entity.posY, entity.posZ));
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		return true;
	}

}
