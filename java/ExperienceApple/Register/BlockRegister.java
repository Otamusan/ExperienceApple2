package ExperienceApple.Register;

import Blocks.BlockAccelerateStone;
import Blocks.BlockCabinetStone;
import Blocks.BlockCodensedExperienceOre;
import Blocks.BlockExperienceOre;
import Blocks.BlockGrowthStone;
import Blocks.BlockMod;
import Blocks.BlockPath;
import Blocks.BlockPureExperience;
import Blocks.BlockRitualGlass;
import Blocks.BlockRitualLauncher;
import Blocks.BlockRitualStone;
import ExperienceApple.EAMain;
import Rituals.RitualStones;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockRegister {
	public static final Block ritualGlassTier1 = new BlockRitualGlass(Material.GLASS, 1, RitualStones.tier1)
			.setHardness(0.3F);
	public static final Block ritualGlassTier2 = new BlockRitualGlass(Material.GLASS, 1, RitualStones.tier2)
			.setHardness(0.3F);
	public static final Block ritualGlassTier3 = new BlockRitualGlass(Material.GLASS, 1, RitualStones.tier3)
			.setHardness(0.3F);
	public static final Block ritualGlassTier4 = new BlockRitualGlass(Material.GLASS, 1, RitualStones.tier4)
			.setHardness(0.3F);
	public static final Block ritualStoneTier1 = new BlockRitualStone(Material.ROCK, 1, RitualStones.tier1)
			.setHardness(100).setResistance(100);
	public static final Block ritualStoneTier2 = new BlockRitualStone(Material.ROCK, 1, RitualStones.tier2)
			.setHardness(100).setResistance(100);
	public static final Block ritualStoneTier3 = new BlockRitualStone(Material.ROCK, 1, RitualStones.tier3)
			.setHardness(100).setResistance(100);
	public static final Block ritualStoneTier4 = new BlockRitualStone(Material.ROCK, 1, RitualStones.tier4)
			.setHardness(100).setResistance(100);
	public static final Block ritualLauncherTier1 = new BlockRitualLauncher(Material.ROCK, 1, RitualStones.tier1)
			.setHardness(100).setResistance(100);
	public static final Block ritualLauncherTier2 = new BlockRitualLauncher(Material.ROCK, 1, RitualStones.tier2)
			.setHardness(100).setResistance(100);
	public static final Block ritualLauncherTier3 = new BlockRitualLauncher(Material.ROCK, 1, RitualStones.tier3)
			.setHardness(100).setResistance(100);
	public static final Block ritualLauncherTier4 = new BlockRitualLauncher(Material.ROCK, 1, RitualStones.tier4)
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
	public static final Block path = new BlockPath().setLightLevel(15);

	public static Side Fside;

	public static void init(Side side) {

		Fside = side;
		// ((ITooltip) ritualGlassTier1).addTooltip("testtest");
		register(ritualGlassTier1, "ritualGlassTier1");
		register(ritualGlassTier2, "ritualGlassTier2");
		register(ritualGlassTier3, "ritualGlassTier3");
		register(ritualGlassTier4, "ritualGlassTier4");
		register(ritualStoneTier1, "ritualStoneTier1");
		register(ritualStoneTier2, "ritualStoneTier2");
		register(ritualStoneTier3, "ritualStoneTier3");
		register(ritualStoneTier4, "ritualStoneTier4");
		register(ritualLauncherTier1, "ritualLauncherTier1");
		register(ritualLauncherTier2, "ritualLauncherTier2");
		register(ritualLauncherTier3, "ritualLauncherTier3");
		register(ritualLauncherTier4, "ritualLauncherTier4");
		register(cabinetStone, "cabinetStone");

		register(accelerateStone, "accelerateStone");
		register(advancedAccelerateStone, "advancedAccelerateStone");

		register(growthStone, "growthStone");
		register(advancedGrowthStone, "advancedGrowthStone");

		register(weakExperienceIronBlock, "weakExperienceIronBlock");
		register(experienceOre, "experienceOre");
		register(condensedExperienceOre, "condensedExperienceOre");
		register(experienceIronBlock, "experienceIronBlock");
		register(pureExperienceBlock, "pureExperienceBlock");
		register(path, "path");

	}

	public static void register(Block block, String str) {
		GameRegistry.register(block, new ResourceLocation(EAMain.MOD_ID + ":" + str));
		block.setUnlocalizedName(str);
		block.setCreativeTab(EAMain.tabAdd);

		ItemBlock item = new ItemBlock(block);
		GameRegistry.register(item, new ResourceLocation(EAMain.MOD_ID + ":" + str));
		item.setUnlocalizedName(str);
		item.setCreativeTab(EAMain.tabAdd);

		if (Fside == Side.CLIENT) {
			modelRegister(item, str);
		}
	}

	@SideOnly(Side.CLIENT)
	public static void modelRegister(Item item, String str) {
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(EAMain.MOD_ID + ":" + str));
	}
}
