package ExperienceApple;

import java.io.File;

import Common.CommonProxy;
import Crafting.EnchantCraft.EnchantCrafting;
import Crafting.ExperienceAppleCrafting.ExperienceAppleCrafting;
import Crafting.RepairCraft.RepairCrafting;
import ExperienceApple.Register.BlockRegister;
import ExperienceApple.Register.EntityRegister;
import ExperienceApple.Register.ItemRegister;
import ExperienceApple.Register.RecipeRegister;
import ExperienceApple.Register.RitualRegister;
import World.ExperienceOreGenerator;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = EAMain.MOD_ID, name = EAMain.MOD_NAME, version = EAMain.MOD_VERSION, acceptedMinecraftVersions = EAMain.MOD_ACCEPTED_MC_VERSIONS, useMetadata = true)
public class EAMain {
	public static final String MOD_ID = "experienceapple2";
	public static final String MOD_NAME = "ExperienceApple2";
	public static final String MOD_VERSION = "0.0.1";
	public static final String MOD_ACCEPTED_MC_VERSIONS = "[1.10]";

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
		RecipeRegister.init();
		RitualRegister.init();
		EntityRegister.init(this);
		GameRegistry.addRecipe(new ExperienceAppleCrafting());
		GameRegistry.addRecipe(new RepairCrafting());
		GameRegistry.addRecipe(new EnchantCrafting());

		GameRegistry.registerWorldGenerator(new ExperienceOreGenerator(), 0);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {

		proxy.registerTileEntity();
		proxy.registerEventHandlers();

	}
}