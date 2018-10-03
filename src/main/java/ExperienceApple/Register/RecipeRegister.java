package ExperienceApple.Register;

import java.util.ArrayList;
import java.util.List;

import ExperienceApple.EAMain;
import Recipes.EACraft;
import Recipes.EnchCraft;
import Recipes.RepairCraft;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.potion.PotionType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;;

public class RecipeRegister {
	public static int ritualGlassCost = 50;
	public static List<IRecipe> repaiableItem = new ArrayList<>();
	public static List<IRecipe> eaCraftedItem = new ArrayList<>();
	public static List<IRecipe> enchantableItem = new ArrayList<>();

	public static void init() {
		addEnchRecipe(ItemRegister.advancedExperienceIronAxe);
		addEnchRecipe(ItemRegister.advancedExperienceIronPickaxe);
		addEnchRecipe(ItemRegister.advancedExperienceIronShovel);
		addEnchRecipe(ItemRegister.advancedExperienceIronSword);
		addEnchRecipe(ItemRegister.advancedExperienceIronBoots);
		addEnchRecipe(ItemRegister.advancedExperienceIronChestplate);
		addEnchRecipe(ItemRegister.advancedExperienceIronHelmet);
		addEnchRecipe(ItemRegister.advancedExperienceIronLeggings);

		addEARecipe(Items.IRON_INGOT, 25, ItemRegister.weakExperienceIronIngot);
		addEARecipe(Item.getItemFromBlock(Blocks.GLASS), ritualGlassCost,
				Item.getItemFromBlock(BlockRegister.ritualGlassTier1));
		addEARecipe(ItemRegister.bakedSeeds, 100, ItemRegister.infusedBakedSeeds);
		addEARecipe(Items.DRAGON_BREATH, 10000, ItemRegister.superDye);

		addRepairRecipe(ItemRegister.weakExperienceIronPickaxe, 10);
		addRepairRecipe(ItemRegister.weakExperienceIronAxe, 10);
		addRepairRecipe(ItemRegister.weakExperienceIronShovel, 10);
		addRepairRecipe(ItemRegister.weakExperienceIronSword, 10);
		addRepairRecipe(ItemRegister.weakExperienceIronHelmet, 10);
		addRepairRecipe(ItemRegister.weakExperienceIronChestplate, 10);
		addRepairRecipe(ItemRegister.weakExperienceIronLeggings, 10);
		addRepairRecipe(ItemRegister.weakExperienceIronBoots, 10);
		addRepairRecipe(ItemRegister.ashOfEntropy, 1);
		addRepairRecipe(ItemRegister.ashOfBalance, 1);
		addRepairRecipe(ItemRegister.ashOfOrder, 1);

		blockRecipe(ItemRegister.weakExperienceIronIngot, BlockRegister.weakExperienceIronBlock);
		blockRecipe(ItemRegister.experienceIronIngot, BlockRegister.experienceIronBlock);
		blockRecipe(ItemRegister.pureExperienceIngot, BlockRegister.pureExperienceBlock);

		GameRegistry.addSmelting(Items.WHEAT_SEEDS, new ItemStack(ItemRegister.bakedSeeds), 1);

		addShapedRecipe(new ItemStack(ItemRegister.warpStone, 16), "SGS", "GEG", "SGS", 'G',
				BlockRegister.ritualGlassTier2, 'S', Blocks.STONE, 'E', Items.ENDER_PEARL);

		addShapedRecipe(new ItemStack(ItemRegister.weakExperienceIronShovel), " W ", " S ", " S ", 'W',
				ItemRegister.weakExperienceIronIngot, 'S', Items.STICK);
		addShapedRecipe(new ItemStack(ItemRegister.weakExperienceIronPickaxe), "WWW", " S ", " S ", 'W',
				ItemRegister.weakExperienceIronIngot, 'S', Items.STICK);
		addShapedRecipe(new ItemStack(ItemRegister.weakExperienceIronSword), " W ", " W ", " S ", 'W',
				ItemRegister.weakExperienceIronIngot, 'S', Items.STICK);
		addShapedRecipe(new ItemStack(ItemRegister.weakExperienceIronAxe), "WW ", "WS ", " S ", 'W',
				ItemRegister.weakExperienceIronIngot, 'S', Items.STICK);
		addShapedRecipe(new ItemStack(ItemRegister.weakExperienceIronHelmet), "WWW", "W W", "   ", 'W',
				ItemRegister.weakExperienceIronIngot);
		addShapedRecipe(new ItemStack(ItemRegister.weakExperienceIronChestplate), "W W", "WWW", "WWW", 'W',
				ItemRegister.weakExperienceIronIngot);
		addShapedRecipe(new ItemStack(ItemRegister.weakExperienceIronLeggings), "WWW", "W W", "W W", 'W',
				ItemRegister.weakExperienceIronIngot);
		addShapedRecipe(new ItemStack(ItemRegister.weakExperienceIronBoots), "   ", "W W", "W W", 'W',
				ItemRegister.weakExperienceIronIngot);

		addShapedRecipe(new ItemStack(ItemRegister.experienceIronShovel), " W ", " S ", " S ", 'W',
				ItemRegister.experienceIronIngot, 'S', Items.STICK);
		addShapedRecipe(new ItemStack(ItemRegister.experienceIronPickaxe), "WWW", " S ", " S ", 'W',
				ItemRegister.experienceIronIngot, 'S', Items.STICK);
		addShapedRecipe(new ItemStack(ItemRegister.experienceIronSword), " W ", " W ", " S ", 'W',
				ItemRegister.experienceIronIngot, 'S', Items.STICK);
		addShapedRecipe(new ItemStack(ItemRegister.experienceIronAxe), "WW ", "WS ", " S ", 'W',
				ItemRegister.experienceIronIngot, 'S', Items.STICK);
		addShapedRecipe(new ItemStack(ItemRegister.experienceIronHelmet), "WWW", "W W", "   ", 'W',
				ItemRegister.experienceIronIngot);
		addShapedRecipe(new ItemStack(ItemRegister.experienceIronChestplate), "W W", "WWW", "WWW", 'W',
				ItemRegister.experienceIronIngot);
		addShapedRecipe(new ItemStack(ItemRegister.experienceIronLeggings), "WWW", "W W", "W W", 'W',
				ItemRegister.experienceIronIngot);
		addShapedRecipe(new ItemStack(ItemRegister.experienceIronBoots), "   ", "W W", "W W", 'W',
				ItemRegister.experienceIronIngot);

		addShapedRecipe(new ItemStack(BlockRegister.wEIHopper), "W W", "WHW", " W ", 'W',
				ItemRegister.weakExperienceIronIngot, 'H', Blocks.HOPPER);
		addShapedRecipe(new ItemStack(BlockRegister.eIHopper), "W W", "WHW", " W ", 'W',
				ItemRegister.experienceIronIngot, 'H', BlockRegister.wEIHopper);
		addShapedRecipe(new ItemStack(BlockRegister.storageRack), "GGG", "GHG", "WWW", 'W',
				ItemRegister.weakExperienceIronIngot, 'H', BlockRegister.cabinetStone, 'G',
				BlockRegister.ritualGlassTier1);

		addShapedRecipe(new ItemStack(BlockRegister.ritualStoneTier1, 8), "WWW", "WGW", "WWW", 'W',
				BlockRegister.ritualGlassTier1, 'G', BlockRegister.cabinetStone);
		addShapedRecipe(new ItemStack(BlockRegister.ritualStoneTier2, 8), "WWW", "WGW", "WWW", 'W',
				BlockRegister.ritualGlassTier2, 'G', BlockRegister.cabinetStone);
		addShapedRecipe(new ItemStack(BlockRegister.ritualStoneTier3, 8), "WWW", "WGW", "WWW", 'W',
				BlockRegister.ritualGlassTier3, 'G', BlockRegister.cabinetStone);
		addShapedRecipe(new ItemStack(BlockRegister.ritualStoneTier4, 8), "WWW", "WGW", "WWW", 'W',
				BlockRegister.ritualGlassTier4, 'G', BlockRegister.cabinetStone);

		addShapedRecipe(new ItemStack(BlockRegister.ritualLauncherTier1), "WWW", "WGW", "WWW", 'W',
				BlockRegister.ritualStoneTier1, 'G', BlockRegister.highFrequencyRedStone);
		addShapedRecipe(new ItemStack(BlockRegister.ritualLauncherTier2), "WWW", "WGW", "WWW", 'W',
				BlockRegister.ritualStoneTier2, 'G', BlockRegister.highFrequencyRedStone);
		addShapedRecipe(new ItemStack(BlockRegister.ritualLauncherTier3), "WWW", "WGW", "WWW", 'W',
				BlockRegister.ritualStoneTier3, 'G', BlockRegister.highFrequencyRedStone);
		addShapedRecipe(new ItemStack(BlockRegister.ritualLauncherTier4), "WWW", "WGW", "WWW", 'W',
				BlockRegister.ritualStoneTier4, 'G', BlockRegister.highFrequencyRedStone);

		addShapedRecipe(new ItemStack(BlockRegister.rackManager), "WHW", "HGH", "WHW", 'W', BlockRegister.cabinetStone,
				'G', Blocks.BRICK_BLOCK, 'H', Items.ENDER_PEARL);

		addShapelessRecipe(new ItemStack(ItemRegister.experienceApple), Items.APPLE, Items.GOLD_INGOT);
		addShapelessRecipe(new ItemStack(ItemRegister.atomicCutter), ItemRegister.entropyIngot, Items.SHEARS,
				Items.BLAZE_ROD);
		addShapelessRecipe(new ItemStack(BlockRegister.faceBlock), Blocks.SLIME_BLOCK, ItemRegister.superDye);
		faceRecipe(new ItemStack(BlockRegister.accelerateStone), new ItemStack(BlockRegister.largeAccelerateStone));
		faceRecipe(new ItemStack(BlockRegister.advancedAccelerateStone),
				new ItemStack(BlockRegister.largeAdvancedAccelerateStone));
		faceRecipe(new ItemStack(BlockRegister.growthStone), new ItemStack(BlockRegister.largeGrowthStone));
		faceRecipe(new ItemStack(BlockRegister.advancedGrowthStone),
				new ItemStack(BlockRegister.largeAdvancedGrowthStone));
		faceRecipe(new ItemStack(BlockRegister.cabinetStone), new ItemStack(BlockRegister.largeCabinetStone));
		faceRecipe(new ItemStack(BlockRegister.ritualGlassTier1), new ItemStack(BlockRegister.largeRitualGlass1));
		faceRecipe(new ItemStack(BlockRegister.ritualGlassTier2), new ItemStack(BlockRegister.largeRitualGlass2));
		faceRecipe(new ItemStack(BlockRegister.ritualGlassTier3), new ItemStack(BlockRegister.largeRitualGlass3));
		faceRecipe(new ItemStack(BlockRegister.ritualGlassTier4), new ItemStack(BlockRegister.largeRitualGlass4));

		faceRecipe(new ItemStack(BlockRegister.ritualStoneTier1), new ItemStack(BlockRegister.largeRitualStone1));
		faceRecipe(new ItemStack(BlockRegister.ritualStoneTier2), new ItemStack(BlockRegister.largeRitualStone2));
		faceRecipe(new ItemStack(BlockRegister.ritualStoneTier3), new ItemStack(BlockRegister.largeRitualStone3));
		faceRecipe(new ItemStack(BlockRegister.ritualStoneTier4), new ItemStack(BlockRegister.largeRitualStone4));

		addShapelessRecipe(new ItemStack(ItemRegister.warpStone), ItemRegister.warpStone);
		addShapelessRecipe(new ItemStack(ItemRegister.enchantmentPearl), Items.ENCHANTED_BOOK, Items.ENDER_PEARL);
		addShapelessRecipe(new ItemStack(ItemRegister.flyingSpellPaper), ItemRegister.ashOfOrder,
				ItemRegister.pureExperienceIngot, Items.NETHER_STAR, Items.FEATHER, Items.PAPER);
		addShapelessRecipe(new ItemStack(ItemRegister.satietySpellPaper), ItemRegister.ashOfOrder,
				ItemRegister.pureExperienceIngot, Items.NETHER_STAR, Items.SLIME_BALL, Items.PAPER);
		addShapedRecipe(new ItemStack(ItemRegister.gravityCompressor), "OOO", "OBO", "OOO", 'O',
				BlockRegister.experienceOre, 'B', Blocks.OBSIDIAN);

		addShapedRecipe(new ItemStack(Item.getItemFromBlock(BlockRegister.ritualGlassTier2)), "OOO", "OBO", "OOO", 'O',
				BlockRegister.ritualGlassTier1, 'B', ItemRegister.gravityCompressor);
		addShapedRecipe(new ItemStack(Item.getItemFromBlock(BlockRegister.ritualGlassTier3)), "OOO", "OBO", "OOO", 'O',
				BlockRegister.ritualGlassTier2, 'B', ItemRegister.gravityCompressor);
		addShapedRecipe(new ItemStack(Item.getItemFromBlock(BlockRegister.ritualGlassTier4)), "OOO", "OBO", "OOO", 'O',
				BlockRegister.ritualGlassTier3, 'B', ItemRegister.gravityCompressor);
		addShapedRecipe(new ItemStack(Item.getItemFromBlock(BlockRegister.condensedExperienceOre)), "EOE", "OGO", "EOE",
				'E', BlockRegister.experienceOre, 'G', ItemRegister.gravityCompressor, 'O', Items.EXPERIENCE_BOTTLE);

		addShapedRecipe(new ItemStack(ItemRegister.balanceIngot), "WWW", "WGW", "WWW", 'W',
				new ItemStack(ItemRegister.ashOfBalance, 1, 0), 'G', ItemRegister.experienceIronIngot);
		addShapedRecipe(new ItemStack(ItemRegister.entropyIngot), "WWW", "WGW", "WWW", 'W',
				new ItemStack(ItemRegister.ashOfEntropy, 1, 0), 'G', ItemRegister.experienceIronIngot);
		addShapedRecipe(new ItemStack(ItemRegister.orderIngot), "WWW", "WGW", "WWW", 'W',
				new ItemStack(ItemRegister.ashOfOrder, 1, 0), 'G', ItemRegister.experienceIronIngot);

		GameRegistry.addSmelting(Items.GHAST_TEAR, new ItemStack(ItemRegister.ashOfEntropy, 1, 99), 1F);

		addShapelessRecipe(new ItemStack(ItemRegister.ritualAssembler), Items.APPLE, Items.EXPERIENCE_BOTTLE);

		GameRegistry
				.addSmelting(new ItemStack(ItemRegister.ashOfEntropy, 1, 0),
						new ItemStack(ItemRegister.ashOfBalance, 1,
								ItemRegister.ashOfBalance.getMaxDamage(new ItemStack(ItemRegister.ashOfBalance)) - 1),
						1F);

		GameRegistry.addSmelting(new ItemStack(ItemRegister.ashOfBalance, 1, 0), new ItemStack(ItemRegister.ashOfOrder,
				1, ItemRegister.ashOfOrder.getMaxDamage(new ItemStack(ItemRegister.ashOfOrder)) - 1), 1F);
		brewingRecipe(PotionRegister.retentionNormal, PotionRegister.retentionLong,
				new ItemStack(ItemRegister.ashOfOrder));
		brewingRecipe(PotionRegister.stateProtectionNormal, PotionRegister.stateProtectionLong,
				new ItemStack(ItemRegister.fragmentOfTheBrain));

	}

