package Item;

import ExperienceApple.Register.ItemRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemExperienceApple extends Item {
	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack itemstack) {
		return false;
	}

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		ItemStack itemstack = new ItemStack(ItemRegister.registeredExperienceApple);
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setString("registedPlayer", entityIn.getPersistentID().toString());
		itemstack.setTagCompound(nbt);
		EntityPlayer player = (EntityPlayer) entityIn;
		player.inventory.setInventorySlotContents(itemSlot, itemstack);
	}
}
