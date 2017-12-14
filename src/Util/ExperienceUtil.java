package Util;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ExperienceUtil {
	public static boolean experiencePull(EntityPlayer player, int n, World world) {
		if (player != null && !world.isRemote) {
			for (int i = 0; i < n; i++) {

				if (player.experience > 0) {
					player.experience -= 1F / player.xpBarCap();
				} else {
					player.experienceLevel--;
					player.experience = 1;
				}

				player.experienceLevel--;
				player.addExperienceLevel(1);
			}
		} else {
			return false;
		}
		return true;
	}

	public static double getExperiencePoints(EntityPlayer player) {
		double exp = 0;
		for (int i = 0; i < player.experienceLevel; i++) {
			exp = exp + (i >= 30 ? 112 + (i - 30) * 9 : (i >= 15 ? 37 + (i - 15) * 5 : 7 + i * 2));
		}
		exp = exp + player.experience * player.xpBarCap();
		return Math.round(exp);
	}

	public static Boolean BlockPlaceOrDrop(int x, int y, int z, Block block, World world) {
		if (world.getBlockState(new BlockPos(x, y, z)).getBlock() != Blocks.AIR) {
			if (!world.isRemote) {
				ItemStack itemstack3 = new ItemStack(Item.getItemFromBlock(block));
				EntityItem itementity3 = new EntityItem(world, x, (double) y - 1, z, itemstack3);
				world.spawnEntityInWorld(itementity3);
			}
		} else {
			world.setBlockState(new BlockPos(x, y, z), block.getBlockState().getBaseState());
		}
		return null;
	}
}