	public static void addEnchRecipe(ItemStack tool) {
		IRecipe recipe = new EnchCraft(tool);
		recipe.setRegistryName(new ResourceLocation(EAMain.MOD_ID, recipe.toString()));
		ForgeRegistries.RECIPES.register(recipe);
		enchantableItem.add(recipe);
	}

	public static void addEnchRecipe(Item tool) {
		addEnchRecipe(new ItemStack(tool));
	}

	public static void addEARecipe(ItemStack material, int cost, ItemStack output) {
		IRecipe recipe = new EACraft(material, cost, output);
		recipe.setRegistryName(new ResourceLocation(EAMain.MOD_ID, recipe.toString()));
		ForgeRegistries.RECIPES.register(recipe);
		eaCraftedItem.add(recipe);
	}

	public static void addEARecipe(Item material, int cost, Item output) {
		addEARecipe(new ItemStack(material), cost, new ItemStack(output));
	}

	public static void addRepairRecipe(ItemStack itemStack, int amount) {
		IRecipe recipe = new RepairCraft(itemStack, amount);
		recipe.setRegistryName(new ResourceLocation(EAMain.MOD_ID, recipe.toString()));
		ForgeRegistries.RECIPES.register(recipe);
		repaiableItem.add(recipe);
	}

	public static void addRepairRecipe(Item item, int amount) {
		addRepairRecipe(new ItemStack(item), amount);
	}

