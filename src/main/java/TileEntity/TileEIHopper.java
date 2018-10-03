package TileEntity;

import net.minecraft.tileentity.TileEntityHopper;

public class TileEIHopper extends TileEntityHopper {
	@Override
	public void update() {

		for (int i = 0; i < 64; i++) {
			setTransferCooldown(0);
			this.updateHopper();
			setTransferCooldown(0);
		}
	}

}
