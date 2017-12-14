package CauldronCraft;

import java.util.ArrayList;
import java.util.Arrays;

import ExperienceApple.Register.BlockRegister;
import ExperienceApple.Register.ItemRegister;
import Util.ItemList;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class CauldronLocateData {
	public static final int ritualAmount = 30;
	public static final int ritualsize = 4;
	public static final Block ritualLocate[][][][] = new Block[ritualAmount][ritualsize - 1][ritualsize - 1][ritualsize
			- 1];
	public static Enchantment enchant[] = new Enchantment[ritualAmount];
	public static ItemList craftItemList[] = new ItemList[ritualAmount];

	public static Block Data(int n, int x, int y, int z) {

		for (int in = 0; in < 3; in++) {
			for (int ix = 0; ix < 3; ix++) {
				for (int iy = 0; iy < 3; iy++) {
					for (int iz = 0; iz < 3; iz++) {
						ritualLocate[in][ix][iy][iz] = null;
					}
				}
			}
		}

		ItemStack enchaPearlMending = new ItemStack(ItemRegister.enchantmentPearl);
		enchaPearlMending.addEnchantment(Enchantments.MENDING, 1);

		ItemStack enchaPearlEfficiency1 = new ItemStack(ItemRegister.enchantmentPearl);
		enchaPearlEfficiency1.addEnchantment(Enchantments.EFFICIENCY, 1);

		ItemStack enchaPearlEfficiency5 = new ItemStack(ItemRegister.enchantmentPearl);
		enchaPearlEfficiency5.addEnchantment(Enchantments.EFFICIENCY, 5);

		ItemStack enchaPearlSilkTouch = new ItemStack(ItemRegister.enchantmentPearl);
		enchaPearlSilkTouch.addEnchantment(Enchantments.SILK_TOUCH, 1);

		ItemStack enchaPearlInfinity = new ItemStack(ItemRegister.enchantmentPearl);
		enchaPearlInfinity.addEnchantment(Enchantments.INFINITY, 1);

		ItemStack enchaPearlLooting3 = new ItemStack(ItemRegister.enchantmentPearl);
		enchaPearlLooting3.addEnchantment(Enchantments.LOOTING, 3);

		craftItemList[0] = new ItemList(new ArrayList<ItemStack>(Arrays.asList(
				new ItemStack(Blocks.STONE, 16),
				new ItemStack(ItemRegister.experienceApple, 1),
				new ItemStack(BlockRegister.condensedExperienceOre, 1),
				enchaPearlMending)));
		ritualLocate[0][1][1][1] = Blocks.CAULDRON;

		craftItemList[1] = new ItemList(new ArrayList<ItemStack>(Arrays.asList(
				new ItemStack(BlockRegister.cabinetStone, 1),
				new ItemStack(BlockRegister.ritualGlassTier1, 16),
				enchaPearlEfficiency1)));
		ritualLocate[1][0][1][1] = BlockRegister.cabinetStone;
		ritualLocate[1][1][1][0] = BlockRegister.cabinetStone;
		ritualLocate[1][1][1][1] = Blocks.CAULDRON;
		ritualLocate[1][1][1][2] = BlockRegister.cabinetStone;
		ritualLocate[1][2][1][1] = BlockRegister.cabinetStone;

		craftItemList[2] = new ItemList(new ArrayList<ItemStack>(Arrays.asList(
				new ItemStack(BlockRegister.cabinetStone, 1),
				new ItemStack(BlockRegister.ritualGlassTier2, 16),
				enchaPearlEfficiency1)));
		ritualLocate[2][0][1][1] = BlockRegister.ritualStoneTier1;
		ritualLocate[2][1][1][0] = BlockRegister.ritualStoneTier1;
		ritualLocate[2][1][1][1] = Blocks.CAULDRON;
		ritualLocate[2][1][1][2] = BlockRegister.ritualStoneTier1;
		ritualLocate[2][2][1][1] = BlockRegister.ritualStoneTier1;

		craftItemList[3] = new ItemList(new ArrayList<ItemStack>(Arrays.asList(
				new ItemStack(BlockRegister.cabinetStone, 1),
				new ItemStack(BlockRegister.ritualGlassTier3, 16),
				enchaPearlEfficiency1)));
		ritualLocate[3][0][1][1] = BlockRegister.ritualStoneTier2;
		ritualLocate[3][1][1][0] = BlockRegister.ritualStoneTier2;
		ritualLocate[3][1][1][1] = Blocks.CAULDRON;
		ritualLocate[3][1][1][2] = BlockRegister.ritualStoneTier2;
		ritualLocate[3][2][1][1] = BlockRegister.ritualStoneTier2;

		craftItemList[4] = new ItemList(new ArrayList<ItemStack>(Arrays.asList(
				new ItemStack(BlockRegister.cabinetStone, 1),
				new ItemStack(BlockRegister.ritualGlassTier4, 16),
				enchaPearlEfficiency1)));
		ritualLocate[4][0][1][1] = BlockRegister.ritualStoneTier3;
		ritualLocate[4][1][1][0] = BlockRegister.ritualStoneTier3;
		ritualLocate[4][1][1][1] = Blocks.CAULDRON;
		ritualLocate[4][1][1][2] = BlockRegister.ritualStoneTier3;
		ritualLocate[4][2][1][1] = BlockRegister.ritualStoneTier3;

		craftItemList[5] = new ItemList(new ArrayList<ItemStack>(Arrays.asList(
				new ItemStack(ItemRegister.ashOfOrder),
				new ItemStack(Items.EXPERIENCE_BOTTLE, 16),
				enchaPearlSilkTouch)));
		ritualLocate[5][0][0][1] = BlockRegister.ritualStoneTier2;
		ritualLocate[5][0][0][0] = BlockRegister.cabinetStone;
		ritualLocate[5][0][0][2] = BlockRegister.cabinetStone;
		ritualLocate[5][1][0][0] = BlockRegister.ritualStoneTier2;
		ritualLocate[5][1][0][1] = BlockRegister.ritualStoneTier3;
		ritualLocate[5][1][0][2] = BlockRegister.ritualStoneTier2;
		ritualLocate[5][2][0][1] = BlockRegister.ritualStoneTier2;
		ritualLocate[5][2][0][2] = BlockRegister.cabinetStone;
		ritualLocate[5][2][0][0] = BlockRegister.cabinetStone;
		ritualLocate[5][1][1][1] = Blocks.CAULDRON;
		ritualLocate[5][2][1][0] = BlockRegister.ritualStoneTier1;
		ritualLocate[5][0][1][2] = BlockRegister.ritualStoneTier1;
		ritualLocate[5][2][1][2] = BlockRegister.ritualStoneTier1;
		ritualLocate[5][0][1][0] = BlockRegister.ritualStoneTier1;

		craftItemList[6] = new ItemList(new ArrayList<ItemStack>(Arrays.asList(
				new ItemStack(Items.NETHER_STAR, 16),
				new ItemStack(Items.PAPER, 64),
				new ItemStack(Items.FEATHER, 64),
				new ItemStack(ItemRegister.pureExperienceIngot, 8),

				enchaPearlInfinity)));

		ritualLocate[6][0][1][0] = BlockRegister.ritualStoneTier1;
		ritualLocate[6][0][1][2] = BlockRegister.ritualStoneTier1;
		ritualLocate[6][1][1][1] = Blocks.CAULDRON;
		ritualLocate[6][2][1][0] = BlockRegister.ritualStoneTier1;
		ritualLocate[6][2][1][2] = BlockRegister.ritualStoneTier1;
		ritualLocate[6][0][2][0] = BlockRegister.ritualStoneTier2;
		ritualLocate[6][0][2][2] = BlockRegister.ritualStoneTier2;
		ritualLocate[6][2][2][0] = BlockRegister.ritualStoneTier2;
		ritualLocate[6][2][2][2] = BlockRegister.ritualStoneTier2;

		craftItemList[7] = new ItemList(new ArrayList<ItemStack>(Arrays.asList(
				new ItemStack(Items.SLIME_BALL, 64),
				new ItemStack(Items.COOKED_BEEF, 16),
				new ItemStack(Items.COOKED_CHICKEN, 16),
				new ItemStack(Items.COOKED_FISH, 16),
				new ItemStack(Items.COOKED_MUTTON, 16),
				new ItemStack(Items.COOKED_PORKCHOP, 16),
				new ItemStack(Items.COOKED_RABBIT, 16),
				new ItemStack(Items.PAPER, 64),
				new ItemStack(ItemRegister.pureExperienceIngot, 1),

				enchaPearlEfficiency5)));

		ritualLocate[7][0][1][0] = BlockRegister.ritualStoneTier1;
		ritualLocate[7][0][1][2] = BlockRegister.ritualStoneTier1;
		ritualLocate[7][1][1][1] = Blocks.CAULDRON;
		ritualLocate[7][2][1][0] = BlockRegister.ritualStoneTier1;
		ritualLocate[7][2][1][2] = BlockRegister.ritualStoneTier1;
		ritualLocate[7][0][2][0] = BlockRegister.ritualStoneTier2;
		ritualLocate[7][0][2][2] = BlockRegister.ritualStoneTier2;
		ritualLocate[7][2][2][0] = BlockRegister.ritualStoneTier2;
		ritualLocate[7][2][2][2] = BlockRegister.ritualStoneTier2;

		craftItemList[8] = new ItemList(new ArrayList<ItemStack>(Arrays.asList(
				enchaPearlLooting3,
				new ItemStack(ItemRegister.pureExperienceIngot, 4),
				new ItemStack(ItemRegister.ashOfOrder))));
		ritualLocate[8][0][0][1] = BlockRegister.ritualStoneTier2;
		ritualLocate[8][1][0][0] = BlockRegister.ritualStoneTier2;
		ritualLocate[8][1][0][2] = BlockRegister.ritualStoneTier2;
		ritualLocate[8][2][0][1] = BlockRegister.ritualStoneTier2;

		ritualLocate[8][0][1][0] = BlockRegister.ritualStoneTier2;
		ritualLocate[8][0][1][2] = BlockRegister.ritualStoneTier2;
		ritualLocate[8][1][1][1] = Blocks.CAULDRON;
		ritualLocate[8][2][1][0] = BlockRegister.ritualStoneTier2;
		ritualLocate[8][2][1][2] = BlockRegister.ritualStoneTier2;

		ritualLocate[8][0][2][1] = BlockRegister.ritualStoneTier2;
		ritualLocate[8][1][2][0] = BlockRegister.ritualStoneTier2;
		ritualLocate[8][1][2][2] = BlockRegister.ritualStoneTier2;
		ritualLocate[8][2][2][1] = BlockRegister.ritualStoneTier2;

		craftItemList[9] = new ItemList(new ArrayList<ItemStack>(Arrays.asList(
				enchaPearlEfficiency5,
				new ItemStack(BlockRegister.accelerateStone))));
		ritualLocate[9][0][0][1] = BlockRegister.ritualStoneTier4;
		ritualLocate[9][1][0][0] = BlockRegister.ritualStoneTier4;
		ritualLocate[9][1][0][2] = BlockRegister.ritualStoneTier4;
		ritualLocate[9][2][0][1] = BlockRegister.ritualStoneTier4;

		ritualLocate[9][0][1][0] = BlockRegister.ritualStoneTier4;
		ritualLocate[9][0][1][2] = BlockRegister.ritualStoneTier4;
		ritualLocate[9][1][1][1] = Blocks.CAULDRON;
		ritualLocate[9][2][1][0] = BlockRegister.ritualStoneTier4;
		ritualLocate[9][2][1][2] = BlockRegister.ritualStoneTier4;

		ritualLocate[9][0][2][1] = BlockRegister.ritualStoneTier4;
		ritualLocate[9][1][2][0] = BlockRegister.ritualStoneTier4;
		ritualLocate[9][1][2][2] = BlockRegister.ritualStoneTier4;
		ritualLocate[9][2][2][1] = BlockRegister.ritualStoneTier4;

		craftItemList[10] = new ItemList(new ArrayList<ItemStack>(Arrays.asList(
				enchaPearlEfficiency5,
				new ItemStack(BlockRegister.growthStone))));
		ritualLocate[10][0][0][1] = BlockRegister.ritualStoneTier4;
		ritualLocate[10][1][0][0] = BlockRegister.ritualStoneTier4;
		ritualLocate[10][1][0][2] = BlockRegister.ritualStoneTier4;
		ritualLocate[10][2][0][1] = BlockRegister.ritualStoneTier4;

		ritualLocate[10][0][1][0] = BlockRegister.ritualStoneTier4;
		ritualLocate[10][0][1][2] = BlockRegister.ritualStoneTier4;
		ritualLocate[10][1][1][1] = Blocks.CAULDRON;
		ritualLocate[10][2][1][0] = BlockRegister.ritualStoneTier4;
		ritualLocate[10][2][1][2] = BlockRegister.ritualStoneTier4;

		ritualLocate[10][0][2][1] = BlockRegister.ritualStoneTier4;
		ritualLocate[10][1][2][0] = BlockRegister.ritualStoneTier4;
		ritualLocate[10][1][2][2] = BlockRegister.ritualStoneTier4;
		ritualLocate[10][2][2][1] = BlockRegister.ritualStoneTier4;
		return ritualLocate[n][x][y][z];
	}
}