package TileEntity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileHighFrequencyRedStone extends TileEntity implements ITickable {

	public int MAX_RED = 10;
	public int MAX_NONE = 10;
	public int red = 0;
	public int none = 1;
	public boolean isPower = false;

	public TileHighFrequencyRedStone() {

	}

	@Override
	public void update() {
		if (!worldObj.isRemote) {
			if (none == MAX_NONE) {
				none = 0;
				isPower = true;
				// for (EnumFacing enumfacing : EnumFacing.values()) {
				// worldObj.notifyNeighborsOfStateChange(pos.offset(enumfacing),
				// blockType);
				// }
				worldObj.notifyNeighborsOfStateChange(pos, this.getBlockType());
				return;

			}
			if (red == MAX_RED) {
				red = 0;
				isPower = false;
				// for (EnumFacing enumfacing : EnumFacing.values()) {
				// worldObj.notifyNeighborsOfStateChange(pos.offset(enumfacing),
				// blockType);
				// }
				worldObj.notifyNeighborsOfStateChange(pos, this.getBlockType());
				return;

			}
			if (!isPower) {
				none++;
			} else {
				red++;
			}
		}
	}

	public NBTTagCompound writeToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setInteger("red", this.red);
		par1NBTTagCompound.setInteger("none", this.none);
		par1NBTTagCompound.setInteger("max_red", this.MAX_RED);
		par1NBTTagCompound.setInteger("max_none", this.MAX_NONE);
		par1NBTTagCompound.setBoolean("isPower", this.isPower);
		return par1NBTTagCompound;
	}

	public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readFromNBT(par1NBTTagCompound);
		this.red = par1NBTTagCompound.getInteger("red");
		this.none = par1NBTTagCompound.getInteger("none");
		this.MAX_RED = par1NBTTagCompound.getInteger("max_red");
		this.MAX_NONE = par1NBTTagCompound.getInteger("max_none");
		this.isPower = par1NBTTagCompound.getBoolean("isPower");
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
