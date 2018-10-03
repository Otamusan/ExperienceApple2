package Common;

import Common.FlyingSpellPaper.HasItemUpdate;
import ExperienceApple.EAMain;
import TileEntity.TileAccelerateStone;
import TileEntity.TileAwakenedSpawner;
import TileEntity.TileEIHopper;
import TileEntity.TileGrowthStone;
import TileEntity.TileHighFrequencyRedStone;
import TileEntity.TileRackManager;
import TileEntity.TileRitualLauncher;
import TileEntity.TileStorageRack;
import TileEntity.TileWEIHopper;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy {
	public World getClientWorld() {
		return null;
	}

	public void registerTileEntity() {
		GameRegistry.registerTileEntity(TileAccelerateStone.class, EAMain.MOD_ID + ":tileAccelerateStone");
		GameRegistry.registerTileEntity(TileGrowthStone.class, EAMain.MOD_ID + ":tileGrowthStone");
		GameRegistry.registerTileEntity(TileRitualLauncher.class, EAMain.MOD_ID + ":tileRitualLauncher");
		GameRegistry.registerTileEntity(TileHighFrequencyRedStone.class, EAMain.MOD_ID + ":tileHighFrequencyRedStone");
		GameRegistry.registerTileEntity(TileAwakenedSpawner.class, EAMain.MOD_ID + ":tileAwakenedSpawner");
		GameRegistry.registerTileEntity(TileStorageRack.class, EAMain.MOD_ID + ":tileStorageRack");
		GameRegistry.registerTileEntity(TileWEIHopper.class, EAMain.MOD_ID + ":tileWEIHopper");
		GameRegistry.registerTileEntity(TileEIHopper.class, EAMain.MOD_ID + ":tileEIHopper");
		GameRegistry.registerTileEntity(TileRackManager.class, EAMain.MOD_ID + ":tileRackManager");
	}

	public void registerEventHandlers() {
		MinecraftForge.EVENT_BUS.register(new DeathEnderDragon());
		MinecraftForge.EVENT_BUS.register(new DeathVillager());
		MinecraftForge.EVENT_BUS.register(new HasItemUpdate());
		MinecraftForge.EVENT_BUS.register(new BlockLeftClick());
		MinecraftForge.EVENT_BUS.register(new PotionStateProtection());

	}
}