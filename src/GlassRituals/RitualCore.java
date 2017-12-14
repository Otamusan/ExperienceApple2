package GlassRituals;

import ExperienceApple.EAMain;
import ExperienceApple.Register.BlockRegister;
import Util.ExperienceUtil;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RitualCore {
	public static int ritualScan(World world, int x, int y, int z) {
		int count[] = new int[100];

		for (int in = 0; in < RitualLocateData.ritualAmount; in++) {
			outside: for (int ix = 0; ix < 3; ix++) {
				for (int iy = 0; iy < 3; iy++) {
					for (int iz = 0; iz < 3; iz++) {
						IBlockState blockstate = world.getBlockState(new BlockPos(x + ix - 1, y + iy - 1, z + iz - 1));
						Block Rblock = blockstate.getBlock();
						Block Dblock = RitualLocateData.Data(in, ix, iy, iz);

						if (Rblock == Dblock || (Dblock == null
								&& (Rblock != BlockRegister.ritualGlassTier1)
								&& (Rblock != BlockRegister.ritualGlassTier2)
								&& (Rblock != BlockRegister.ritualGlassTier3)
								&& (Rblock != BlockRegister.ritualGlassTier4))) {
							count[in]++;
						} else {
							break outside;
						}
					}
				}
			}
			if (count[in] == 27) {
				return in;
			}
		}
		return 114514;
	}

	public static boolean ritualActive(EntityPlayer player, World world, int x, int y, int z) {

		int n = ritualScan(world, x, y, z);

		if (n != 114514)
			ritualRemove(x, y, z, n, world, player);

		switch (n) {
		case 0:
			world.createExplosion(null, x, y, z, 5.0F, true);
			break;
		case 1:
			if (!world.isRemote) {
				ItemStack itemstack = new ItemStack(
						Item.getItemFromBlock(world.getBlockState(new BlockPos(x, y - 1, z)).getBlock()));
				EntityItem itementity = new EntityItem(world, x, (double) y + 2, z, itemstack);
				world.spawnEntityInWorld(itementity);
				world.setBlockToAir(new BlockPos(x, y - 1, z));
			}
			break;
		case 2:
			ExperienceUtil.BlockPlaceOrDrop(x + 1, y, z + 1, Blocks.WATER, world);
			ExperienceUtil.BlockPlaceOrDrop(x + 1, y, z - 1, Blocks.WATER, world);
			ExperienceUtil.BlockPlaceOrDrop(x + 1, y, z, Blocks.WATER, world);
			ExperienceUtil.BlockPlaceOrDrop(x - 1, y, z + 1, Blocks.WATER, world);
			ExperienceUtil.BlockPlaceOrDrop(x - 1, y, z - 1, Blocks.WATER, world);
			ExperienceUtil.BlockPlaceOrDrop(x - 1, y, z, Blocks.WATER, world);
			ExperienceUtil.BlockPlaceOrDrop(x, y, z + 1, Blocks.WATER, world);
			ExperienceUtil.BlockPlaceOrDrop(x, y, z - 1, Blocks.WATER, world);
			ExperienceUtil.BlockPlaceOrDrop(x, y, z, Blocks.WATER, world);
			break;
		case 3:
			ExperienceUtil.BlockPlaceOrDrop(x + 1, y, z + 1, Blocks.LAVA, world);
			ExperienceUtil.BlockPlaceOrDrop(x + 1, y, z - 1, Blocks.LAVA, world);
			ExperienceUtil.BlockPlaceOrDrop(x + 1, y, z, Blocks.LAVA, world);
			ExperienceUtil.BlockPlaceOrDrop(x - 1, y, z + 1, Blocks.LAVA, world);
			ExperienceUtil.BlockPlaceOrDrop(x - 1, y, z - 1, Blocks.LAVA, world);
			ExperienceUtil.BlockPlaceOrDrop(x - 1, y, z, Blocks.LAVA, world);
			ExperienceUtil.BlockPlaceOrDrop(x, y, z + 1, Blocks.LAVA, world);
			ExperienceUtil.BlockPlaceOrDrop(x, y, z - 1, Blocks.LAVA, world);
			ExperienceUtil.BlockPlaceOrDrop(x, y, z, Blocks.LAVA, world);
			break;
		case 4:
			ExperienceUtil.BlockPlaceOrDrop(x, y, z, BlockRegister.experienceIronBlock, world);
			break;
		case 5:
			for (int ix = -6; ix < 6; ix++) {
				for (int iy = -6; iy < 6; iy++) {
					for (int iz = -6; iz < 6; iz++) {
						IBlockState blockState = world.getBlockState(new BlockPos(ix + x, iy + y, iz + z));
						world.setBlockToAir(new BlockPos(ix + x, iy + y, iz + z));
						EntityFallingBlock entityFallingBlock = new EntityFallingBlock(world, ix + x, iy + y, iz + z,
								blockState);
						entityFallingBlock.worldObj.spawnEntityInWorld(entityFallingBlock);
					}
				}
			}
		}

		return false;
	}

	public static void ritualRemove(int x, int y, int z, int n, World world, EntityPlayer player) {
		for (int ix = 0; ix < 3; ix++) {
			for (int iy = 0; iy < 3; iy++) {
				for (int iz = 0; iz < 3; iz++) {
					IBlockState blockstate = world.getBlockState(new BlockPos(x + ix - 1, y + iy - 1, z + iz - 1));
					Block Rblock = blockstate.getBlock();
					if (Rblock == BlockRegister.ritualGlassTier1 || Rblock == BlockRegister.ritualGlassTier2
							|| Rblock == BlockRegister.ritualGlassTier3 || Rblock == BlockRegister.ritualGlassTier4
							|| Rblock == RitualLocateData.Data(n, ix, iy, iz)) {
						world.setBlockToAir(new BlockPos(x + ix - 1, y + iy - 1, z + iz - 1));
						if (Rblock == RitualLocateData.Data(n, ix, iy, iz)) {
							if (EAMain.particle == false && RitualLocateData.Data(n, ix, iy, iz) != Blocks.AIR) {
								for (int i = 0; i < 10; i++) {
									world.spawnParticle(EnumParticleTypes.FIREWORKS_SPARK, x + ix - Math.random(),
											y + iy - Math.random(), z + iz - Math.random(), 0.0D, 0.0D, 0.0D);

								}
							}
						}
					}
				}
			}
		}
	}
}
