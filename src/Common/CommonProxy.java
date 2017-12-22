package Common;

import Common.FlyingSpellPaper.HasItemUpdate;
import ExperienceApple.EAMain;
import TileEntity.TileAccelerateStone;
import TileEntity.TileAdvancedAccelerateStone;
import TileEntity.TileAdvancedGrowthStone;
import TileEntity.TileGrowthStone;
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
		GameRegistry.registerTileEntity(TileAdvancedAccelerateStone.class,
				EAMain.MOD_ID + "tileAdvancedAccelerateStone");
		GameRegistry.registerTileEntity(TileGrowthStone.class, EAMain.MOD_ID + "tileGrowthStone");
		GameRegistry.registerTileEntity(TileAdvancedGrowthStone.class, EAMain.MOD_ID + "tileAdvancedGrowthStone");
		GameRegistry.registerTileEntity(TileRitualLauncher.class, EAMain.MOD_ID + "tileRitualLauncher");

	}

	public void registerEventHandlers() {
		FMLCommonHandler.instance().bus().register(new HasItemUpdate());
		FMLCommonHandler.instance().bus().register(new DeathEnderDragon());
	}
}