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
import Rituals.Rituals.RitualInjection.RitualInjection;
import Rituals.Rituals.RitualInjection.RitualInjectionCore;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class RitualRegister {
	public static void init() {
		Ritual ritualExplode = new RitualExplode(new StonePosData(), "explode");
		ritualExplode.setStoneBlock(0, 0, 0, tier1);
		ritualExplode.setStoneBlock(0, 0, 1, tier1);
		ritualExplode.setStoneBlock(0, 0, -1, tier1);
		ritualExplode.setStoneBlock(-1, 0, 0, tier1);
		ritualExplode.setStoneBlock(1, 0, 0, tier1);
		RitualRegistry.register(ritualExplode);

		Ritual ritualBlockCut = new RitualBlockCut(new StonePosData(), "blockCut");
		ritualBlockCut.setStoneBlock(0, -1, 1, tier1);
		ritualBlockCut.setStoneBlock(1, -1, 0, tier1);
		ritualBlockCut.setStoneBlock(0, -1, -1, tier1);
		ritualBlockCut.setStoneBlock(-1, -1, 0, tier1);
		ritualBlockCut.setStoneBlock(0, 0, 0, tier1);
		RitualRegistry.register(ritualBlockCut);

		Ritual ritualCreateWater = new RitualCreateWater(new StonePosData(), "createWater");
		ritualCreateWater.setStoneBlock(-1, 0, 1, tier1);
		ritualCreateWater.setStoneBlock(1, 0, -1, tier1);
		ritualCreateWater.setStoneBlock(-1, 0, -1, tier1);
		ritualCreateWater.setStoneBlock(1, 0, 1, tier1);
		ritualCreateWater.setStoneBlock(0, 0, 0, tier1);
		RitualRegistry.register(ritualCreateWater);

		Ritual ritualCreateLava = new RitualCreateLava(new StonePosData(), "createLava");
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

		Ritual ritualInjection = new RitualInjectionCore(new StonePosData(), "injection");
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

		Ritual ritualEggization = new RitualEggization(new StonePosData(), "eggization");
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

		Ritual ritualSpawnerActivation = new RitualSpawnerActivation(new StonePosData(), "spawnerActivation");
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

		Ritual ritualCollection = new RitualCollection(new StonePosData(), "collection");
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

		Ritual ritualCraft = new RitualCraftCore(new StonePosData(), "ritualCraft");
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
	}
}
