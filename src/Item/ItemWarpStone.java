package Item;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class ItemWarpStone extends Item {
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World world, EntityPlayer player,
			EnumHand hand) {
		ItemStack itemStack = player.getHeldItem(hand);
		NBTTagCompound nbt = itemStack.getTagCompound();
		if (nbt == null) {
			nbt = new NBTTagCompound();

			nbt.setDouble("setX", player.posX);
			nbt.setDouble("setY", player.posY);
			nbt.setDouble("setZ", player.posZ);
			nbt.setInteger("setDimension", player.dimension);
			itemStack.setTagCompound(nbt);
			world.playSound(player.posX, player.posY, player.posZ, SoundEvents.BLOCK_ANVIL_USE,
					SoundCategory.PLAYERS, 1, 1, true);
		} else {

			if (nbt.getInteger("setDimension") == player.dimension) {
				player.setPosition(nbt.getDouble("setX"), nbt.getDouble("setY"), nbt.getDouble("setZ"));
				world.playSound(player.posX, player.posY, player.posZ, SoundEvents.ENTITY_ENDERMEN_TELEPORT,
						SoundCategory.PLAYERS, 1, 1, true);
				itemStack.stackSize--;
			}
		}

		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
	}

	@Override
	public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer player, EntityLivingBase target,
			EnumHand hand) {
		ItemStack itemStack = player.getHeldItem(hand);
		NBTTagCompound nbt = itemStack.getTagCompound();
		World world = player.getEntityWorld();
		if (nbt == null) {
		} else {
			if (nbt.getInteger("setDimension") == target.dimension) {
				world.playSound(target.posX, target.posY, target.posZ, SoundEvents.ENTITY_ENDERMEN_TELEPORT,
						SoundCategory.NEUTRAL, 1, 1, true);
				target.setPosition(nbt.getDouble("setX"), nbt.getDouble("setY"), nbt.getDouble("setZ"));

				itemStack.stackSize--;
			}
		}
		return true;
	}

	@Override
	public boolean onLeftClickEntity(ItemStack itemStack, EntityPlayer player, Entity target) {
		NBTTagCompound nbt = itemStack.getTagCompound();
		World world = player.getEntityWorld();
		if (nbt == null) {
		} else {
			if (nbt.getInteger("setDimension") == target.dimension) {
				world.playSound(target.posX, target.posY, target.posZ, SoundEvents.ENTITY_ENDERMEN_TELEPORT,
						SoundCategory.NEUTRAL, 1, 1, true);
				target.setPosition(nbt.getDouble("setX"), nbt.getDouble("setY"), nbt.getDouble("setZ"));

				itemStack.stackSize--;
			}
		}
		return true;
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		NBTTagCompound nbt = stack.getTagCompound();
		if (nbt != null) {
			tooltip.add("X: " + (int) nbt.getDouble("setX") + " Y: " + (int) nbt.getDouble("setY") + " Z: "
					+ (int) nbt.getDouble("setZ"));
			tooltip.add("Dimension: " + nbt.getInteger("setDimention"));
		}
	}
}
