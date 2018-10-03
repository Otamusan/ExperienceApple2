package Item;

import java.util.List;
import java.util.UUID;

import Util.ExperienceUtil;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemRegisteredExperienceApple extends Item {

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack itemstack) {
		return true;
	}

	@Override
	public void addInformation(ItemStack itemstack, World world, List<String> tooltip, ITooltipFlag flagIn) {
		NBTTagCompound nbt = itemstack.getTagCompound();
		if (nbt != null) {
			if (nbt.getString("registedPlayer") == "") {
				tooltip.add("§3" + I18n.format("item.registeredExperienceApple.registedPlayer") + " : " + "None");
			} else {
				if (world.getPlayerEntityByUUID(UUID.fromString(nbt.getString("registedPlayer"))) == null) {
					tooltip.add("§3" + I18n.format("item.registeredExperienceApple.registedPlayer") + " : "
							+ I18n.format("item.registeredExperienceApple.notlogin"));
					return;
				}
				tooltip.add("§3" + I18n.format("item.registeredExperienceApple.registedPlayer") + " : "
						+ world.getPlayerEntityByUUID(UUID.fromString(nbt.getString("registedPlayer")))
								.getDisplayNameString());
				tooltip.add("§3" + I18n.format("item.registeredExperienceApple.exppoint") + " : "
						+ ExperienceUtil.getExperiencePoints(
								world.getPlayerEntityByUUID(UUID.fromString(nbt.getString("registedPlayer")))));
			}
		}
	}

	public static EntityPlayer getPlayer(World world, ItemStack stack) {
		if (stack.getTagCompound() == null)
			return null;
		return world.getPlayerEntityByUUID(UUID.fromString(stack.getTagCompound().getString("registedPlayer")));
	}
}
