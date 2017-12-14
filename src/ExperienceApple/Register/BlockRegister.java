package ExperienceApple.Register;

import Blocks.BlockAccelerateStone;
import Blocks.BlockAdvancedAccelerateStone;
import Blocks.BlockAdvancedGrowthStone;
import Blocks.BlockCabinetStone;
import Blocks.BlockCodensedExperienceOre;
import Blocks.BlockExperienceOre;
import Blocks.BlockGrowthStone;
import Blocks.BlockLiquidXP;
import Blocks.BlockPureExperience;
import Blocks.BlockRitualGlassTier1;
import Blocks.BlockRitualGlassTier2;
import Blocks.BlockRitualGlassTier3;
import Blocks.BlockRitualGlassTier4;
import Blocks.BlockRitualStoneTier1;
import Blocks.BlockRitualStoneTier2;
import Blocks.BlockRitualStoneTier3;
import Blocks.BlockRitualStoneTier4;
import ExperienceApple.EAMain;
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
	public static final Block ritualGlassTier1 = new BlockRitualGlassTier1(Material.GLASS).setHardness(0.3F);
	public static final Block ritualGlassTier2 = new BlockRitualGlassTier2(Material.GLASS).setHardness(0.3F);
	public static final Block ritualGlassTier3 = new BlockRitualGlassTier3(Material.GLASS).setHardness(0.3F);
	public static final Block ritualGlassTier4 = new BlockRitualGlassTier4(Material.GLASS).setHardness(0.3F);
	public static final Block ritualStoneTier1 = new BlockRitualStoneTier1(Material.ROCK).setHardness(5.0F);
	public static final Block ritualStoneTier2 = new BlockRitualStoneTier2(Material.ROCK).setHardness(5.0F);
	public static final Block ritualStoneTier3 = new BlockRitualStoneTier3(Material.ROCK).setHardness(5.0F);
	public static final Block ritualStoneTier4 = new BlockRitualStoneTier4(Material.ROCK).setHardness(5.0F);
	public static final Block cabinetStone = new BlockCabinetStone(Material.ROCK).setHardness(5.0F);

	public static final Block accelerateStone = new BlockAccelerateStone(Material.ROCK).setHardness(5.0F);
	public static final Block advancedAccelerateStone = new BlockAdvancedAccelerateStone(Material.ROCK)
			.setHardness(5.0F);

	public static final Block growthStone = new BlockGrowthStone(Material.ROCK).setHardness(5.0F);
	public static final Block advancedGrowthStone = new BlockAdvancedGrowthStone(Material.ROCK)
			.setHardness(5.0F);

	public static final Block weakExperienceIronBlock = new Block(Material.IRON).setHardness(5.0F);
	public static final Block experienceIronBlock = new Block(Material.IRON).setHardness(5.0F);
	public static final Block liquidXP = new BlockLiquidXP();
	public static final Block experienceOre = new BlockExperienceOre(Material.ROCK).setHardness(50);
	public static final Block condensedExperienceOre = new BlockCodensedExperienceOre(Material.ROCK).setHardness(5.0F)
			.setHardness(364);
	public static final Block pureExperienceBlock = new BlockPureExperience().setLightLevel(114514);

	public static Side Fside;

	public static void init(Side side) {

		Fside = side;

		register(liquidXP, "liquidXP");
		register(ritualGlassTier1, "ritualGlassTier1");
		register(ritualGlassTier2, "ritualGlassTier2");
		register(ritualGlassTier3, "ritualGlassTier3");
		register(ritualGlassTier4, "ritualGlassTier4");
		register(ritualStoneTier1, "ritualStoneTier1");
		register(ritualStoneTier2, "ritualStoneTier2");
		register(ritualStoneTier3, "ritualStoneTier3");
		register(ritualStoneTier4, "ritualStoneTier4");
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
