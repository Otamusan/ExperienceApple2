package ExperienceApple.Register;

import static Rituals.RitualStones.*;

import java.util.ArrayList;

import Rituals.RitualRegistry;
import Rituals.StonePosData;
import Rituals.Rituals.Ritual;
import Rituals.Rituals.RitualBlockCut;
import Rituals.Rituals.RitualCreateEI;
import Rituals.Rituals.RitualCreateLava;
import Rituals.Rituals.RitualCreateWater;
import Rituals.Rituals.RitualEggization;
import Rituals.Rituals.RitualExplode;
import Rituals.Rituals.RitualSpawnerActivation;
import Rituals.Rituals.RitualCraft.RitualCraft;
import Rituals.Rituals.RitualCraft.RitualCraftCore;
import Rituals.Rituals.RitualCraft.RitualCraftRegister;
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

		Ritual ritualCreateEI = new RitualCreateEI(new StonePosData(), "createEI");
		ritualCreateEI.setStoneBlock(-1, 0, 1, tier2);
		ritualCreateEI.setStoneBlock(1, 0, -1, tier2);
		ritualCreateEI.setStoneBlock(-1, 0, -1, tier2);
		ritualCreateEI.setStoneBlock(1, 0, 1, tier2);
		ritualCreateEI.setStoneBlock(0, 0, 0, tier2);
		RitualRegistry.register(ritualCreateEI);

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
		ritualSpawnerActivation.setStoneBlock(-1, 2, 1, tier1);
		ritualSpawnerActivation.setStoneBlock(1, 2, -1, tier1);
		ritualSpawnerActivation.setStoneBlock(-1, 2, -1, tier1);
		ritualSpawnerActivation.setStoneBlock(1, 2, 1, tier1);
		ritualSpawnerActivation.setStoneBlock(0, 0, 0, tier2);
		ritualSpawnerActivation.setStoneBlock(-1, 0, 1, tier1);
		ritualSpawnerActivation.setStoneBlock(1, 0, -1, tier1);
		ritualSpawnerActivation.setStoneBlock(-1, 0, -1, tier1);
		ritualSpawnerActivation.setStoneBlock(1, 0, 1, tier1);
		RitualRegistry.register(ritualSpawnerActivation);

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

		RitualCraft cabinetstone = new RitualCraft(new ArrayList<ItemStack>(),
				new ItemStack(BlockRegister.cabinetStone));
		cabinetstone.setIteminList(new ItemStack(Items.APPLE));
		cabinetstone.setIteminList(new ItemStack(Items.GOLDEN_APPLE));
		cabinetstone.setIteminList(new ItemStack(ItemRegister.experienceApple));
		cabinetstone.setIteminList(new ItemStack(ItemRegister.warpStone));
		RitualCraftRegister.register("Cabinetstone", cabinetstone);

		RitualCraft pureexperience = new RitualCraft(new ArrayList<ItemStack>(),
				new ItemStack(BlockRegister.pureExperienceBlock));
		pureexperience.setIteminList(new ItemStack(BlockRegister.experienceIronBlock, 16));
		pureexperience.setIteminList(new ItemStack(BlockRegister.ritualStoneTier4));
		pureexperience.setIteminList(new ItemStack(ItemRegister.ashOfOrder, 1, 0));
		pureexperience.setIteminList(new ItemStack(Items.NETHER_STAR, 32));
		RitualCraftRegister.register("PureExperience", pureexperience);
	}
}
