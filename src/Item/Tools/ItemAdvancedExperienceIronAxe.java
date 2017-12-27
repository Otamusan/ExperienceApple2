package Item.Tools;

import java.util.Set;

import com.google.common.collect.Sets;

import Util.ParticleUtil;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemAdvancedExperienceIronAxe extends ItemTool {

	public static int cooldown = 0;

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
