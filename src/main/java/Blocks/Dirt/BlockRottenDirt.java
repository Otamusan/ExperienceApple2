package Blocks.Dirt;

import java.lang.reflect.InvocationTargetException;
import java.util.Random;

import Client.Particle.EAParticleFunc;
import Client.Particle.EAParticleFuncs;
import Client.Particle.PF;
import Client.Particle.PS;
import Util.Vec.Vec;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
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
		EAParticleFuncs funcs = new EAParticleFuncs(new EAParticleFunc(40, 0, 0, 0, PF.setBlink(2),
				PF.setColor(rnd.nextFloat(), rnd.nextFloat(), rnd.nextFloat(), rnd.nextFloat()),
				PF.setMotion(0, 0.001, 0)), 1, PS.randomSquare(1, 0, 1));
		funcs.spawn(world, new Vec(pos.getX(), pos.getY() + 1, pos.getZ()));
	}
}