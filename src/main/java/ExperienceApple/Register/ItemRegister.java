package ExperienceApple.Register;

import java.util.ArrayList;
import java.util.List;

import ExperienceApple.EAMain;
import Item.ItemAshIngot;
import Item.ItemAshOfBalance;
import Item.ItemAshOfEntropy;
import Item.ItemAshOfOrder;
import Item.ItemDebug;
import Item.ItemEnchantmentPearl;
import Item.ItemExperienceApple;
import Item.ItemFragmentOfTheBrain;
import Item.ItemGravityCompressor;
import Item.ItemMod;
import Item.ItemModFood;
import Item.ItemRegisteredExperienceApple;
import Item.ItemRitualAssembler;
import Item.ItemSuperDye;
import Item.ItemTimeSand;
import Item.ItemUniversalNutrient;
import Item.ItemWarpStone;
import Item.Armors.ItemAdvancedExperienceIronArmor;
import Item.Armors.ItemExperienceIronArmor;
import Item.Armors.ItemWeakExperienceIronArmor;
import Item.SpellPapers.ItemSpellPapers;
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
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

public class ItemRegister {
	public static List<Item> descriptionlist = new ArrayList<>();

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

	public static final Item ashOfEntropy = new ItemAshOfEntropy().setMaxStackSize(1).setMaxDamage(100);
	public static final Item entropyIngot = new ItemAshIngot();
	public static final Item ashOfBalance = new ItemAshOfBalance().setMaxStackSize(1).setMaxDamage(1000);;
	public static final Item balanceIngot = new ItemAshIngot();
	public static final Item ashOfOrder = new ItemAshOfOrder().setMaxStackSize(1).setMaxDamage(10000);
	public static final Item orderIngot = new ItemAshIngot();

	public static final Item flyingSpellPaper = new ItemSpellPapers(PotionRegister.potionFly, 5, 0).setMaxStackSize(1);
	public static final Item satietySpellPaper = new ItemSpellPapers(PotionRegister.potionSatiety, 1, 0)
			.setMaxStackSize(1);

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
	public static final Item weakExperienceIronIngot = new ItemMod();

	public static final Item weakExperienceIronHelmet = new ItemWeakExperienceIronArmor(WEIArmorMaterial, 0,
			EntityEquipmentSlot.HEAD);
	public static final Item weakExperienceIronChestplate = new ItemWeakExperienceIronArmor(WEIArmorMaterial, 0,
			EntityEquipmentSlot.CHEST);
	public static final Item weakExperienceIronLeggings = new ItemWeakExperienceIronArmor(WEIArmorMaterial, 0,
			EntityEquipmentSlot.LEGS);
	public static final Item weakExperienceIronBoots = new ItemWeakExperienceIronArmor(WEIArmorMaterial, 0,
			EntityEquipmentSlot.FEET);

	public static final Item pureExperienceIngot = new ItemMod();
	public static final Item experienceIronIngot = new ItemMod();

	public static final Item timeSand = new ItemTimeSand(100);
	public static final Item universalNutrient = new ItemUniversalNutrient(20);

	public static final Item fragmentOfTheBrain = new ItemFragmentOfTheBrain(10000);

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

	public static final Item ritualAssembler = new ItemRitualAssembler();
	public static final Item bakedSeeds = new ItemModFood(1, 0, true, 4);
	public static final Item infusedBakedSeeds = new ItemModFood(1, 1, true, 4);

	public static Side Fside;

	public static final Item debug = new ItemDebug();

	public static void init(Side side) {
		Fside = side;

		register(ashOfEntropy, "ashOfEntropy", true);
		register(entropyIngot, "entropyIngot", false);
		register(ashOfBalance, "ashOfBalance", true);
		register(balanceIngot, "balanceIngot", false);
		register(ashOfOrder, "ashOfOrder", true);
		register(orderIngot, "orderIngot", false);

		register(flyingSpellPaper, "flyingSpellPaper", true);
		register(satietySpellPaper, "satietySpellPaper", true);
		// register(acceleratedSpellPaper, "acceleratedSpellPaper");

		register(experienceApple, "experienceApple", true);
		register(registeredExperienceApple, "registeredExperienceApple", true);
		register(enchantmentPearl, "enchantmentPearl", true);
		register(superDye, "superDye", true);

		register(featherSword, "featherSword", true);
		register(magicBow, "magicBow", true);
		register(unstableAxe, "unstableAxe", true);
		register(atomicCutter, "atomicCutter", true);

		register(weakExperienceIronAxe, "weakExperienceIronAxe", true);
		register(weakExperienceIronPickaxe, "weakExperienceIronPickaxe", true);
		register(weakExperienceIronShovel, "weakExperienceIronShovel", true);
		register(weakExperienceIronSword, "weakExperienceIronSword", true);

		register(weakExperienceIronIngot, "weakExperienceIronIngot", false);

		register(weakExperienceIronHelmet, "weakExperienceIronHelmet", true);
		register(weakExperienceIronChestplate, "weakExperienceIronChestplate", true);
		register(weakExperienceIronLeggings, "weakExperienceIronLeggings", true);
		register(weakExperienceIronBoots, "weakExperienceIronBoots", true);
		register(gravityCompressor, "gravityCompressor", false);

		register(pureExperienceIngot, "pureExperienceIngot", true);
		register(experienceIronIngot, "experienceIronIngot", false);

		register(timeSand, "timeSand", true);
		register(universalNutrient, "universalNutrient", true);

		register(warpStone, "warpStone", true);

		register(fragmentOfTheBrain, "fragmentOfTheBrain", true);

		register(experienceIronAxe, "experienceIronAxe", true);
		register(experienceIronPickaxe, "experienceIronPickaxe", true);
		register(experienceIronShovel, "experienceIronShovel", true);
		register(experienceIronSword, "experienceIronSword", true);

		register(advancedExperienceIronAxe, "advancedExperienceIronAxe", true);
		register(advancedExperienceIronPickaxe, "advancedExperienceIronPickaxe", true);
		register(advancedExperienceIronShovel, "advancedExperienceIronShovel", true);
		register(advancedExperienceIronSword, "advancedExperienceIronSword", true);

		register(experienceIronHelmet, "experienceIronHelmet", true);
		register(experienceIronChestplate, "experienceIronChestplate", true);
		register(experienceIronLeggings, "experienceIronLeggings", true);
		register(experienceIronBoots, "experienceIronBoots", true);

		register(advancedExperienceIronHelmet, "advancedExperienceIronHelmet", true);
		register(advancedExperienceIronChestplate, "advancedExperienceIronChestplate", true);
		register(advancedExperienceIronLeggings, "advancedExperienceIronLeggings", true);
		register(advancedExperienceIronBoots, "advancedExperienceIronBoots", true);

		register(infusedBakedSeeds, "infusedBakedSeeds", true);
		register(bakedSeeds, "bakedSeeds", true);
		register(ritualAssembler, "ritualAssembler", true);

		register(debug, "debug", false);

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

		OreDictionary.registerOre("ingotExperienceIron", experienceIronIngot);
		OreDictionary.registerOre("ingotWeakExperienceIron", weakExperienceIronIngot);
		OreDictionary.registerOre("ingotPureExperience", pureExperienceIngot);
	}

	public static void register(Item item, String str, boolean hasDescription) {
		if (hasDescription) {
			descriptionlist.add(item);
		}
		item.setRegistryName(new ResourceLocation(EAMain.MOD_ID + ":" + str));
		ForgeRegistries.ITEMS.register(item);
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
