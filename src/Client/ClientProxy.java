package Client;

import Common.CommonProxy;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;

public class ClientProxy extends CommonProxy {
	@Override
	public World getClientWorld() {
		return FMLClientHandler.instance().getClient().theWorld;
	}

	@Override
	public void registerTileEntity() {
		super.registerTileEntity();
	}

	@Override
	public void registerEventHandlers() {
		super.registerEventHandlers();
		//MinecraftForge.EVENT_BUS.register(new TooltipHandler());
		//ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(BlockRegister.liquidXP), new ItemMeshDefinition() {
		//@Override
		//	public ModelResourceLocation getModelLocation(ItemStack stack) {
		//	return BlockLiquidXP.liquidXPLocation;
		//}
		//});
		/*ModelLoader.setCustomStateMapper(blockRegister.liquidXP, new StateMapperBase() {
			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState p_178132_1_) {
				return blockLiquidXP.liquidXPLocation;
			}
		});*/
	}
}