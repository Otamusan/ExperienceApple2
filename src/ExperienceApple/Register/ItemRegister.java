package ExperienceApple.Register;

import ExperienceApple.EAMain;
import Item.ItemAshOfBalance;
import Item.ItemAshOfEntropy;
import Item.ItemAshOfOrder;
import Item.ItemDebug;
import Item.ItemEnchantmentPearl;
import Item.ItemExperienceApple;
import Item.ItemFragmentOfTheBrain;
import Item.ItemGravityCompressor;
import Item.ItemRegisteredExperienceApple;
import Item.ItemSuperDye;
import Item.ItemTimeSand;
import Item.ItemUniversalNutrient;
import Item.ItemWarpStone;
import Item.Armors.ItemAdvancedExperienceIronArmor;
import Item.Armors.ItemExperienceIronArmor;
import Item.Armors.ItemWeakExperienceIronArmor;
import Item.SpellPapers.ItemAcceleratedSpellPaper;
import Item.SpellPapers.ItemFlyingSpellPaper;
import Item.SpellPapers.ItemSatietySpellPaper;
import Item.Tools.ItemAdvancedExperienceIronAxe;
import Item.Tools.ItemAdvancedExperienceIronPickaxe;
import Item.Tools.ItemAdvancedExperienceIronShovel;
import Item.Tools.ItemAdvancedExperienceIronSword;
import Item.Tools.ItemAtomicCutter;
import Item.Tools.ItemExperienceIronAxe;
import Item.Tools.ItemExperienceIronPickaxe;
import Item.Tools.ItemExperienceIronShovel;
import Item.Tools.ItemExperienceIronSword;
import Item.Tools.ItemFeatherSword;
import Item.Tools.ItemMagicBow;
import Item.Tools.ItemUnstableAxe;
import Item.Tools.ItemWeakExperienceIronAxe;
import Item.Tools.ItemWeakExperienceIronPickaxe;
import Item.Tools.ItemWeakExperienceIronShovel;
import Item.Tools.ItemWeakExperienceIronSword;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemFood;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

public class ItemRegister {
	public static final ItemArmor.ArmorMaterial WEIArmorMaterial = EnumHelper.addArmorMaterial("EAWEI",
			"weakexperienceiron", 15, new int[] { 2, 5, 6, 2 }, 9, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0f);
	public static final ItemArmor.ArmorMaterial EIArmorMaterial = EnumHelper.addArmorMaterial("EAEI", "experienceiron",
			3, new int[] { 4, 7, 9, 4 }, 18, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0f);

	public static final ItemArmor.ArmorMaterial AEIArmorMaterial = EnumHelper.addArmorMaterial("EAAEI",
			"advancedexperienceiron", 0, new int[] { 5, 8, 10, 5 }, 18, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0f);

	public static final ToolMaterial EIToolMaterial = EnumHelper.addToolMaterial("EI", 9, 15, 100F, 20F, 3);
	public static final ToolMaterial FToolMaterial = EnumHelper.addToolMaterial("Feather", 1, 200, 100F, 0F, 3);
	public static final ToolMaterial AEIToolMaterial = EnumHelper.addToolMaterial("AEI", 15, 0, 1000F, 20F, 3);

	public static final Item experienceApple = new ItemExperienceApple().setMaxStackSize(1);
	public static final Item gravityCompressor = new ItemGravityCompressor().setMaxStackSize(1);
	public static final Item enchantmentPearl = new ItemEnchantmentPearl().setMaxStackSize(1);

	public static final Item ashOfEntropy = new ItemAshOfEntropy().setMaxStackSize(1);
	public static final Item ashOfBalance = new ItemAshOfBalance().setMaxStackSize(1);
	public static final Item ashOfOrder = new ItemAshOfOrder().setMaxStackSize(1);

	public static final int flyingSpellPaperCost = 5;
	public static final Item flyingSpellPaper = new ItemFlyingSpellPaper().setMaxStackSize(1);
	public static final Item satietySpellPaper = new ItemSatietySpellPaper().setMaxStackSize(1);
	public static final Item acceleratedSpellPaper = new ItemAcceleratedSpellPaper().setMaxStackSize(1);

	public static final Item warpStone = new ItemWarpStone();

	public static final Item registeredExperienceApple = new ItemRegisteredExperienceApple().setMaxStackSize(1);

