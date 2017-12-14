package Util;

import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class ParticleUtil {
	public static void verticalCircle(EnumParticleTypes part, World world, double x, double y, double z, double r,
			int amount) {
		for (int i = 0; i <= amount; i++) {
			double angle = (360 / amount) * i;
			double rad = angle * Math.PI / 180;
			double hx = Math.cos(rad) * r;
			double hy = Math.sin(rad) * r;
			world.spawnParticle(part, x + hx, y, z + hy, 0.0D, 0.0D, 0.0D);
		}
	}
}
