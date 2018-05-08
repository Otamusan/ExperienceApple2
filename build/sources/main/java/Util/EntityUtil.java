package Util;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityUtil {
	public static Position3D getRotation(Entity entity) {
		Double f = (double) (-MathHelper.sin(entity.rotationYaw * 0.017453292F)
				* MathHelper.cos(entity.rotationPitch * 0.017453292F));
		Double f1 = (double) -MathHelper.sin((entity.rotationPitch) * 0.017453292F);
		Double f2 = (double) (MathHelper.cos(entity.rotationYaw * 0.017453292F)
				* MathHelper.cos(entity.rotationPitch * 0.017453292F));
		return new Position3D(f, f1, f2);
	}

	public static EntityVillager getEntityItemListFromPos(World world, BlockPos pos) {
		List<Entity> entitylist = world.loadedEntityList;
		for (Entity entity : entitylist) {
			if (entity.getClass() == EntityVillager.class) {
				EntityVillager villager = (EntityVillager) entity;

				if (entity.getPosition().getX() == pos.getX() && entity.getPosition().getY() == pos.getY()
						&& entity.getPosition().getZ() == pos.getZ()) {
					return villager;
				}
			}
		}

		return null;
	}
}
