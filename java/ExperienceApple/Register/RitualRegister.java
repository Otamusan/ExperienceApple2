package ExperienceApple.Register;

import static Rituals.EnumRitualStones.*;

import java.util.ArrayList;

import Rituals.RitualRegistry;
import Rituals.StonePosData;
import Rituals.Rituals.Ritual;
import Rituals.Rituals.RitualBlockCut;
import Rituals.Rituals.RitualCollection;
import Rituals.Rituals.RitualCreateLava;
import Rituals.Rituals.RitualCreateWater;
import Rituals.Rituals.RitualEggization;
import Rituals.Rituals.RitualExplode;
import Rituals.Rituals.RitualSpawnerActivation;
import Rituals.Rituals.RitualCraft.RitualCraft;
import Rituals.Rituals.RitualCraft.RitualCraftCore;
import Rituals.Rituals.RitualDehydration.RitualDehydration;
import Rituals.Rituals.RitualDehydration.RitualDehydrationRecipe;
import Rituals.Rituals.RitualInjection.RitualInjection;
import Rituals.Rituals.RitualInjection.RitualInjectionCore;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class RitualRegister {
	public static Ritual ritualExplode = new RitualExplode(new StonePosData(), "explode");
	public static Ritual ritualBlockCut = new RitualBlockCut(new StonePosData(), "blockCut");
	public static Ritual ritualCreateWater = new RitualCreateWater(new StonePosData(), "createWater");
	public static Ritual ritualCreateLava = new RitualCreateLava(new StonePosData(), "createLava");
	public static Ritual ritualInjection = new RitualInjectionCore(new StonePosData(), "injection");
	public static Ritual ritualEggization = new RitualEggization(new StonePosData(), "eggization");
	public static Ritual ritualSpawnerActivation = new RitualSpawnerActivation(new StonePosData(), "spawnerActivation");
	public static Ritual ritualCollection = new RitualCollection(new StonePosData(), "collection");
	public static Ritual ritualCraft = new RitualCraftCore(new StonePosData(), "ritualCraft");
	public static Ritual ritualDehydration = new RitualDehydration(new StonePosData(), "ritualDehydration");

	public static void init() {
		ritualExplode.setStoneBlock(0, 0, 0, tier1);
		ritualExplode.setStoneBlock(0, 0, 1, tier1);
		ritualExplode.setStoneBlock(0, 0, -1, tier1);
		ritualExplode.setStoneBlock(-1, 0, 0, tier1);
		ritualExplode.setStoneBlock(1, 0, 0, tier1);
		RitualRegistry.register(ritualExplode);

		ritualBlockCut.setStoneBlock(0, -1, 1, tier1);
		ritualBlockCut.setStoneBlock(1, -1, 0, tier1);
		ritualBlockCut.setStoneBlock(0, -1, -1, tier1);
		ritualBlockCut.setStoneBlock(-1, -1, 0, tier1);
		ritualBlockCut.setStoneBlock(0, 0, 0, tier1);
		RitualRegistry.register(ritualBlockCut);

		ritualCreateWater.setStoneBlock(-1, 0, 1, tier1);
		ritualCreateWater.setStoneBlock(1, 0, -1, tier1);
		ritualCreateWater.setStoneBlock(-1, 0, -1, tier1);
		ritualCreateWater.setStoneBlock(1, 0, 1, tier1);
		ritualCreateWater.setStoneBlock(0, 0, 0, tier1);
		RitualRegistry.register(ritualCreateWater);

		ritualCreateLava.setStoneBlock(-1, 0, 1, tier2);
		ritualCreateLava.setStoneBlock(1, 0, -1, tier2);
		ritualCreateLava.setStoneBlock(-1, 0, -1, tier2);
		ritualCreateLava.setStoneBlock(1, 0, 1, tier2);
		ritualCreateLava.setStoneBlock(0, 0, 0, tier2);
		ritualCreateLava.setStoneBlock(1, -1, 0, tier1);
		ritualCreateLava.setStoneBlock(0, -1, 1, tier1);
		ritualCreateLava.setStoneBlock(0, -1, -1, tier1);
		ritualCreateLava.setStoneBlock(-1, -1, 0, tier1);
		RitualRegistry.register(ritualCreateLava);

		ritualInjection.setStoneBlock(0, 0, 0, tier2);
		ritualInjection.setStoneBlock(1, 1, 0, tier2);
		ritualInjection.setStoneBlock(0, 1, 1, tier2);
		ritualInjection.setStoneBlock(-1, 1, 0, tier2);
		ritualInjection.setStoneBlock(0, 1, -1, tier2);
		ritualInjection.setStoneBlock(0, 2, 0, tier2);
		RitualRegistry.register(ritualInjection);

		((RitualInjectionCore) ritualInjection).register.register("EI",
				new RitualInjection(BlockRegister.weakExperienceIronBlock, BlockRegister.experienceIronBlock));
		((RitualInjectionCore) ritualInjection).register.register("HFRB",
				new RitualInjection(Blocks.REDSTONE_BLOCK, BlockRegister.highFrequencyRedStone));
		((RitualInjectionCore) ritualInjection).register.register("cabinetStone",
				new RitualInjection(Blocks.END_STONE, BlockRegister.cabinetStone));

		ritualEggization.setStoneBlock(-1, 0, 1, tier1);
		ritualEggization.setStoneBlock(1, 0, -1, tier1);
		ritualEggization.setStoneBlock(-1, 0, -1, tier1);
		ritualEggization.setStoneBlock(1, 0, 1, tier1);
		ritualEggization.setStoneBlock(0, 0, 0, tier2);

		ritualEggization.setStoneBlock(1, 1, 0, tier3);
		ritualEggization.setStoneBlock(-1, 1, 0, tier3);
		ritualEggization.setStoneBlock(0, 1, 1, tier3);
		ritualEggization.setStoneBlock(0, 1, -1, tier3);

		RitualRegistry.register(ritualEggization);

		ritualSpawnerActivation.setStoneBlock(-1, 2, 1, tier2);
		ritualSpawnerActivation.setStoneBlock(1, 2, -1, tier2);
		ritualSpawnerActivation.setStoneBlock(-1, 2, -1, tier2);
		ritualSpawnerActivation.setStoneBlock(1, 2, 1, tier2);
		ritualSpawnerActivation.setStoneBlock(0, 0, 0, tier2);
		ritualSpawnerActivation.setStoneBlock(-1, 0, 1, tier3);
		ritualSpawnerActivation.setStoneBlock(1, 0, -1, tier3);
		ritualSpawnerActivation.setStoneBlock(-1, 0, -1, tier3);
		ritualSpawnerActivation.setStoneBlock(1, 0, 1, tier3);
		RitualRegistry.register(ritualSpawnerActivation);

		ritualCollection.setStoneBlock(0, 0, 0, tier1);
		ritualCollection.setStoneBlock(-2, -1, 2, tier1);
		ritualCollection.setStoneBlock(-2, 1, 2, tier1);

		ritualCollection.setStoneBlock(2, -1, -2, tier1);
		ritualCollection.setStoneBlock(2, 1, -2, tier1);

		ritualCollection.setStoneBlock(-2, -1, -2, tier1);
		ritualCollection.setStoneBlock(-2, 1, -2, tier1);

		ritualCollection.setStoneBlock(2, -1, 2, tier1);
		ritualCollection.setStoneBlock(2, 1, 2, tier1);
		RitualRegistry.register(ritualCollection);

		ritualCraft.setStoneBlock(-1, 0, -1, tier2);
		ritualCraft.setStoneBlock(-1, 0, 0, tier2);
		ritualCraft.setStoneBlock(-1, 0, 1, tier2);
		ritualCraft.setStoneBlock(0, 0, -1, tier2);
		ritualCraft.setStoneBlock(0, 0, 0, tier2);
		ritualCraft.setStoneBlock(0, 0, 1, tier2);
		ritualCraft.setStoneBlock(1, 0, -1, tier2);
		ritualCraft.setStoneBlock(1, 0, 0, tier2);
		ritualCraft.setStoneBlock(1, 0, 1, tier2);
		ritualCraft.setStoneBlock(-2, -1, -1, tier1);
		ritualCraft.setStoneBlock(-2, -1, 1, tier1);
		ritualCraft.setStoneBlock(2, -1, -1, tier1);
		ritualCraft.setStoneBlock(2, -1, 1, tier1);
		ritualCraft.setStoneBlock(-1, -1, -2, tier1);
		ritualCraft.setStoneBlock(1, -1, -2, tier1);
		ritualCraft.setStoneBlock(-1, -1, 2, tier1);
		ritualCraft.setStoneBlock(1, -1, 2, tier1);
		ritualCraft.setStoneBlock(2, 0, 2, tier1);
		ritualCraft.setStoneBlock(2, 0, -2, tier1);
		ritualCraft.setStoneBlock(-2, 0, 2, tier1);
		ritualCraft.setStoneBlock(-2, 0, -2, tier1);
		ritualCraft.setStoneBlock(2, 1, 2, tier1);
		ritualCraft.setStoneBlock(2, 1, -2, tier1);
		ritualCraft.setStoneBlock(-2, 1, 2, tier1);
		ritualCraft.setStoneBlock(-2, 1, -2, tier1);
		ritualCraft.setStoneBlock(2, 2, 2, tier1);
		ritualCraft.setStoneBlock(2, 2, -2, tier1);
		ritualCraft.setStoneBlock(-2, 2, 2, tier1);
		ritualCraft.setStoneBlock(-2, 2, -2, tier1);
		ritualCraft.setStoneBlock(-1, 3, -1, tier3);
		ritualCraft.setStoneBlock(-1, 3, 1, tier3);
		ritualCraft.setStoneBlock(1, 3, -1, tier3);
		ritualCraft.setStoneBlock(1, 3, 1, tier3);
		RitualRegistry.register(ritualCraft);

		RitualCraft pureexperience = new RitualCraft(new ArrayList<ItemStack>(),
				new ItemStack(BlockRegister.pureExperienceBlock));
		pureexperience.setIteminList(new ItemStack(BlockRegister.experienceIronBlock, 16));
		pureexperience.setIteminList(new ItemStack(BlockRegister.ritualStoneTier4));
		pureexperience.setIteminList(new ItemStack(ItemRegister.ashOfOrder, 1, 0));
		pureexperience.setIteminList(new ItemStack(Items.NETHER_STAR, 32));
		((RitualCraftCore) ritualCraft).register.register("PureExperience", pureexperience);

		RitualCraft timesand = new RitualCraft(new ArrayList<ItemStack>(), new ItemStack(ItemRegister.timeSand, 16));
		timesand.setIteminList(new ItemStack(Blocks.SAND, 16));
		timesand.setIteminList(new ItemStack(ItemRegister.ashOfBalance, 1, 0));
		((RitualCraftCore) ritualCraft).register.register("TimeSand", timesand);

		RitualCraft universalNutrient = new RitualCraft(new ArrayList<ItemStack>(),
				new ItemStack(ItemRegister.universalNutrient, 16));
		universalNutrient.setIteminList(new ItemStack(Blocks.BONE_BLOCK, 16));
		universalNutrient.setIteminList(new ItemStack(ItemRegister.ashOfBalance, 1, 0));
		((RitualCraftCore) ritualCraft).register.register("UniversalNutrient", universalNutrient);

		RitualCraft accelerateStone = new RitualCraft(new ArrayList<ItemStack>(),
				new ItemStack(BlockRegister.accelerateStone, 16));
		accelerateStone.setIteminList(new ItemStack(BlockRegister.cabinetStone, 16));
		accelerateStone.setIteminList(new ItemStack(ItemRegister.ashOfOrder, 1, 0));
		accelerateStone.setIteminList(new ItemStack(ItemRegister.timeSand, 4, 0));
		((RitualCraftCore) ritualCraft).register.register("AccelerateStone", accelerateStone);

		RitualCraft growthStone = new RitualCraft(new ArrayList<ItemStack>(),
				new ItemStack(BlockRegister.growthStone, 16));
		growthStone.setIteminList(new ItemStack(BlockRegister.cabinetStone, 16));
		growthStone.setIteminList(new ItemStack(ItemRegister.ashOfOrder, 1, 0));
		growthStone.setIteminList(new ItemStack(ItemRegister.universalNutrient, 4, 0));
		((RitualCraftCore) ritualCraft).register.register("GrowthStone", growthStone);

		RitualCraft AEIAxe = new RitualCraft(new ArrayList<ItemStack>(),
				new ItemStack(ItemRegister.advancedExperienceIronAxe, 1));
		AEIAxe.setIteminList(new ItemStack(ItemRegister.pureExperienceIngot, 1));
		AEIAxe.setIteminList(new ItemStack(ItemRegister.fragmentOfTheBrain, 1));
		AEIAxe.setIteminList(new ItemStack(ItemRegister.experienceIronAxe, 1));
		((RitualCraftCore) ritualCraft).register.register("AEIAxe", AEIAxe);

		RitualCraft AEIPickaxe = new RitualCraft(new ArrayList<ItemStack>(),
				new ItemStack(ItemRegister.advancedExperienceIronPickaxe, 1));
		AEIPickaxe.setIteminList(new ItemStack(ItemRegister.pureExperienceIngot, 1));
		AEIPickaxe.setIteminList(new ItemStack(ItemRegister.fragmentOfTheBrain, 1));
		AEIPickaxe.setIteminList(new ItemStack(ItemRegister.experienceIronPickaxe, 1));
		((RitualCraftCore) ritualCraft).register.register("AEIPickaxe", AEIPickaxe);

		RitualCraft AEISword = new RitualCraft(new ArrayList<ItemStack>(),
				new ItemStack(ItemRegister.advancedExperienceIronSword, 1));
		AEISword.setIteminList(new ItemStack(ItemRegister.pureExperienceIngot, 1));
		AEISword.setIteminList(new ItemStack(ItemRegister.fragmentOfTheBrain, 1));
		AEISword.setIteminList(new ItemStack(ItemRegister.experienceIronSword, 1));
		((RitualCraftCore) ritualCraft).register.register("AEISword", AEISword);

		RitualCraft AEIShovel = new RitualCraft(new ArrayList<ItemStack>(),
				new ItemStack(ItemRegister.advancedExperienceIronShovel, 1));
		AEIShovel.setIteminList(new ItemStack(ItemRegister.pureExperienceIngot, 1));
		AEIShovel.setIteminList(new ItemStack(ItemRegister.fragmentOfTheBrain, 1));
		AEIShovel.setIteminList(new ItemStack(ItemRegister.experienceIronShovel, 1));
		((RitualCraftCore) ritualCraft).register.register("AEIShovel", AEIShovel);

		RitualCraft AEIHelmet = new RitualCraft(new ArrayList<ItemStack>(),
				new ItemStack(ItemRegister.advancedExperienceIronHelmet, 1));
		AEIHelmet.setIteminList(new ItemStack(ItemRegister.pureExperienceIngot, 1));
		AEIHelmet.setIteminList(new ItemStack(ItemRegister.fragmentOfTheBrain, 1));
		AEIHelmet.setIteminList(new ItemStack(ItemRegister.experienceIronHelmet, 1));
		((RitualCraftCore) ritualCraft).register.register("AEIHelmet", AEIHelmet);

		RitualCraft AEIChestplate = new RitualCraft(new ArrayList<ItemStack>(),
				new ItemStack(ItemRegister.advancedExperienceIronChestplate, 1));
		AEIChestplate.setIteminList(new ItemStack(ItemRegister.pureExperienceIngot, 1));
		AEIChestplate.setIteminList(new ItemStack(ItemRegister.fragmentOfTheBrain, 1));
		AEIChestplate.setIteminList(new ItemStack(ItemRegister.experienceIronChestplate, 1));
		((RitualCraftCore) ritualCraft).register.register("AEIChestplate", AEIChestplate);

		RitualCraft AEILeggings = new RitualCraft(new ArrayList<ItemStack>(),
				new ItemStack(ItemRegister.advancedExperienceIronLeggings, 1));
		AEILeggings.setIteminList(new ItemStack(ItemRegister.pureExperienceIngot, 1));
		AEILeggings.setIteminList(new ItemStack(ItemRegister.fragmentOfTheBrain, 1));
		AEILeggings.setIteminList(new ItemStack(ItemRegister.experienceIronLeggings, 1));
		((RitualCraftCore) ritualCraft).register.register("AEILeggings", AEILeggings);

		RitualCraft AEIBoots = new RitualCraft(new ArrayList<ItemStack>(),
				new ItemStack(ItemRegister.advancedExperienceIronBoots, 1));
		AEIBoots.setIteminList(new ItemStack(ItemRegister.pureExperienceIngot, 1));
		AEIBoots.setIteminList(new ItemStack(ItemRegister.fragmentOfTheBrain, 1));
		AEIBoots.setIteminList(new ItemStack(ItemRegister.experienceIronBoots, 1));
		((RitualCraftCore) ritualCraft).register.register("AEIBoots", AEIBoots);

		ritualDehydration.setStoneBlock(0, 0, 0, tier1);
		ritualDehydration.setStoneBlock(1, 0, 2, tier1);
		ritualDehydration.setStoneBlock(-1, 0, 2, tier1);
		ritualDehydration.setStoneBlock(1, 0, -2, tier1);
		ritualDehydration.setStoneBlock(-1, 0, -2, tier1);
		ritualDehydration.setStoneBlock(2, 0, 1, tier1);
		ritualDehydration.setStoneBlock(2, 0, -1, tier1);
		ritualDehydration.setStoneBlock(-2, 0, 1, tier1);
		ritualDehydration.setStoneBlock(-2, 0, -1, tier1);
		ritualDehydration.setStoneBlock(0, 1, 1, tier1);
		ritualDehydration.setStoneBlock(0, 1, -1, tier1);
		ritualDehydration.setStoneBlock(1, 1, 0, tier1);
		ritualDehydration.setStoneBlock(-1, 1, 0, tier1);
		// RitualRegistry.register(ritualDehydration);

		((RitualDehydration) ritualDehydration).setRecipeAll(OreDictionary.getOres("logWood"),
				new ItemStack(Items.COAL, 9, 1));
		((RitualDehydration) ritualDehydration).setRecipeAll(OreDictionary.getOres("plankWood"),
				new ItemStack(Items.COAL, 1, 1));
		((RitualDehydration) ritualDehydration).setRecipeAll(OreDictionary.getOres("treeLeaves"),
				new ItemStack(Items.COAL, 1, 1));
		((RitualDehydration) ritualDehydration).setRecipeAll(OreDictionary.getOres("treeSapling"),
				new ItemStack(Blocks.DEADBUSH, 1));
		((RitualDehydration) ritualDehydration).setRecipe(new ItemStack(Blocks.GRASS), new ItemStack(Blocks.DIRT, 1));

		for (RitualDehydrationRecipe recipe : ((RitualDehydration) ritualDehydration).list) {
			System.out.println(recipe.getSource());
			System.out.println(recipe.getItem());
			System.out.println("////////////");
		}
	}
}