package ExperienceApple.Register;

import ExperienceApple.EAMain;
import Potion.PotionFly;
import Potion.PotionRetention;
import Potion.PotionSatiety;
import Potion.PotionStateProtection;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class PotionRegister {
	public static final Potion potionRetention = new PotionRetention(
			new ResourceLocation(EAMain.MOD_ID, "textures/potion/retention.png"));
	public static final Potion potionStateProtection = new PotionStateProtection(
			new ResourceLocation(EAMain.MOD_ID, "textures/potion/stateProtection.png"));
	public static final Potion potionSatiety = new PotionSatiety(
			new ResourceLocation(EAMain.MOD_ID, "textures/potion/satiety.png"));
	public static final Potion potionFly = new PotionFly(
			new ResourceLocation(EAMain.MOD_ID, "textures/potion/fly.png"));
	public static PotionType retentionNormal;
	public static PotionType retentionLong;
	public static PotionType stateProtectionNormal;
	public static PotionType stateProtectionLong;

	public static void init() {
		registerPotion(potionRetention, "retention");
		registerPotion(potionStateProtection, "stateProtection");
		registerPotion(potionSatiety, "satiety");
		registerPotion(potionFly, "fly");

		retentionNormal = registerPotionType(potionRetention, 0, 3600, "retention");
		retentionLong = registerPotionType(potionRetention, 0, 9600, "retentionLong");

		stateProtectionNormal = registerPotionType(potionStateProtection, 0, 3600, "stateProtection");
		stateProtectionLong = registerPotionType(potionStateProtection, 0, 9600, "stateProtectionLong");

	}

	public static void registerPotion(Potion potion, String name) {
		potion.setRegistryName(new ResourceLocation(EAMain.MOD_ID, name));
		ForgeRegistries.POTIONS.register(potion);

	}

	public static PotionType registerPotionType(Potion potion, int amp, int du, String name) {
		PotionType potionType = new PotionType(name, new PotionEffect[] { new PotionEffect(potion, du, amp) });
		potionType.setRegistryName(new ResourceLocation(EAMain.MOD_ID, name));
		ForgeRegistries.POTION_TYPES.register(potionType);
		return potionType;
	}

	public static ItemStack addPotionToItemStack(ItemStack itemIn, PotionType potionIn) {
		ResourceLocation resourcelocation = potionIn.getRegistryName();

		if (resourcelocation != null) {
			NBTTagCompound nbttagcompound = itemIn.hasTagCompound() ? itemIn.getTagCompound() : new NBTTagCompound();
			nbttagcompound.setString("Potion", resourcelocation.toString());
			itemIn.setTagCompound(nbttagcompound);
		}

		return itemIn;
	}
}
