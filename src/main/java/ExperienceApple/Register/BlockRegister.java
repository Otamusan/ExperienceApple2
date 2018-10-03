package ExperienceApple.Register;

import java.util.ArrayList;
import java.util.List;

import Blocks.BlockAccelerateStone;
import Blocks.BlockAwakenedSpawner;
import Blocks.BlockCabinetStone;
import Blocks.BlockClearGlass;
import Blocks.BlockCodensedExperienceOre;
import Blocks.BlockEIHopper;
import Blocks.BlockExperienceOre;
import Blocks.BlockGrowthStone;
import Blocks.BlockHighFrequencyRedStone;
import Blocks.BlockMod;
import Blocks.BlockPureExperience;
import Blocks.BlockRackManager;
import Blocks.BlockRitualGlass;
import Blocks.BlockRitualLauncher;
import Blocks.BlockRitualStone;
import Blocks.BlockStorageRack;
import Blocks.BlockWEIHopper;
import Blocks.Dirt.BlockEnrichedFarmLand;
import Blocks.Dirt.BlockPath;
import Blocks.Dirt.BlockRottenDirt;
import ExperienceApple.EAMain;
import Rituals.EnumRitualStones;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlime;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

public class BlockRegister {
	public static List<Block> descriptionlist = new ArrayList<>();
	public static final Block ritualGlassTier1 = new BlockRitualGlass(Material.GLASS, 1, EnumRitualStones.tier1)
			.setHardness(0.3F);
	public static final Block ritualGlassTier2 = new BlockRitualGlass(Material.GLASS, 1, EnumRitualStones.tier2)
			.setHardness(0.3F);
	public static final Block ritualGlassTier3 = new BlockRitualGlass(Material.GLASS, 1, EnumRitualStones.tier3)
			.setHardness(0.3F);
	public static final Block ritualGlassTier4 = new BlockRitualGlass(Material.GLASS, 1, EnumRitualStones.tier4)
			.setHardness(0.3F);
	public static final Block ritualStoneTier1 = new BlockRitualStone(Material.ROCK, 1, EnumRitualStones.tier1)
			.setHardness(100).setResistance(100);
	public static final Block ritualStoneTier2 = new BlockRitualStone(Material.ROCK, 1, EnumRitualStones.tier2)
			.setHardness(100).setResistance(100);
	public static final Block ritualStoneTier3 = new BlockRitualStone(Material.ROCK, 1, EnumRitualStones.tier3)
			.setHardness(100).setResistance(100);
	public static final Block ritualStoneTier4 = new BlockRitualStone(Material.ROCK, 1, EnumRitualStones.tier4)
			.setHardness(100).setResistance(100);
	public static final Block ritualLauncherTier1 = new BlockRitualLauncher(Material.ROCK, 1, EnumRitualStones.tier1)
			.setHardness(100).setResistance(100);
	public static final Block ritualLauncherTier2 = new BlockRitualLauncher(Material.ROCK, 1, EnumRitualStones.tier2)
			.setHardness(100).setResistance(100);
	public static final Block ritualLauncherTier3 = new BlockRitualLauncher(Material.ROCK, 1, EnumRitualStones.tier3)
			.setHardness(100).setResistance(100);
	public static final Block ritualLauncherTier4 = new BlockRitualLauncher(Material.ROCK, 1, EnumRitualStones.tier4)
			.setHardness(100).setResistance(100);

	public static final Block cabinetStone = new BlockCabinetStone(Material.ROCK).setHardness(5.0F);

	public static final Block accelerateStone = new BlockAccelerateStone(Material.ROCK, 10).setHardness(5.0F);
	public static final Block advancedAccelerateStone = new BlockAccelerateStone(Material.ROCK, 2000).setHardness(5.0F);

	public static final Block growthStone = new BlockGrowthStone(Material.ROCK, 1).setHardness(5.0F);
	public static final Block advancedGrowthStone = new BlockGrowthStone(Material.ROCK, 20).setHardness(5.0F);

	public static final Block weakExperienceIronBlock = new BlockMod(Material.IRON).setHardness(5.0F);
	public static final Block experienceIronBlock = new BlockMod(Material.IRON).setHardness(5.0F);
	public static final Block experienceOre = new BlockExperienceOre(Material.ROCK).setHardness(50);
	public static final Block condensedExperienceOre = new BlockCodensedExperienceOre(Material.ROCK).setHardness(5.0F)
			.setHardness(364);
	public static final Block pureExperienceBlock = new BlockPureExperience().setLightLevel(114514);
	public static final Block path = new BlockPath();
	public static final Block rottenDirt = new BlockRottenDirt();
	public static final Block enrichedFarmLand = new BlockEnrichedFarmLand();

	public static final Block highFrequencyRedStone = new BlockHighFrequencyRedStone(Material.GROUND);
	public static final Block awakenedSpawner = new BlockAwakenedSpawner();

	public static final Block storageRack = new BlockStorageRack(Material.IRON).setHardness(5.0F);
	public static final Block rackManager = new BlockRackManager(Material.IRON).setHardness(5.0F);

	public static final Block faceBlock = new BlockSlime();

	public static final Block largeRitualGlass1 = new BlockClearGlass(Material.GLASS).setHardness(5.0F);
	public static final Block largeRitualGlass2 = new BlockClearGlass(Material.GLASS).setHardness(5.0F);
	public static final Block largeRitualGlass3 = new BlockClearGlass(Material.GLASS).setHardness(5.0F);
	public static final Block largeRitualGlass4 = new BlockClearGlass(Material.GLASS).setHardness(5.0F);

