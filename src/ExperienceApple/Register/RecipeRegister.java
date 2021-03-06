package ExperienceApple.Register;

import Crafting.EnchantCraft.EnchantRegister;
import Crafting.ExperienceAppleCrafting.ExperienceAppleCraftingRegister;
import Crafting.RepairCraft.RepairRegister;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;;

public class RecipeRegister {
	public static void init() {
		ExperienceAppleCraftingRegister.register(Items.IRON_INGOT, ItemRegister.weakExperienceIronIngot, 25);
		ExperienceAppleCraftingRegister.register(Item.getItemFromBlock(Blocks.GLASS),
				Item.getItemFromBlock(BlockRegister.ritualGlassTier1), 50);
		ExperienceAppleCraftingRegister.register(ItemRegister.bakedSeeds,
				ItemRegister.infusedBakedSeeds, 100);

		RepairRegister.register(ItemRegister.weakExperienceIronPickaxe);
		RepairRegister.register(ItemRegister.weakExperienceIronAxe);
		RepairRegister.register(ItemRegister.weakExperienceIronShovel);
		RepairRegister.register(ItemRegister.weakExperienceIronSword);
		RepairRegister.register(ItemRegister.weakExperienceIronHelmet);
		RepairRegister.register(ItemRegister.weakExperienceIronChestplate);
		RepairRegister.register(ItemRegister.weakExperienceIronLeggings);
		RepairRegister.register(ItemRegister.weakExperienceIronBoots);
		RepairRegister.register(ItemRegister.ashOfEntropy);
		RepairRegister.register(ItemRegister.ashOfBalance);
		RepairRegister.register(ItemRegister.ashOfOrder);

		EnchantRegister.register(ItemRegister.advancedExperienceIronAxe);
		EnchantRegister.register(ItemRegister.advancedExperienceIronPickaxe);
		EnchantRegister.register(ItemRegister.advancedExperienceIronShovel);
		EnchantRegister.register(ItemRegister.advancedExperienceIronSword);
		EnchantRegister.register(ItemRegister.advancedExperienceIronBoots);
		EnchantRegister.register(ItemRegister.advancedExperienceIronChestplate);
		EnchantRegister.register(ItemRegister.advancedExperienceIronHelmet);
		EnchantRegister.register(ItemRegister.advancedExperienceIronLeggings);

		blockRecipe(ItemRegister.weakExperienceIronIngot, BlockRegister.weakExperienceIronBlock);
		blockRecipe(ItemRegister.experienceIronIngot, BlockRegister.experienceIronBlock);
		blockRecipe(ItemRegister.pureExperienceIngot, BlockRegister.pureExperienceBlock);

		GameRegistry.addSmelting(Items.WHEAT_SEEDS, new ItemStack(ItemRegister.bakedSeeds), 1);

		GameRegistry.addShapedRecipe(new ItemStack(ItemRegister.enchantmentPearl), "EEE", "EPE", "EEE", 'E',
				Items.APPLE, 'P', Items.ENDER_PEARL);
		GameRegistry.addShapedRecipe(new ItemStack(ItemRegister.warpStone, 16), "SGS", "GEG", "SGS", 'G',
				BlockRegister.ritualGlassTier2, 'S', Blocks.STONE, 'E', Items.ENDER_PEARL);

		GameRegistry.addShapedRecipe(new ItemStack(ItemRegister.weakExperienceIronShovel), " W ", " S ", " S ", 'W',
				ItemRegister.weakExperienceIronIngot, 'S', Items.STICK);
		GameRegistry.addShapedRecipe(new ItemStack(ItemRegister.weakExperienceIronPickaxe), "WWW", " S ", " S ", 'W',
				ItemRegister.weakExperienceIronIngot, 'S', Items.STICK);
		GameRegistry.addShapedRecipe(new ItemStack(ItemRegister.weakExperienceIronSword), " W ", " W ", " S ", 'W',
				ItemRegister.weakExperienceIronIngot, 'S', Items.STICK);
		GameRegistry.addShapedRecipe(new ItemStack(ItemRegister.weakExperienceIronAxe), "WW ", "WS ", " S ", 'W',
				ItemRegister.weakExperienceIronIngot, 'S', Items.STICK);
		GameRegistry.addShapedRecipe(new ItemStack(ItemRegister.weakExperienceIronHelmet), "WWW", "W W", "   ", 'W',
				ItemRegister.weakExperienceIronIngot);
		GameRegistry.addShapedRecipe(new ItemStack(ItemRegister.weakExperienceIronChestplate), "W W", "WWW", "WWW", 'W',
				ItemRegister.weakExperienceIronIngot);
		GameRegistry.addShapedRecipe(new ItemStack(ItemRegister.weakExperienceIronLeggings), "WWW", "W W", "W W", 'W',
				ItemRegister.weakExperienceIronIngot);
		GameRegistry.addShapedRecipe(new ItemStack(ItemRegister.weakExperienceIronBoots), "   ", "W W", "W W", 'W',
				ItemRegister.weakExperienceIronIngot);

		GameRegistry.addShapedRecipe(new ItemStack(ItemRegister.experienceIronShovel), " W ", " S ", " S ", 'W',
				ItemRegister.experienceIronIngot, 'S', Items.STICK);
		GameRegistry.addShapedRecipe(new ItemStack(ItemRegister.experienceIronPickaxe), "WWW", " S ", " S ", 'W',
				ItemRegister.experienceIronIngot, 'S', Items.STICK);
		GameRegistry.addShapedRecipe(new ItemStack(ItemRegister.experienceIronSword), " W ", " W ", " S ", 'W',
				ItemRegister.experienceIronIngot, 'S', Items.STICK);
		GameRegistry.addShapedRecipe(new ItemStack(ItemRegister.experienceIronAxe), "WW ", "WS ", " S ", 'W',
				ItemRegister.experienceIronIngot, 'S', Items.STICK);
		GameRegistry.addShapedRecipe(new ItemStack(ItemRegister.experienceIronHelmet), "WWW", "W W", "   ", 'W',
				ItemRegister.experienceIronIngot);
		GameRegistry.addShapedRecipe(new ItemStack(ItemRegister.experienceIronChestplate), "W W", "WWW", "WWW", 'W',
				ItemRegister.experienceIronIngot);
		GameRegistry.addShapedRecipe(new ItemStack(ItemRegister.experienceIronLeggings), "WWW", "W W", "W W", 'W',
				ItemRegister.experienceIronIngot);
		GameRegistry.addShapedRecipe(new ItemStack(ItemRegister.experienceIronBoots), "   ", "W W", "W W", 'W',
				ItemRegister.experienceIronIngot);

		GameRegistry.addShapedRecipe(new ItemStack(ItemRegister.timeSand), " W ", "WCW", " W ", 'W',
				BlockRegister.ritualGlassTier2, 'C', Items.CLOCK);
		GameRegistry.addShapedRecipe(new ItemStack(ItemRegister.universalNutrient), " W ", "WCW", " W ", 'W',
				BlockRegister.ritualGlassTier2, 'C', Items.BONE);
		GameRegistry.addShapedRecipe(new ItemStack(BlockRegister.accelerateStone), "TTT", "TCT", "TTT", 'T',
				ItemRegister.timeSand, 'C', BlockRegister.cabinetStone);
		GameRegistry.addShapedRecipe(new ItemStack(BlockRegister.growthStone), "TTT", "TCT", "TTT", 'T',
				ItemRegister.universalNutrient, 'C', BlockRegister.cabinetStone);
		GameRegistry.addShapelessRecipe(new ItemStack(ItemRegister.experienceApple), Items.APPLE, Items.GOLD_INGOT);

		GameRegistry.addShapelessRecipe(new ItemStack(ItemRegister.advancedExperienceIronAxe),
				ItemRegister.experienceIronAxe, ItemRegister.pureExperienceIngot);
		GameRegistry.addShapelessRecipe(new ItemStack(ItemRegister.advancedExperienceIronPickaxe),
				ItemRegister.experienceIronPickaxe, ItemRegister.pureExperienceIngot);
		GameRegistry.addShapelessRecipe(new ItemStack(ItemRegister.advancedExperienceIronShovel),
				ItemRegister.experienceIronShovel, ItemRegister.pureExperienceIngot);
		GameRegistry.addShapelessRecipe(new ItemStack(ItemRegister.advancedExperienceIronSword),
				ItemRegister.experienceIronSword, ItemRegister.pureExperienceIngot);
		GameRegistry.addShapelessRecipe(new ItemStack(ItemRegister.advancedExperienceIronBoots),
				ItemRegister.experienceIronBoots, ItemRegister.pureExperienceIngot);
		GameRegistry.addShapelessRecipe(new ItemStack(ItemRegister.advancedExperienceIronChestplate),
				ItemRegister.experienceIronChestplate, ItemRegister.pureExperienceIngot);
		GameRegistry.addShapelessRecipe(new ItemStack(ItemRegister.advancedExperienceIronHelmet),
				ItemRegister.experienceIronHelmet, ItemRegister.pureExperienceIngot);
		GameRegistry.addShapelessRecipe(new ItemStack(ItemRegister.advancedExperienceIronLeggings),
				ItemRegister.experienceIronLeggings, ItemRegister.pureExperienceIngot);
		GameRegistry.addShapelessRecipe(new ItemStack(ItemRegister.warpStone),
				ItemRegister.warpStone);
		//GameRegistry.addShapelessRecipe(new ItemStack(ItemRegister.enchantmentPearl),
		//		Items.ENCHANTED_BOOK, Items.ENDER_PEARL);

		GameRegistry.addShapedRecipe(new ItemStack(ItemRegister.gravityCompressor), "OOO", "OBO", "OOO", 'O',
				BlockRegister.experienceOre, 'B', Blocks.OBSIDIAN);

		GameRegistry.addShapedRecipe(new ItemStack(Item.getItemFromBlock(BlockRegister.ritualGlassTier2)), "OOO", "OBO",
				"OOO", 'O',
				BlockRegister.ritualGlassTier1, 'B', ItemRegister.gravityCompressor);
		GameRegistry.addShapedRecipe(new ItemStack(Item.getItemFromBlock(BlockRegister.ritualGlassTier3)), "OOO", "OBO",
				"OOO", 'O',
				BlockRegister.ritualGlassTier2, 'B', ItemRegister.gravityCompressor);
		GameRegistry.addShapedRecipe(new ItemStack(Item.getItemFromBlock(BlockRegister.ritualGlassTier4)), "OOO", "OBO",
				"OOO", 'O',
				BlockRegister.ritualGlassTier3, 'B', ItemRegister.gravityCompressor);
		GameRegistry.addShapedRecipe(new ItemStack(Item.getItemFromBlock(BlockRegister.condensedExperienceOre)), "EOE",
				"OGO",
				"EOE", 'E',
				BlockRegister.experienceOre, 'G', ItemRegister.gravityCompressor, 'O', Items.EXPERIENCE_BOTTLE);
		GameRegistry.addSmelting(Items.GHAST_TEAR, new ItemStack(ItemRegister.ashOfEntropy, 1, 99), 1F);

		GameRegistry.addSmelting(new ItemStack(ItemRegister.ashOfEntropy, 1, 0),
				new ItemStack(ItemRegister.ashOfBalance, 1,
						ItemRegister.ashOfBalance.getMaxDamage(new ItemStack(ItemRegister.ashOfBalance)) - 1),
				1F);

		GameRegistry.addSmelting(new ItemStack(ItemRegister.ashOfBalance, 1, 0),
				new ItemStack(ItemRegister.ashOfOrder, 1,
						ItemRegister.ashOfOrder.getMaxDamage(new ItemStack(ItemRegister.ashOfOrder)) - 1),
				1F);

	}

	public static void blockRecipe(Item item, Block block) {
		GameRegistry.addShapedRecipe(new ItemStack(Item.getItemFromBlock(block)), "AAA", "AAA", "AAA", 'A', item);
		GameRegistry.addShapelessRecipe(new ItemStack(item, 9), block);
	}
}