	public static void brewingRecipe(PotionType potion, PotionType longpotion, ItemStack material) {
		BrewingRecipeRegistry.addRecipe(
				PotionRegister.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.AWKWARD), material,
				PotionRegister.addPotionToItemStack(new ItemStack(Items.POTIONITEM), potion));

		BrewingRecipeRegistry.addRecipe(PotionRegister.addPotionToItemStack(new ItemStack(Items.POTIONITEM), potion),
				new ItemStack(Items.REDSTONE, 1, 0),
				PotionRegister.addPotionToItemStack(new ItemStack(Items.POTIONITEM), longpotion));

		BrewingRecipeRegistry.addRecipe(
				PotionRegister.addPotionToItemStack(new ItemStack(Items.SPLASH_POTION), PotionTypes.AWKWARD), material,
				PotionRegister.addPotionToItemStack(new ItemStack(Items.SPLASH_POTION), potion));

		BrewingRecipeRegistry.addRecipe(PotionRegister.addPotionToItemStack(new ItemStack(Items.SPLASH_POTION), potion),
				new ItemStack(Items.REDSTONE, 1, 0),
				PotionRegister.addPotionToItemStack(new ItemStack(Items.SPLASH_POTION), longpotion));

		BrewingRecipeRegistry.addRecipe(
				PotionRegister.addPotionToItemStack(new ItemStack(Items.LINGERING_POTION), PotionTypes.AWKWARD),
				material, PotionRegister.addPotionToItemStack(new ItemStack(Items.LINGERING_POTION), potion));

