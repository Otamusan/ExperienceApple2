package TileEntity;

import net.minecraft.tileentity.TileEntityHopper;

public class TileWEIHopper extends TileEntityHopper {
	@Override
	public void update() {
		super.update();
		setTransferCooldown(0);

	}

}
