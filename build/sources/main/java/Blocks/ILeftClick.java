package Blocks;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface ILeftClick {
	public void onLeftClick(EntityPlayer player, BlockPos pos, World world, EnumFacing facing);
}