	public static final Block largeRitualStone1 = new Block(Material.ROCK).setHardness(5.0F);
	public static final Block largeRitualStone2 = new Block(Material.ROCK).setHardness(5.0F);
	public static final Block largeRitualStone3 = new Block(Material.ROCK).setHardness(5.0F);
	public static final Block largeRitualStone4 = new Block(Material.ROCK).setHardness(5.0F);

	public static final Block largeCabinetStone = new Block(Material.ROCK).setHardness(5.0F);

	public static final Block largeAccelerateStone = new Block(Material.ROCK).setHardness(5.0F);
	public static final Block largeAdvancedAccelerateStone = new Block(Material.ROCK).setHardness(5.0F);

	public static final Block largeGrowthStone = new Block(Material.ROCK).setHardness(5.0F);
	public static final Block largeAdvancedGrowthStone = new Block(Material.ROCK).setHardness(5.0F);

	public static final Block wEIHopper = new BlockWEIHopper().setHardness(5.0F);
	public static final Block eIHopper = new BlockEIHopper().setHardness(5.0F);

	public static Side Fside;

	public static void init(Side side) {

		Fside = side;
		// ((ITooltip) ritualGlassTier1).addTooltip("testtest");
		register(ritualGlassTier1, "ritualGlassTier1", true);
		register(ritualGlassTier2, "ritualGlassTier2", true);
		register(ritualGlassTier3, "ritualGlassTier3", true);
		register(ritualGlassTier4, "ritualGlassTier4", true);
		register(ritualStoneTier1, "ritualStoneTier1", true);
		register(ritualStoneTier2, "ritualStoneTier2", true);
		register(ritualStoneTier3, "ritualStoneTier3", true);
		register(ritualStoneTier4, "ritualStoneTier4", true);
		register(ritualLauncherTier1, "ritualLauncherTier1", true);
		register(ritualLauncherTier2, "ritualLauncherTier2", true);
		register(ritualLauncherTier3, "ritualLauncherTier3", true);
		register(ritualLauncherTier4, "ritualLauncherTier4", true);
		register(cabinetStone, "cabinetStone", false);

		register(accelerateStone, "accelerateStone", true);
		register(advancedAccelerateStone, "advancedAccelerateStone", true);

		register(growthStone, "growthStone", true);
		register(advancedGrowthStone, "advancedGrowthStone", true);

		register(weakExperienceIronBlock, "weakExperienceIronBlock", false);
		register(experienceOre, "experienceOre", true);
		register(condensedExperienceOre, "condensedExperienceOre", true);
		register(experienceIronBlock, "experienceIronBlock", false);
		register(pureExperienceBlock, "pureExperienceBlock", false);
		register(path, "path", true);
		register(rottenDirt, "rottenDirt", true);
		register(enrichedFarmLand, "enrichedFarmLand", true);

		register(highFrequencyRedStone, "highFrequencyRedStone", true);
		register(awakenedSpawner, "awakenedSpawner", true);

		register(storageRack, "storageRack", true);
		register(rackManager, "rackManager", true);

		register(faceBlock, "faceBlock", true);
		register(largeRitualGlass1, "largeRitualGlass1", true);
		register(largeRitualGlass2, "largeRitualGlass2", true);
		register(largeRitualGlass3, "largeRitualGlass3", true);
		register(largeRitualGlass4, "largeRitualGlass4", true);
		register(largeRitualStone1, "largeRitualStone1", true);
		register(largeRitualStone2, "largeRitualStone2", true);
		register(largeRitualStone3, "largeRitualStone3", true);
		register(largeRitualStone4, "largeRitualStone4", true);
		register(largeCabinetStone, "largeCabinetStone", true);
		register(largeAccelerateStone, "largeAccelerateStone", true);
		register(largeAdvancedAccelerateStone, "largeAdvancedAccelerateStone", true);
		register(largeGrowthStone, "largeGrowthStone", true);
		register(largeAdvancedGrowthStone, "largeAdvancedGrowthStone", true);

		register(wEIHopper, "wEIHopper", true);
		register(eIHopper, "eIHopper", true);

		OreDictionary.registerOre("dirt", path);
		OreDictionary.registerOre("dirt", rottenDirt);
		OreDictionary.registerOre("dirt", enrichedFarmLand);

		OreDictionary.registerOre("oreExperience", experienceOre);

	}

	public static void register(Block block, String str, boolean hasDescription) {
		if (hasDescription) {
			descriptionlist.add(block);
		}
		block.setRegistryName(new ResourceLocation(EAMain.MOD_ID + ":" + str));
		ForgeRegistries.BLOCKS.register(block);

		block.setUnlocalizedName(str);
		block.setCreativeTab(EAMain.tabAdd);

		ItemBlock item = new ItemBlock(block);
		item.setRegistryName(new ResourceLocation(EAMain.MOD_ID + ":" + str));
		ForgeRegistries.ITEMS.register(item);
		item.setUnlocalizedName(str);
		item.setCreativeTab(EAMain.tabAdd);

		if (Fside == Side.CLIENT) {
			modelRegister(block, str);
		}
	}

	@SideOnly(Side.CLIENT)
	public static void modelRegister(Block block, String str) {

		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0,
				new ModelResourceLocation(EAMain.MOD_ID + ":" + str));

	}
}
