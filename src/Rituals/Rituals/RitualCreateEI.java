package Rituals.Rituals;

import ExperienceApple.Register.BlockRegister;
import Rituals.StonePosData;
import Util.ExperienceUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RitualCreateEI extends Ritual {

	public RitualCreateEI(StonePosData posData, String name) {
		super(posData, name);
	}

	@Override
	public void activate(EntityPlayer player, World world, BlockPos pos) {
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		world.setBlockToAir(new BlockPos(x + 1, y, z));
		world.setBlockToAir(new BlockPos(x - 1, y, z));
		world.setBlockToAir(new BlockPos(x, y, z + 1));
		world.setBlockToAir(new BlockPos(x, y, z - 1));
		ExperienceUtil.BlockPlaceOrDrop(x, y, z, BlockRegister.experienceIronBlock, world);
	}

	@Override
	public boolean canActivate(EntityPlayer player, World world, BlockPos pos) {
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		if (world.getBlockState(new BlockPos(x + 1, y, z)).getBlock() != BlockRegister.weakExperienceIronBlock)
			return false;
		if (world.getBlockState(new BlockPos(x - 1, y, z)).getBlock() != BlockRegister.weakExperienceIronBlock)
			return false;
		if (world.getBlockState(new BlockPos(x, y, z + 1)).getBlock() != BlockRegister.weakExperienceIronBlock)
			return false;
		if (world.getBlockState(new BlockPos(x, y, z - 1)).getBlock() != BlockRegister.weakExperienceIronBlock)
			return false;

		return true;
	}
}
