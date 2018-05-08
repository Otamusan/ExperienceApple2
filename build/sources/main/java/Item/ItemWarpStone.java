package Item;

import java.util.ArrayList;
import java.util.List;

import ExperienceApple.ITooltip;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemWarpStone extends Item implements ITooltip {
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {

		ItemStack itemStack = player.getHeldItem(hand);
		NBTTagCompound nbt = itemStack.getTagCompound();
		if (nbt == null) {
			nbt = new NBTTagCompound();

			nbt.setDouble("setX", player.posX);
			nbt.setDouble("setY", player.posY);
			nbt.setDouble("setZ", player.posZ);
			nbt.setInteger("setDimension", player.dimension);
			itemStack.setTagCompound(nbt);
			world.playSound(player.posX, player.posY, player.posZ, SoundEvents.BLOCK_ANVIL_USE, SoundCategory.PLAYERS,
					1, 1, true);
		} else {

			if (nbt.getInteger("setDimension") == player.dimension) {
				player.setPosition(nbt.getDouble("setX"), nbt.getDouble("setY"), nbt.getDouble("setZ"));
				world.playSound(player.posX, player.posY, player.posZ, SoundEvents.ENTITY_ENDERMEN_TELEPORT,
						SoundCategory.PLAYERS, 1, 1, true);
				itemStack.shrink(1);
			}
		}

		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack itemStack = playerIn.getHeldItem(hand);
		NBTTagCompound nbt = itemStack.getTagCompound();
		if (nbt == null) {
			nbt = new NBTTagCompound();

			nbt.setDouble("setX", pos.getX());
			nbt.setDouble("setY", pos.getY());
			nbt.setDouble("setZ", pos.getZ());
			nbt.setInteger("setDimension", playerIn.dimension);
			itemStack.setTagCompound(nbt);
			worldIn.playSound(playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.BLOCK_ANVIL_USE,
					SoundCategory.PLAYERS, 1, 1, true);
			return EnumActionResult.SUCCESS;
		}
		return EnumActionResult.FAIL;
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

				itemStack.shrink(1);
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

				itemStack.shrink(1);
			}
		}
		return true;
	}

	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag tooltipFlag) {
		NBTTagCompound nbt = stack.getTagCompound();
		if (!GuiScreen.isShiftKeyDown())
			return;
		if (nbt != null) {
			tooltip.add(I18n.format("item.warpStone.x") + " : " + (int) nbt.getDouble("setX") + " "
					+ I18n.format("item.warpStone.y") + " : " + (int) nbt.getDouble("setY") + " "
					+ I18n.format("item.warpStone.z") + " : " + (int) nbt.getDouble("setZ"));
			tooltip.add(I18n.format("item.warpStone.dimension") + " : " + nbt.getInteger("setDimention"));
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
}