	public static final Item unstableAxe = new ItemUnstableAxe().setMaxDamage(0);
	public static final Item featherSword = new ItemFeatherSword();
	public static final Item magicBow = new ItemMagicBow();
	public static final Item atomicCutter = new ItemAtomicCutter().setMaxStackSize(1);

	public static final Item weakExperienceIronAxe = new ItemWeakExperienceIronAxe();
	public static final Item weakExperienceIronPickaxe = new ItemWeakExperienceIronPickaxe();
	public static final Item weakExperienceIronShovel = new ItemWeakExperienceIronShovel();
	public static final Item weakExperienceIronSword = new ItemWeakExperienceIronSword();
	public static final Item weakExperienceIronIngot = new Item();

	public static final Item weakExperienceIronHelmet = new ItemWeakExperienceIronArmor(WEIArmorMaterial, 0,
			EntityEquipmentSlot.HEAD);
	public static final Item weakExperienceIronChestplate = new ItemWeakExperienceIronArmor(WEIArmorMaterial, 0,
			EntityEquipmentSlot.CHEST);
	public static final Item weakExperienceIronLeggings = new ItemWeakExperienceIronArmor(WEIArmorMaterial, 0,
			EntityEquipmentSlot.LEGS);
	public static final Item weakExperienceIronBoots = new ItemWeakExperienceIronArmor(WEIArmorMaterial, 0,
			EntityEquipmentSlot.FEET);

	public static final Item pureExperienceIngot = new Item();
	public static final Item experienceIronIngot = new Item();

	public static final Item timeSand = new ItemTimeSand();
	public static final Item universalNutrient = new ItemUniversalNutrient();

	public static final Item fragmentOfTheBrain = new ItemFragmentOfTheBrain();

	public static final Item superDye = new ItemSuperDye();

	public static final Item experienceIronAxe = new ItemExperienceIronAxe(EIToolMaterial, 60, 5);
	public static final Item experienceIronPickaxe = new ItemExperienceIronPickaxe(EIToolMaterial, 60, 5);
	public static final Item experienceIronShovel = new ItemExperienceIronShovel(EIToolMaterial, 60, 5);
	public static final Item experienceIronSword = new ItemExperienceIronSword(EIToolMaterial, 60, 5);

	public static final Item advancedExperienceIronAxe = new ItemAdvancedExperienceIronAxe(AEIToolMaterial);
	public static final Item advancedExperienceIronPickaxe = new ItemAdvancedExperienceIronPickaxe(AEIToolMaterial);
	public static final Item advancedExperienceIronShovel = new ItemAdvancedExperienceIronShovel(AEIToolMaterial);
	public static final Item advancedExperienceIronSword = new ItemAdvancedExperienceIronSword(AEIToolMaterial);

	public static final Item experienceIronHelmet = new ItemExperienceIronArmor(EIArmorMaterial, 0,
			EntityEquipmentSlot.HEAD, 60, 5);
	public static final Item experienceIronChestplate = new ItemExperienceIronArmor(EIArmorMaterial, 0,
			EntityEquipmentSlot.CHEST, 60, 5);
	public static final Item experienceIronLeggings = new ItemExperienceIronArmor(EIArmorMaterial, 0,
			EntityEquipmentSlot.LEGS, 60, 5);
	public static final Item experienceIronBoots = new ItemExperienceIronArmor(EIArmorMaterial, 0,
			EntityEquipmentSlot.FEET, 60, 5);

	public static final Item advancedExperienceIronHelmet = new ItemAdvancedExperienceIronArmor(AEIArmorMaterial, 0,
			EntityEquipmentSlot.HEAD, 80);
	public static final Item advancedExperienceIronChestplate = new ItemAdvancedExperienceIronArmor(AEIArmorMaterial, 0,
			EntityEquipmentSlot.CHEST, 80);
	public static final Item advancedExperienceIronLeggings = new ItemAdvancedExperienceIronArmor(AEIArmorMaterial, 0,
			EntityEquipmentSlot.LEGS, 80);
	public static final Item advancedExperienceIronBoots = new ItemAdvancedExperienceIronArmor(AEIArmorMaterial, 0,
			EntityEquipmentSlot.FEET, 80);

	public static final Item bakedSeeds = new ItemFood(1, 0, true);
	public static final Item infusedBakedSeeds = new ItemFood(1, 1, true);

	public static Side Fside;

	public static final Item debug = new ItemDebug();

