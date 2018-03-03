package Client;

import TileEntity.TileStorageRack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.math.MathHelper;

public class TileStorageRackRenderer extends TileEntitySpecialRenderer<TileStorageRack> {
	public void renderTileEntityAt(TileStorageRack te, double x, double y, double z, float partialTicks,
			int destroyStage) {

		if (te.getItemStack() != null) {
			GlStateManager.pushMatrix();
			GlStateManager.translate(x + 0.5, y + 0.7, z + 0.5);
			GlStateManager.translate(0,
					MathHelper.sin((te.getWorld().getTotalWorldTime() + partialTicks) / 10.0F) * 0.1F + 0.1F, 0);
			GlStateManager.scale(0.75, 0.75, 0.75);
			GlStateManager.enableLighting();

			float angle = (te.getWorld().getTotalWorldTime() + partialTicks) / 20.0F * (180F / (float) Math.PI);
			GlStateManager.rotate(angle, 0.0F, 1.0F, 0.0F);
			Minecraft.getMinecraft().getRenderItem().renderItem(te.getItemStack(),
					ItemCameraTransforms.TransformType.GROUND);
			GlStateManager.popMatrix();
		}
	}
}