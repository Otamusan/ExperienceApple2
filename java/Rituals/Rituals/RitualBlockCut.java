package Rituals.Rituals;

import Rituals.StonePosData;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RitualBlockCut extends Ritual {

	public RitualBlockCut(StonePosData posData, String name) {
		super(posData, name);
	}

	@Override
	public void activate(EntityPlayer player, World world, BlockPos pos) {
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();

		if (!world.isRemote) {
			ItemStack itemstack = new ItemStack(
					Item.getItemFromBlock(world.getBlockState(new BlockPos(x, y - 1, z)).getBlock()));
			EntityItem itementity = new EntityItem(world, x, y - 1, z, itemstack);
			world.spawnEntityInWorld(itementity);
			world.setBlockToAir(new BlockPos(x, y - 1, z));
		}
	}

	@Override
	public boolean canActivate(EntityPlayer player, World world, BlockPos pos) {
		return true;
	}

}
