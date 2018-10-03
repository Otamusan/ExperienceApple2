package Rituals.Rituals;

import java.util.Random;

import Rituals.StonePosData;
import Util.ExperienceUtil;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RitualCreateWater extends Ritual {

	public RitualCreateWater(StonePosData posData, String name) {
		super(posData, name);
	}

	@Override
	public void activate(EntityPlayer player, World world, BlockPos pos) {
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		world.playSound(x, y, z, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1, 1, true);
		for (int i = 0; i < 100; i++) {
			Random r = new Random();
			System.out.println(world.isRemote);
			world.spawnParticle(EnumParticleTypes.BLOCK_CRACK, pos.getX() + r.nextFloat() * 3 - 1,
					pos.getY() + r.nextFloat(), pos.getZ() + r.nextFloat() * 3 - 1, 0, 0, 0,
					Block.getStateId(Blocks.WATER.getDefaultState()));
		}
		ExperienceUtil.BlockPlaceOrDrop(x + 1, y, z + 1, Blocks.WATER, world);
		ExperienceUtil.BlockPlaceOrDrop(x + 1, y, z - 1, Blocks.WATER, world);
		ExperienceUtil.BlockPlaceOrDrop(x + 1, y, z, Blocks.WATER, world);
		ExperienceUtil.BlockPlaceOrDrop(x - 1, y, z + 1, Blocks.WATER, world);
		ExperienceUtil.BlockPlaceOrDrop(x - 1, y, z - 1, Blocks.WATER, world);
		ExperienceUtil.BlockPlaceOrDrop(x - 1, y, z, Blocks.WATER, world);
		ExperienceUtil.BlockPlaceOrDrop(x, y, z + 1, Blocks.WATER, world);
		ExperienceUtil.BlockPlaceOrDrop(x, y, z - 1, Blocks.WATER, world);
		ExperienceUtil.BlockPlaceOrDrop(x, y, z, Blocks.WATER, world);
	}

	@Override
	public boolean canActivate(EntityPlayer player, World world, BlockPos pos) {
		return true;
	}

}
