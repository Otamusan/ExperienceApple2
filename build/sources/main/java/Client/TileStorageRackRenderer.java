package Client;

import TileEntity.TileStorageRack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

public class TileStorageRackRenderer extends TileEntitySpecialRenderer<TileStorageRack> {
	public void renderTileEntityAt(TileStorageRack te, double x, double y, double z, float partialTicks,
			int destroyStage) {

	}

	@Override
	public void render(TileStorageRack te, double x, double y, double z, float partialTicks, int destroyStage,
			float alpha) {
		if (!te.getItemStack().isEmpty()) {
			GlStateManager.pushMatrix();
			GlStateManager.translate(x + 0.5, y + 0.4, z + 0.5);
			GlStateManager.scale(0.8, 0.8, 0.8);
			GlStateManager.enableLighting();

			float angle = (te.getWorld().getTotalWorldTime() + partialTicks) / 20.0F * (180F / (float) Math.PI);
			GlStateManager.rotate(angle, 0.0F, 1.0F, 0.0F);
			Minecraft.getMinecraft().getRenderItem().renderItem(te.getItemStack(),
					ItemCameraTransforms.TransformType.GROUND);
			GlStateManager.popMatrix();
		}
		if (!te.getItemStack().isEmpty()) {
			this.drawNameplate(te, te.getItemStack().getDisplayName() + ":" + te.getItemAmount(), x, y - 0.5, z, 20);
		}
	}
}