package Item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemGravityCompressor extends Item {
	public ItemGravityCompressor() {
		this.setContainerItem(this);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack itemstack) {
		return true;
	}
}
