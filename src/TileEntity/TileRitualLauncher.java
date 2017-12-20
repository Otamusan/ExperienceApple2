package TileEntity;

import com.sun.istack.internal.Nullable;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileRitualLauncher extends TileEntity{
	public String Eplayer="null";
    public boolean previousRedstoneState;


    public void setString(String par1){
    	this.Eplayer = par1;
    }

    public String getString(){
        return this.Eplayer;
    }

	public NBTTagCompound writeToNBT(NBTTagCompound par1NBTTagCompound){
	    super.writeToNBT(par1NBTTagCompound);
	    par1NBTTagCompound.setString("String", this.Eplayer);
	    par1NBTTagCompound.setBoolean("powered", this.previousRedstoneState);

		return par1NBTTagCompound;
	}

	public void readFromNBT(NBTTagCompound par1NBTTagCompound){
        super.readFromNBT(par1NBTTagCompound);
        this.Eplayer = par1NBTTagCompound.getString("String");
        this.previousRedstoneState = par1NBTTagCompound.getBoolean("powered");
    }
	@Nullable
    public SPacketUpdateTileEntity getUpdatePacket()
    {
        return new SPacketUpdateTileEntity(this.pos, 0, this.getUpdateTag());
    }
	public NBTTagCompound getUpdateTag()
    {
		return this.writeToNBT(new NBTTagCompound());
    }

}
