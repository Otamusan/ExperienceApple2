package ExperienceApple;

import java.awt.Color;
import java.io.File;

import Common.CommonProxy;
import ExperienceApple.Register.BlockRegister;
import ExperienceApple.Register.EntityRegister;
import ExperienceApple.Register.ItemRegister;
import ExperienceApple.Register.PotionRegister;
import ExperienceApple.Register.RecipeRegister;
import ExperienceApple.Register.RitualRegister;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

@Mod(modid = EAMain.MOD_ID, name = EAMain.MOD_NAME, version = EAMain.MOD_VERSION, acceptedMinecraftVersions = EAMain.MOD_ACCEPTED_MC_VERSIONS, useMetadata = true)
public class EAMain {
	public static final String MOD_ID = "experienceapple2";
	public static final String MOD_NAME = "ExperienceApple2";
	public static final String MOD_VERSION = "0.0.1";
	public static final String MOD_ACCEPTED_MC_VERSIONS = "[1.12.2]";

	@SidedProxy(clientSide = "Client.ClientProxy", serverSide = "Common.CommonProxy")
	public static CommonProxy proxy;
	public static final CreativeTabs tabAdd = new TabExperienceApple("experienceApple");
	public static boolean particle;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {

		Configuration cfg = new Configuration(new File("config/ExperienceApple.cfg"));
		try {
			cfg.load();
			particle = cfg.getBoolean("particle", "client", false, "disableParticles?");
		} finally {
			cfg.save();
		}

		ItemRegister.init(event.getSide());
		BlockRegister.init(event.getSide());
		RitualRegister.init();
		EntityRegister.init(this);
		PotionRegister.init();
		RecipeRegister.init();

	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new IItemColor() {
			@Override
			public int colorMultiplier(ItemStack stack, int tintIndex) {

				if (stack.getEnchantmentTagList() != null) {
					int EnchID = stack.getEnchantmentTagList().getCompoundTagAt(0).getShort("id");
					int EnchMAX = ForgeRegistries.ENCHANTMENTS.getKeys().size();
					float hue = (float) ((float) EnchID / (float) EnchMAX);
					return Color.getHSBColor(hue, 1.0F, 1.0F).getRGB();
				}
				return Color.getHSBColor(0.0f, 0.0F, 1.0F).getRGB();
			}
		}, ItemRegister.enchantmentPearl);
		proxy.registerTileEntity();
		proxy.registerEventHandlers();

	}
}