package TileEntity;

import java.util.Random;

import ExperienceApple.Register.BlockRegister;
import Util.ParticleUtil;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileGrowthStone extends TileEntity implements ITickable {

	@Override
	public void update() {
		World world = this.worldObj;
		int x = this.pos.getX();
		int y = this.pos.getY();
		int z = this.pos.getZ();
		accelerate(world.getTileEntity(new BlockPos(x + 1, y, z)), world, x + 1, y, z);
		accelerate(world.getTileEntity(new BlockPos(x - 1, y, z)), world, x - 1, y, z);
		accelerate(world.getTileEntity(new BlockPos(x, y + 1, z)), world, x, y + 1, z);
		accelerate(world.getTileEntity(new BlockPos(x, y - 1, z)), world, x, y - 1, z);
		accelerate(world.getTileEntity(new BlockPos(x, y, z + 1)), world, x, y, z + 1);
		accelerate(world.getTileEntity(new BlockPos(x, y, z - 1)), world, x, y, z - 1);
	}

	public void accelerate(TileEntity entity, World world, int x, int y, int z) {
		ParticleUtil.ball(EnumParticleTypes.DRIP_WATER, world, pos.getX() + 0.6, pos.getY() + 0.6, pos.getZ() + 0.6,
				1.7, 1);
		if (world.getBlockState(new BlockPos(x, y, z)).getBlock() == BlockRegister.accelerateStone)
			return;
		if (world.getBlockState(new BlockPos(x, y, z)).getBlock() == BlockRegister.advancedAccelerateStone)
			return;

		if (world.isRemote)
			return;
		for (int ix = 0; ix < 1; ix++) {
			world.getBlockState(new BlockPos(x, y, z)).getBlock().updateTick(world, new BlockPos(x, y, z),
					world.getBlockState(new BlockPos(x, y, z)), new Random());
		}
	}

	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		final NBTTagCompound nbt = new NBTTagCompound();
		this.writeToNBT(nbt);
		return new SPacketUpdateTileEntity(getPos(), 543, nbt);
	}

	@Override
	public void onDataPacket(final NetworkManager net, final SPacketUpdateTileEntity pkt) {
		super.onDataPacket(net, pkt);
		this.readFromNBT(pkt.getNbtCompound());
	}

	@Override
	public boolean shouldRefresh(final World world, final BlockPos pos, final IBlockState oldState,
			final IBlockState newState) {
		return oldState.getBlock() != newState.getBlock();
	}
}
