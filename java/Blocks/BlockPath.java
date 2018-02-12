package Blocks;

import java.util.Random;

import Util.ParticleUtil;
import net.minecraft.block.BlockGrassPath;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockPath extends BlockGrassPath {
	public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
		((EntityLivingBase) entityIn)
				.addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation("speed"), 20, 2, true, false));
	}

	@Override
	public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rnd) {
		ParticleUtil.flow(EnumParticleTypes.ENCHANTMENT_TABLE, world, pos.getY() + 2, pos.getX(), pos.getZ(),
				pos.getX() - 1, pos.getZ() - 1, 1);
	}
}
