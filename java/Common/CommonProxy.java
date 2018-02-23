package Common;

import ExperienceApple.EAMain;
import TileEntity.TileAccelerateStone;
import TileEntity.TileAwakenedSpawner;
import TileEntity.TileGrowthStone;
import TileEntity.TileHighFrequencyRedStone;
import TileEntity.TileRitualLauncher;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy {
	public World getClientWorld() {
		return null;
	}

	public void registerTileEntity() {
		GameRegistry.registerTileEntity(TileAccelerateStone.class, EAMain.MOD_ID + "tileAccelerateStone");
		GameRegistry.registerTileEntity(TileGrowthStone.class, EAMain.MOD_ID + "tileGrowthStone");
		GameRegistry.registerTileEntity(TileRitualLauncher.class, EAMain.MOD_ID + "tileRitualLauncher");
		GameRegistry.registerTileEntity(TileHighFrequencyRedStone.class, EAMain.MOD_ID + "tileHighFrequencyRedStone");
		GameRegistry.registerTileEntity(TileAwakenedSpawner.class, EAMain.MOD_ID + "tileAwakenedSpawner");

	}

	public void registerEventHandlers() {
		FMLCommonHandler.instance().bus().register(new DeathEnderDragon());
		FMLCommonHandler.instance().bus().register(new DeathVillager());

	}
}