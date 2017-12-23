package Util;

import java.util.Random;

import ExperienceApple.EAMain;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ParticleUtil {
	public static void verticalCircle(EnumParticleTypes part, World world, double x, double y, double z, double r,
			int amount) {
		if (EAMain.particle)
			return;
		for (int i = 0; i <= amount; i++) {
			double angle = (360 / amount) * i;
			double rad = angle * Math.PI / 180;
			double hx = Math.cos(rad) * r;
			double hy = Math.sin(rad) * r;
			world.spawnParticle(part, x + hx, y, z + hy, 0.0D, 0.0D, 0.0D);
		}
	}

	public static void blockRemaining(EnumParticleTypes part, World world, BlockPos pos, int amount) {
		random(part, world, pos.getX(), pos.getY(), pos.getZ(), pos.getX() - 1, pos.getY() - 1, pos.getZ() - 1, amount);
	}

	public static void playerRemaining(EnumParticleTypes part, Entity entity, int amount) {
		if (EAMain.particle)
			return;

		random(part, entity.worldObj, entity.getPosition().getX(), entity.getPosition().getY(),
				entity.getPosition().getZ(), entity.getPosition().getX() - 1, entity.getPosition().getY() - 2,
				entity.getPosition().getZ() - 1, amount);
	}

	public static void random(EnumParticleTypes part, World world, double sx, double sy, double sz, double ex,
			double ey, double ez, int amount) {
		if (EAMain.particle)
			return;
		for (int i = 0; i < amount; i++) {
			Random random = new Random();
			double x = sx + ((sx - ex) * random.nextDouble());
			double y = sy + ((sy - ey) * random.nextDouble());
			double z = sz + ((sz - ez) * random.nextDouble());
			world.spawnParticle(EnumParticleTypes.VILLAGER_HAPPY, x, y, z, 0.0D, 0.0D, 0.0D);
		}
	}

}
