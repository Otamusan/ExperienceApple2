package Rituals.Rituals;

import Rituals.StonePosData;
import Util.ExperienceUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RitualCreateLava extends Ritual {

	public RitualCreateLava(StonePosData posData, String name) {
		super(posData, name);
	}

	@Override
	public void activate(EntityPlayer player, World world, BlockPos pos) {
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		ExperienceUtil.BlockPlaceOrDrop(x + 1, y, z + 1, Blocks.LAVA, world);
		ExperienceUtil.BlockPlaceOrDrop(x + 1, y, z - 1, Blocks.LAVA, world);
		ExperienceUtil.BlockPlaceOrDrop(x + 1, y, z, Blocks.LAVA, world);
		ExperienceUtil.BlockPlaceOrDrop(x - 1, y, z + 1, Blocks.LAVA, world);
		ExperienceUtil.BlockPlaceOrDrop(x - 1, y, z - 1, Blocks.LAVA, world);
		ExperienceUtil.BlockPlaceOrDrop(x - 1, y, z, Blocks.LAVA, world);
		ExperienceUtil.BlockPlaceOrDrop(x, y, z + 1, Blocks.LAVA, world);
		ExperienceUtil.BlockPlaceOrDrop(x, y, z - 1, Blocks.LAVA, world);
		ExperienceUtil.BlockPlaceOrDrop(x, y, z, Blocks.LAVA, world);
	}

	@Override
	public boolean canActivate(EntityPlayer player, World world, BlockPos pos) {
		return true;
	}

}
