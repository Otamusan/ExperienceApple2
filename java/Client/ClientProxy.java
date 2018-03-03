package Client;

import Common.CommonProxy;
import TileEntity.TileStorageRack;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class ClientProxy extends CommonProxy {
	@Override
	public World getClientWorld() {
		return FMLClientHandler.instance().getClient().theWorld;
	}

	@Override
	public void registerTileEntity() {
		super.registerTileEntity();
		ClientRegistry.bindTileEntitySpecialRenderer(TileStorageRack.class, new TileStorageRackRenderer());
	}

	@Override
	public void registerEventHandlers() {
		super.registerEventHandlers();
		FMLCommonHandler.instance().bus().register(new TooltipHandler());
	}
}