package Item;

import java.util.List;
import java.util.UUID;

import Util.ExperienceUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemRegisteredExperienceApple extends Item {

	public ItemRegisteredExperienceApple() {
		//this.setContainerItem(ItemRegister.registeredExperienceApple);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack itemstack) {
		return true;
	}

	@Override
	public void addInformation(ItemStack itemstack, EntityPlayer entityplayer, List<String> tooltip, boolean advanced) {
		NBTTagCompound nbt = itemstack.getTagCompound();
		if (nbt != null) {
			if (nbt.getString("registedPlayer") == "") {
				tooltip.add("Registered players : " + "None");
			} else {
				if (entityplayer.worldObj
						.getPlayerEntityByUUID(UUID.fromString(nbt.getString("registedPlayer"))) == null) {
					tooltip.add("Registered players : Registered players are not logged in");
					return;
				}
				tooltip.add("Registered players : "
						+ entityplayer.worldObj.getPlayerEntityByUUID(UUID.fromString(nbt.getString("registedPlayer")))
								.getDisplayNameString());
				tooltip.add("Experience Point : " + ExperienceUtil.getExperiencePoints(entityplayer));
			}
		}
	}

	/*@Override
	public ItemStack getContainerItem(ItemStack stack) {
		System.out.println(stack);
		return stack;
	}
	
	@Override
	public boolean hasContainerItem(ItemStack stack) {
		return true;
	}*/
}
