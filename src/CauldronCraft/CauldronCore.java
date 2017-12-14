package CauldronCraft;

import java.util.List;

import ExperienceApple.EAMain;
import ExperienceApple.Register.BlockRegister;
import ExperienceApple.Register.ItemRegister;
import Util.EntityItemUtil;
import Util.EntityUtil;
import Util.ItemList;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CauldronCore {
	public static int ritualScan(World world, int x, int y, int z, ItemStack item) {
		int count[] = new int[100];

		for (int in = 0; in < CauldronLocateData.ritualAmount; in++) {
			outside: for (int ix = 0; ix < 3; ix++) {
				for (int iy = 0; iy < 3; iy++) {
					for (int iz = 0; iz < 3; iz++) {
						IBlockState blockstate = world
								.getBlockState(new BlockPos(x + ix - 1, y + iy - 1, z + iz - 1));
						Block Rblock = blockstate.getBlock();
						Block Dblock = CauldronLocateData.Data(in, ix, iy, iz);

						if (Rblock == Dblock || (Dblock == null
								&& (Rblock != BlockRegister.ritualStoneTier1)
								&& (Rblock != BlockRegister.ritualStoneTier2)
								&& (Rblock != BlockRegister.ritualStoneTier3)
								&& (Rblock != BlockRegister.ritualStoneTier4)
								&& (Rblock != Blocks.CAULDRON))) {
							count[in]++;
						} else {
							break outside;
						}
					}
				}
			}
			if (count[in] == 27) {
				//if (Enchantment.getEnchantmentByID(item.getEnchantmentTagList().getCompoundTagAt(0)
				//		.getInteger("id")) == CauldronLocateData.enchant[in]) {
				ItemList list = EntityItemUtil.getItemListFromPos(world, new BlockPos(x, y, z));

				if (ItemList.isItemListEqual(CauldronLocateData.craftItemList[in].itemlist, list.itemlist)) {
					return in;
				}
				//}
			}

		}
		return 114514;

	}

	public static boolean ritualActive(World world, int x, int y, int z, ItemStack item) {

		int n = ritualScan(world, x, y, z, item);
		if (n == 114514) {
			return false;
		}

		switch (n) {
		case 0:
			if (!world.isRemote) {
				world.spawnEntityInWorld(new EntityItem(world, x + 0.5, y + 0.5, z + 0.5,
						new ItemStack(BlockRegister.cabinetStone)));
			}
			break;
		case 1:
			if (!world.isRemote) {
				world.spawnEntityInWorld(new EntityItem(world, x + 0.5, y + 0.5, z + 0.5,
						new ItemStack(BlockRegister.ritualStoneTier1)));
			}
			break;
		case 2:
			if (!world.isRemote) {
				world.spawnEntityInWorld(new EntityItem(world, x + 0.5, y + 0.5, z + 0.5,
						new ItemStack(BlockRegister.ritualStoneTier2)));
			}
			break;
		case 3:
			if (!world.isRemote) {
				world.spawnEntityInWorld(new EntityItem(world, x + 0.5, y + 0.5, z + 0.5,
						new ItemStack(BlockRegister.ritualStoneTier3)));
			}
			break;
		case 4:
			if (!world.isRemote) {
				world.spawnEntityInWorld(new EntityItem(world, x + 0.5, y + 0.5, z + 0.5,
						new ItemStack(BlockRegister.ritualStoneTier4)));
			}
			break;
		case 5:
			if (!world.isRemote) {
				world.spawnEntityInWorld(new EntityItem(world, x + 0.5, y + 0.5, z + 0.5,
						new ItemStack(BlockRegister.pureExperienceBlock)));
			}
			break;
		case 6:
			if (!world.isRemote) {
				world.spawnEntityInWorld(new EntityItem(world, x + 0.5, y + 0.5, z + 0.5,
						new ItemStack(ItemRegister.flyingSpellPaper)));
			}
			break;
		case 7:
			if (!world.isRemote) {
				world.spawnEntityInWorld(new EntityItem(world, x + 0.5, y + 0.5, z + 0.5,
						new ItemStack(ItemRegister.satietySpellPaper)));
			}
			break;

		case 8:
			EntityVillager villager = EntityUtil.getEntityItemListFromPos(world, new BlockPos(x, y, z));

			if (villager != null) {
				if (!world.isRemote) {
					world.spawnEntityInWorld(new EntityItem(world, x + 0.5, y + 0.5, z + 0.5,
							new ItemStack(ItemRegister.fragmentOfTheBrain)));
					villager.setDead();
				}
			} else {
				return false;
			}

			break;
		case 9:
			if (!world.isRemote) {
				world.spawnEntityInWorld(new EntityItem(world, x + 0.5, y + 0.5, z + 0.5,
						new ItemStack(BlockRegister.advancedAccelerateStone)));
			}
			break;
		case 10:
			if (!world.isRemote) {
				world.spawnEntityInWorld(new EntityItem(world, x + 0.5, y + 0.5, z + 0.5,
						new ItemStack(BlockRegister.advancedGrowthStone)));
			}
			break;
		}
		ritualRemove(x, y, z, n, world);

		return false;

	}

	public static void ritualRemove(int x, int y, int z, int n, World world) {
		List<EntityItem> entityitemlist = EntityItemUtil.getEntityItemListFromPos(world, new BlockPos(x, y, z));
		for (EntityItem entityItem : entityitemlist) {
			if (entityItem.getEntityItem().getItem() != ItemRegister.enchantmentPearl
					&& CauldronLocateData.craftItemList[n].getItemList()
							.contains(entityItem.getEntityItem().getItem())) {
				entityItem.setDead();
			}
		}
		for (int ix = 0; ix < 3; ix++) {
			for (int iy = 0; iy < 3; iy++) {
				for (int iz = 0; iz < 3; iz++) {
					IBlockState blockstate = world.getBlockState(new BlockPos(x + ix - 1, y + iy - 1, z + iz - 1));
					Block Rblock = blockstate.getBlock();
					if (Rblock == BlockRegister.ritualGlassTier1 || Rblock == BlockRegister.ritualGlassTier2
							|| Rblock == BlockRegister.ritualGlassTier3 || Rblock == BlockRegister.ritualGlassTier4
							|| Rblock == CauldronLocateData.Data(n, ix, iy, iz)) {
						if (Rblock == CauldronLocateData.Data(n, ix, iy, iz)) {
							if (EAMain.particle == false && CauldronLocateData.Data(n, ix, iy, iz) != Blocks.AIR) {
								for (int i = 0; i < 10; i++) {
									world.spawnParticle(EnumParticleTypes.FIREWORKS_SPARK, x + ix - Math.random(),
											y + iy - Math.random(), z + iz - Math.random(), 0.0D, 0.0D, 0.0D);

								}
							}
						}
					}
				}
			}
			if (EAMain.particle == false) {

				for (int i = 0; i < 10; i++) {
					world.spawnParticle(EnumParticleTypes.LAVA, x + 0.5, y + 0.5, z + 0.5, 0, 0, 0);

				}
			}
		}
		world.playSound(x, y, z, SoundEvents.ENTITY_FIREWORK_LAUNCH, SoundCategory.BLOCKS, 3, 1, false);
	}
}
