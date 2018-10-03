package World;

import java.util.Random;

import ExperienceApple.Register.BlockRegister;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldProviderSurface;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

public class ExperienceOreGenerator implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		if (world.provider instanceof WorldProviderSurface) {
			generateOre(world, random, chunkX << 4, chunkZ << 4);
		}
	}

	private void generateOre(World world, Random random, int x, int z) {
		for (int i = 0; i < 1; i++) {
			int genX = x + random.nextInt(16);
			int genY = 1 + random.nextInt(254);
			int genZ = z + random.nextInt(16);
			new WorldGenMinable(BlockRegister.experienceOre.getDefaultState(), 40).generate(world, random,
					new BlockPos(genX, genY, genZ));
		}
	}
}
