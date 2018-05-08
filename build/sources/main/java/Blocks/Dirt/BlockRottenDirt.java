package Blocks.Dirt;

import java.lang.reflect.InvocationTargetException;
import java.util.Random;

import Util.ParticleUtil;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.Biome;

public class BlockRottenDirt extends BlockEADirt {
	public BlockRottenDirt() {
		this.setTickRandomly(true);
	}

	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if (worldIn.isRemote)
			return;
		Biome.SpawnListEntry spawnListEntry = ((WorldServer) worldIn)
				.getSpawnListEntryForTypeAt(EnumCreatureType.MONSTER, pos);
		try {
			EntityLiving entity = (EntityLiving) spawnListEntry.entityClass.getConstructor(new Class[] { World.class })
					.newInstance(worldIn);
			entity.setPosition(pos.getX() + .5, pos.getY() + 2, pos.getZ() + .5);
			worldIn.spawnEntity(entity);
		} catch (InstantiationException | IllegalAccessException | NoSuchMethodException
				| InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rnd) {
		ParticleUtil.blockRemaining(EnumParticleTypes.SMOKE_NORMAL, world, pos, 1);
	}
}