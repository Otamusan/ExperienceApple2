package Client;

import Client.Particle.TextureStitcherBreathFX;
import Common.CommonProxy;
import TileEntity.TileStorageRack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {
	@Override
	public World getClientWorld() {
		return FMLClientHandler.instance().getClient().world;
	}

	@Override
	public void registerTileEntity() {
		super.registerTileEntity();
		ClientRegistry.bindTileEntitySpecialRenderer(TileStorageRack.class, new TileStorageRackRenderer());
	}

	@Override
	public void registerEventHandlers() {
		super.registerEventHandlers();
		MinecraftForge.EVENT_BUS.register(new TextureStitcherBreathFX());
		MinecraftForge.EVENT_BUS.register(new TooltipHandler());
		MinecraftForge.EVENT_BUS.register(new EACraftingEvent());
		MinecraftForge.EVENT_BUS.register(new RepairCraftingEvent());

	}
}