	public static void init(Side side) {

		Fside = side;

		register(ashOfEntropy, "ashOfEntropy");
		register(ashOfBalance, "ashOfBalance");
		register(ashOfOrder, "ashOfOrder");

		register(flyingSpellPaper, "flyingSpellPaper");
		register(satietySpellPaper, "satietySpellPaper");
		// register(acceleratedSpellPaper, "acceleratedSpellPaper");

		register(experienceApple, "experienceApple");
		register(registeredExperienceApple, "registeredExperienceApple");
		register(enchantmentPearl, "enchantmentPearl");
		register(superDye, "superDye");

		register(featherSword, "featherSword");
		register(magicBow, "magicBow");
		register(unstableAxe, "unstableAxe");
		register(atomicCutter, "atomicCutter");

		register(weakExperienceIronAxe, "weakExperienceIronAxe");
		register(weakExperienceIronPickaxe, "weakExperienceIronPickaxe");
		register(weakExperienceIronShovel, "weakExperienceIronShovel");
		register(weakExperienceIronSword, "weakExperienceIronSword");

		register(weakExperienceIronIngot, "weakExperienceIronIngot");

		register(weakExperienceIronHelmet, "weakExperienceIronHelmet");
		register(weakExperienceIronChestplate, "weakExperienceIronChestplate");
		register(weakExperienceIronLeggings, "weakExperienceIronLeggings");
		register(weakExperienceIronBoots, "weakExperienceIronBoots");
		register(gravityCompressor, "gravityCompressor");

		register(pureExperienceIngot, "pureExperienceIngot");
		register(experienceIronIngot, "experienceIronIngot");

		register(timeSand, "timeSand");
		register(universalNutrient, "universalNutrient");

		register(warpStone, "warpStone");

		register(fragmentOfTheBrain, "fragmentOfTheBrain");

		register(experienceIronAxe, "experienceIronAxe");
		register(experienceIronPickaxe, "experienceIronPickaxe");
		register(experienceIronShovel, "experienceIronShovel");
		register(experienceIronSword, "experienceIronSword");

		register(advancedExperienceIronAxe, "advancedExperienceIronAxe");
		register(advancedExperienceIronPickaxe, "advancedExperienceIronPickaxe");
		register(advancedExperienceIronShovel, "advancedExperienceIronShovel");
		register(advancedExperienceIronSword, "advancedExperienceIronSword");

		register(experienceIronHelmet, "experienceIronHelmet");
		register(experienceIronChestplate, "experienceIronChestplate");
		register(experienceIronLeggings, "experienceIronLeggings");
		register(experienceIronBoots, "experienceIronBoots");

		register(advancedExperienceIronHelmet, "advancedExperienceIronHelmet");
		register(advancedExperienceIronChestplate, "advancedExperienceIronChestplate");
		register(advancedExperienceIronLeggings, "advancedExperienceIronLeggings");
		register(advancedExperienceIronBoots, "advancedExperienceIronBoots");

		register(infusedBakedSeeds, "infusedBakedSeeds");
		register(bakedSeeds, "bakedSeeds");

		register(debug, "debug");

		OreDictionary.registerOre("dyeBlack", superDye);
		OreDictionary.registerOre("dyeRed", superDye);
		OreDictionary.registerOre("dyeGreen", superDye);
		OreDictionary.registerOre("dyeBrown", superDye);
		OreDictionary.registerOre("dyeBlue", superDye);
		OreDictionary.registerOre("dyePurple", superDye);
		OreDictionary.registerOre("dyeCyan", superDye);
		OreDictionary.registerOre("dyeLightGray", superDye);
		OreDictionary.registerOre("dyeGray", superDye);
		OreDictionary.registerOre("dyePink", superDye);
		OreDictionary.registerOre("dyeLime", superDye);
		OreDictionary.registerOre("dyeYellow", superDye);
		OreDictionary.registerOre("dyeLightBlue", superDye);
		OreDictionary.registerOre("dyeMagenta", superDye);
		OreDictionary.registerOre("dyeOrange", superDye);
		OreDictionary.registerOre("dyeWhite", superDye);
	}

	public static void register(Item item, String str) {
		GameRegistry.register(item, new ResourceLocation(EAMain.MOD_ID + ":" + str));
		if (Fside == Side.CLIENT) {
			modelRegister(item, str);
		}
		item.setUnlocalizedName(str);
		item.setCreativeTab(EAMain.tabAdd);
	}

	@SideOnly(Side.CLIENT)
	public static void modelRegister(Item item, String str) {
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(EAMain.MOD_ID + ":" + str));
	}
}
