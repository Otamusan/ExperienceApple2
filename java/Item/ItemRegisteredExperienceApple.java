package Item;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import ExperienceApple.ITooltip;
import Util.ExperienceUtil;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemRegisteredExperienceApple extends Item implements ITooltip {

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack itemstack) {
		return true;
	}

	@Override
	public void addInformation(ItemStack itemstack, EntityPlayer entityplayer, List<String> tooltip, boolean advanced) {
		if (!GuiScreen.isShiftKeyDown())
			return;
		NBTTagCompound nbt = itemstack.getTagCompound();
		if (nbt != null) {
			if (nbt.getString("registedPlayer") == "") {
				tooltip.add(I18n.format("item.registeredExperienceApple.registedPlayer") + " : " + "None");
			} else {
				if (entityplayer.worldObj
						.getPlayerEntityByUUID(UUID.fromString(nbt.getString("registedPlayer"))) == null) {
					tooltip.add(I18n.format("item.registeredExperienceApple.registedPlayer") + " : "
							+ I18n.format("item.registeredExperienceApple.notlogin"));
					return;
				}
				tooltip.add(I18n.format("item.registeredExperienceApple.registedPlayer") + " : "
						+ entityplayer.worldObj.getPlayerEntityByUUID(UUID.fromString(nbt.getString("registedPlayer")))
								.getDisplayNameString());
				tooltip.add(I18n.format("item.registeredExperienceApple.exppoint") + " : "
						+ ExperienceUtil.getExperiencePoints(entityplayer));
			}
		}
	}

	public List<String> Tooltip = new ArrayList<String>();

	@Override
	public List<String> getTooltip() {
		return Tooltip;
	}

	@Override
	public void addTooltip(String str) {
		Tooltip.add(str);
	}
	/*
	 * @Override public ItemStack getContainerItem(ItemStack stack) {
	 * System.out.println(stack); return stack; }
	 * 
	 * @Override public boolean hasContainerItem(ItemStack stack) { return true;
	 * }
	 */
}
