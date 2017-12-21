package ExperienceApple.Register;

import static Rituals.RitualStones.*;

import Rituals.RitualRegistry;
import Rituals.StonePosData;
import Rituals.Rituals.Ritual;
import Rituals.Rituals.RitualBlockCut;
import Rituals.Rituals.RitualCreateEI;
import Rituals.Rituals.RitualCreateLava;
import Rituals.Rituals.RitualCreateWater;
import Rituals.Rituals.RitualExplode;

public class RitualRegister {
	public static void init() {
		Ritual ritualExplode = new RitualExplode(new StonePosData(), "Explode");
		ritualExplode.setStoneBlock(0, 0, 0, tier1);
		ritualExplode.setStoneBlock(0, 0, 1, tier1);
		ritualExplode.setStoneBlock(0, 0, -1, tier1);
		ritualExplode.setStoneBlock(-1, 0, 0, tier1);
		ritualExplode.setStoneBlock(1, 0, 0, tier1);
		RitualRegistry.register(ritualExplode);

		Ritual ritualBlockCut = new RitualBlockCut(new StonePosData(), "BlockCut");
		ritualBlockCut.setStoneBlock(0, -1, 1, tier1);
		ritualBlockCut.setStoneBlock(1, -1, 0, tier1);
		ritualBlockCut.setStoneBlock(0, -1, -1, tier1);
		ritualBlockCut.setStoneBlock(-1, -1, 0, tier1);
		ritualBlockCut.setStoneBlock(0, 0, 0, tier1);
		RitualRegistry.register(ritualBlockCut);

		Ritual ritualCreateWater = new RitualCreateWater(new StonePosData(), "CreateWater");
		ritualCreateWater.setStoneBlock(-1, 0, 1, tier1);
		ritualCreateWater.setStoneBlock(1, 0, -1, tier1);
		ritualCreateWater.setStoneBlock(-1, 0, -1, tier1);
		ritualCreateWater.setStoneBlock(1, 0, 1, tier1);
		ritualCreateWater.setStoneBlock(0, 0, 0, tier1);
		RitualRegistry.register(ritualCreateWater);

		Ritual ritualCreateLava = new RitualCreateLava(new StonePosData(), "CreateLava");
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

		Ritual ritualCreateEI = new RitualCreateEI(new StonePosData(), "CreateEI");
		ritualCreateEI.setStoneBlock(-1, 0, 1, tier2);
		ritualCreateEI.setStoneBlock(1, 0, -1, tier2);
		ritualCreateEI.setStoneBlock(-1, 0, -1, tier2);
		ritualCreateEI.setStoneBlock(1, 0, 1, tier2);
		ritualCreateEI.setStoneBlock(0, 0, 0, tier2);
		RitualRegistry.register(ritualCreateEI);
	}
}
