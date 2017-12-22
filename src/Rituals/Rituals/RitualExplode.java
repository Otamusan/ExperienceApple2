package Rituals.Rituals;

import Rituals.StonePosData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RitualExplode extends Ritual {

	public RitualExplode(StonePosData posData, String name) {
		super(posData, name);
	}

	@Override
	public void activate(EntityPlayer player, World world, BlockPos pos) {
		world.createExplosion(player, pos.getX(), pos.getY(), pos.getZ(), 5.0F, true);
	}

	@Override
	public boolean canActivate(EntityPlayer player, World world, BlockPos pos) {
		return true;
	}
}
