package GlassRituals;

import java.util.List;
import java.util.Map;

import ExperienceApple.EAMain;
import Util.BlockAndPos;
import Util.ExperienceUtil;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GlassRitual {
	public static String getCanRitual(World world, BlockPos pos) {
		Map<String, List<BlockAndPos>> rituallist = GlassRitualRegister.glassritual;

		for (Map.Entry<String, List<BlockAndPos>> key : rituallist.entrySet()) {
			boolean flag = true;
			List<BlockAndPos> list = rituallist.get(key.getKey());
			for (BlockAndPos blockAndPos : list) {

				if (world.getBlockState(new BlockPos(pos.getX() + blockAndPos.x, pos.getY() + blockAndPos.y,
						pos.getZ() + blockAndPos.z)) == blockAndPos.blockstate) {

				} else {
					flag = false;
					break;
				}
			}
			if (flag) {
				removeRitual(key.getKey(), world, pos);
				return key.getKey();
			}

		}
		return null;
	}

	public static void startRitual(String key, World world, BlockPos pos) {

		if (world.isRemote)
			return;

		if (key == "Explosion") {
			world.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 5.0F, true);
		}

		if (key == "BlockRemove") {
			ItemStack itemstack = new ItemStack(
					Item.getItemFromBlock(
							world.getBlockState(new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ())).getBlock()));
			EntityItem itementity = new EntityItem(world, pos.getX(), (double) pos.getY() + 2, pos.getZ(),
					itemstack);
			world.spawnEntityInWorld(itementity);
			world.setBlockToAir(new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ()));
		}

		if (key == "SetWater") {
			ExperienceUtil.BlockPlaceOrDrop(pos.getX() + 1, pos.getY(), pos.getZ() + 1, Blocks.WATER, world);
			ExperienceUtil.BlockPlaceOrDrop(pos.getX() + 1, pos.getY(), pos.getZ() - 1, Blocks.WATER, world);
			ExperienceUtil.BlockPlaceOrDrop(pos.getX() + 1, pos.getY(), pos.getZ(), Blocks.WATER, world);
			ExperienceUtil.BlockPlaceOrDrop(pos.getX() - 1, pos.getY(), pos.getZ() + 1, Blocks.WATER, world);
			ExperienceUtil.BlockPlaceOrDrop(pos.getX() - 1, pos.getY(), pos.getZ() - 1, Blocks.WATER, world);
			ExperienceUtil.BlockPlaceOrDrop(pos.getX() - 1, pos.getY(), pos.getZ(), Blocks.WATER, world);
			ExperienceUtil.BlockPlaceOrDrop(pos.getX(), pos.getY(), pos.getZ() + 1, Blocks.WATER, world);
			ExperienceUtil.BlockPlaceOrDrop(pos.getX(), pos.getY(), pos.getZ() - 1, Blocks.WATER, world);
			ExperienceUtil.BlockPlaceOrDrop(pos.getX(), pos.getY(), pos.getZ(), Blocks.WATER, world);
		}

		if (key == "SetLava") {
			ExperienceUtil.BlockPlaceOrDrop(pos.getX() + 1, pos.getY(), pos.getZ() + 1, Blocks.LAVA, world);
			ExperienceUtil.BlockPlaceOrDrop(pos.getX() + 1, pos.getY(), pos.getZ() - 1, Blocks.LAVA, world);
			ExperienceUtil.BlockPlaceOrDrop(pos.getX() + 1, pos.getY(), pos.getZ(), Blocks.LAVA, world);
			ExperienceUtil.BlockPlaceOrDrop(pos.getX() - 1, pos.getY(), pos.getZ() + 1, Blocks.LAVA, world);
			ExperienceUtil.BlockPlaceOrDrop(pos.getX() - 1, pos.getY(), pos.getZ() - 1, Blocks.LAVA, world);
			ExperienceUtil.BlockPlaceOrDrop(pos.getX() - 1, pos.getY(), pos.getZ(), Blocks.LAVA, world);
			ExperienceUtil.BlockPlaceOrDrop(pos.getX(), pos.getY(), pos.getZ() + 1, Blocks.LAVA, world);
			ExperienceUtil.BlockPlaceOrDrop(pos.getX(), pos.getY(), pos.getZ() - 1, Blocks.LAVA, world);
			ExperienceUtil.BlockPlaceOrDrop(pos.getX(), pos.getY(), pos.getZ(), Blocks.LAVA, world);
		}
	}

	public static void removeRitual(String key, World world, BlockPos pos) {
		List<BlockAndPos> rituallist = GlassRitualRegister.glassritual.get(key);
		if (key == null) {
			return;
		}
		for (BlockAndPos blockAndPos : rituallist) {
			world.setBlockToAir(
					new BlockPos(pos.getX() + blockAndPos.x, pos.getY() + blockAndPos.y,
							pos.getZ() + blockAndPos.z));
			if (EAMain.particle == false) {
				for (int i = 0; i < 10; i++) {
					world.spawnParticle(EnumParticleTypes.FIREWORKS_SPARK,
							1 + pos.getX() + blockAndPos.x - Math.random(),
							1 + pos.getY() + blockAndPos.y - Math.random(),
							1 + pos.getZ() + blockAndPos.z - Math.random(),
							0.0D, 0.0D,
							0.0D);
				}
			}
		}
	}
}
