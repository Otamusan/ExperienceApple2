package Util;

import java.util.Random;

import ExperienceApple.EAMain;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ParticleUtil {

	public static void blockRemaining(EnumParticleTypes part, World world, BlockPos pos, int amount) {
		if (EAMain.particle)
			return;
		randomSquare(part, world, pos.getX(), pos.getY(), pos.getZ(), pos.getX() - 1, pos.getY() - 1, pos.getZ() - 1,
				amount);
	}

	public static void blockSurface(EnumParticleTypes part, World world, BlockPos pos, int amount) {
		if (EAMain.particle)
			return;
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		randomSquare(part, world, x + 0, y + 0, z + 0, x - 0, y - 1, z - 0, amount);
		randomSquare(part, world, x + 1, y + 0, z + 0, x + 1, y - 1, z - 0, amount);
		randomSquare(part, world, x + 0, y + 0, z + 1, x - 0, y - 1, z + 1, amount);
		randomSquare(part, world, x + 1, y + 0, z + 1, x + 1, y - 1, z + 1, amount);

		randomSquare(part, world, x + 0, y + 0, z + 0, x - 1, y + 0, z + 0, amount);
		randomSquare(part, world, x + 0, y + 0, z + 0, x + 0, y + 0, z - 1, amount);
		randomSquare(part, world, x + 1, y + 0, z + 1, x + 2, y + 0, z + 1, amount);
		randomSquare(part, world, x + 1, y + 0, z + 1, x + 1, y + 0, z + 2, amount);

		randomSquare(part, world, x + 0, y + 1, z + 0, x - 1, y + 1, z + 0, amount);
		randomSquare(part, world, x + 0, y + 1, z + 0, x + 0, y + 1, z - 1, amount);
		randomSquare(part, world, x + 1, y + 1, z + 1, x + 2, y + 1, z + 1, amount);
		randomSquare(part, world, x + 1, y + 1, z + 1, x + 1, y + 1, z + 2, amount);
	}

	public static void flow(EnumParticleTypes part, World world, double y, double sx, double sz, double ex, double ez,
			int amount) {
		if (EAMain.particle)
			return;

		for (int i = 0; i < amount; i++) {
			Random random = new Random();
			double x = sx + ((sx - ex) * random.nextDouble());
			double z = sz + ((sz - ez) * random.nextDouble());
			world.spawnParticle(part, x, y, z, 0.0D, -1.0D, 0.0D);
		}
	}

	public static void playerRemaining(EnumParticleTypes part, Entity entity, int amount) {
		if (EAMain.particle)
			return;

		randomSquare(part, entity.worldObj, entity.getPosition().getX(), entity.getPosition().getY(),
				entity.getPosition().getZ(), entity.getPosition().getX() - 1, entity.getPosition().getY() - 2,
				entity.getPosition().getZ() - 1, amount);
	}

	public static void randomSquare(EnumParticleTypes part, World world, double sx, double sy, double sz, double ex,
			double ey, double ez, int amount) {
		if (EAMain.particle)
			return;
		for (int i = 0; i < amount; i++) {
			Random random = new Random();
			double x = sx + ((sx - ex) * random.nextDouble());
			double y = sy + ((sy - ey) * random.nextDouble());
			double z = sz + ((sz - ez) * random.nextDouble());
			world.spawnParticle(part, x, y, z, 0.0D, 0.0D, 0.0D);
		}
	}

	public static void partofCircle(EnumParticleTypes part, World world, double x, double y, double z, double r,
			double angle) {
		if (EAMain.particle)
			return;
		double rad = angle * Math.PI / 180;
		double hx = Math.cos(rad) * r;
		double hy = Math.sin(rad) * r;
		world.spawnParticle(part, x + hx, y, z + hy, 0.0D, 0.0D, 0.0D);
	}

	public static void partofBall(EnumParticleTypes part, World world, double x, double y, double z, double r,
			double angle, double angle2) {
		if (EAMain.particle)
			return;
		double rad = angle * Math.PI / 180;
		double rad2 = angle2 * Math.PI / 180;

		double hx = Math.cos(rad) * r * Math.cos(rad2);
		double hz = Math.sin(rad) * r * Math.cos(rad2);
		double hy = Math.sin(rad2) * r;
		world.spawnParticle(part, x + hx, y + hy, z + hz, 0.0D, 0.0D, 0.0D);
	}

	public static void verticalCircle(EnumParticleTypes part, World world, double x, double y, double z, double r,
			int amount) {
		if (EAMain.particle)
			return;
		for (int i = 0; i < amount; i++) {
			partofCircle(part, world, x, y, z, r, new Random().nextFloat() * 360);
		}
	}

	public static void ball(EnumParticleTypes part, World world, double x, double y, double z, double r, int amount) {
		if (EAMain.particle)
			return;

		for (int k = 0; k < amount; k++) {
			partofBall(part, world, x, y, z, r, new Random().nextFloat() * 360, new Random().nextFloat() * 180 - 90);
		}

	}

	public static void blockInjection(EnumParticleTypes part, World world, BlockPos Supplier, BlockPos consumer,
			int amount) {
		/*
		 * Random random = new Random(); double sx = Supplier.getX() +
		 * random.nextFloat(); double sy = Supplier.getY() - 1 +
		 * random.nextFloat(); double sz = Supplier.getZ() + random.nextFloat();
		 * // world.spawnParticle(part, sx, sy, sz, 0, 0, 0); //
		 * ParticleUtil.blockSurface(EnumParticleTypes.VILLAGER_HAPPY, world, //
		 * Supplier, 1);
		 * 
		 * double ex = sx - consumer.getX(); double ey = sy - consumer.getY();
		 * double ez = sz - consumer.getZ(); //
		 * ParticleUtil.blockSurface(EnumParticleTypes.VILLAGER_HAPPY, world, //
		 * consumer, 1);
		 * 
		 * world.spawnParticle(part, sx, sy, sz, 0, 0, 0); //
		 * world.spawnParticle(part, sx, sy, sz, 0, 0, 0);
		 */
		for (int i = 0; i < amount; i++) {

			Random rnd = new Random();
			double rx = consumer.getX() + 0.5;
			double ry = consumer.getY() + 0.5;
			double rz = consumer.getZ() + 0.5;
			double sx = rnd.nextFloat() * 3 - 1.5;
			double sy = rnd.nextFloat() * 2 - 1.5;
			double sz = rnd.nextFloat() * 3 - 1.5;
			world.spawnParticle(part, rx, ry, rz, sx, sy, sz);
		}
	}

}