		BrewingRecipeRegistry.addRecipe(
				PotionRegister.addPotionToItemStack(new ItemStack(Items.LINGERING_POTION), potion),
				new ItemStack(Items.REDSTONE, 1, 0),
				PotionRegister.addPotionToItemStack(new ItemStack(Items.LINGERING_POTION), longpotion));
	}

	public static void brewingRecipe(PotionType potion, PotionType longpotion, PotionType strongpotion,
			ItemStack material) {
		brewingRecipe(strongpotion, longpotion, material);

		BrewingRecipeRegistry.addRecipe(PotionRegister.addPotionToItemStack(new ItemStack(Items.POTIONITEM), potion),
				new ItemStack(Items.GLOWSTONE_DUST, 1, 0),
				PotionRegister.addPotionToItemStack(new ItemStack(Items.POTIONITEM), strongpotion));
		BrewingRecipeRegistry.addRecipe(PotionRegister.addPotionToItemStack(new ItemStack(Items.SPLASH_POTION), potion),
				new ItemStack(Items.GLOWSTONE_DUST, 1, 0),
				PotionRegister.addPotionToItemStack(new ItemStack(Items.SPLASH_POTION), strongpotion));
		BrewingRecipeRegistry.addRecipe(
				PotionRegister.addPotionToItemStack(new ItemStack(Items.LINGERING_POTION), potion),
				new ItemStack(Items.GLOWSTONE_DUST, 1, 0),
				PotionRegister.addPotionToItemStack(new ItemStack(Items.LINGERING_POTION), strongpotion));
	}

	public static String getName(Object ob, Object... param) {
		String name = ob.toString() + param.toString();
		return name;
	}

	public static void addShapedRecipe(ItemStack item, Object... param) {

		ForgeRegistries.RECIPES
				.register(new ShapedOreRecipe(new ResourceLocation(EAMain.MOD_ID, getName(item, param)), item, param)
						.setRegistryName(EAMain.MOD_ID, getName(item, param)));
	}

	public static void addShapedRecipe(Block block, Object... param) {
		ForgeRegistries.RECIPES
				.register(new ShapedOreRecipe(new ResourceLocation(EAMain.MOD_ID, getName(block, param)), block, param)
						.setRegistryName(EAMain.MOD_ID, getName(block, param)));
	}

	public static void addShapedRecipe(Item item, Object... param) {
		ForgeRegistries.RECIPES
				.register(new ShapedOreRecipe(new ResourceLocation(EAMain.MOD_ID, getName(item, param)), item, param)
						.setRegistryName(EAMain.MOD_ID, getName(item, param)));
	}

	public static void addShapelessRecipe(Item item, Object... param) {
		ForgeRegistries.RECIPES
				.register(new ShapelessOreRecipe(new ResourceLocation(EAMain.MOD_ID, getName(item, param)), item, param)
						.setRegistryName(EAMain.MOD_ID, getName(item, param)));
	}

	public static void addShapelessRecipe(ItemStack item, Object... param) {
		ForgeRegistries.RECIPES
				.register(new ShapelessOreRecipe(new ResourceLocation(EAMain.MOD_ID, getName(item, param)), item, param)
						.setRegistryName(EAMain.MOD_ID, getName(item, param)));
	}

	public static void addShapelessRecipe(Block block, Object... param) {
		ForgeRegistries.RECIPES.register(
				new ShapelessOreRecipe(new ResourceLocation(EAMain.MOD_ID, getName(block, param)), block, param)
						.setRegistryName(EAMain.MOD_ID, getName(block, param)));
	}

	public static void blockRecipe(Item item, Block block) {
		addShapedRecipe(new ItemStack(Item.getItemFromBlock(block)), "AAA", "AAA", "AAA", 'A', item);
		addShapelessRecipe(new ItemStack(item, 9), block);
	}

	public static void faceRecipe(ItemStack resource, ItemStack output) {
		output.setCount(2);
		addShapelessRecipe(output, resource, new ItemStack(BlockRegister.faceBlock));
		addShapelessRecipe(output, output, new ItemStack(BlockRegister.faceBlock));
	}
}
