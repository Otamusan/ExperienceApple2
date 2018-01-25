package Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import ExperienceApple.ITooltip;
import ExperienceApple.Register.BlockRegister;
import Rituals.RitualRegistry;
import Rituals.RitualStones;
import Util.PlayerUtil;
import net.minecraft.block.Block;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class ItemRitualAssembler extends Item implements ITooltip {
	public void addInformation(ItemStack itemstack, EntityPlayer entityplayer, List<String> tooltip, boolean advanced) {
		if (!GuiScreen.isShiftKeyDown())
			return;
		NBTTagCompound nbt = itemstack.getTagCompound();
		nbt = checknbt(nbt);
		String str = "ea.ritual." + RitualRegistry.list.get(nbt.getInteger("ritual")).getName() + ".name";
		tooltip.add(I18n.format(str));

		String str2 = "ea.ritual." + RitualRegistry.list.get(nbt.getInteger("ritual")).getName() + ".description";
		tooltip.add(I18n.format(str2));
	}

	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn,
			EnumHand hand) {
		if (worldIn.isRemote)
			return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemStackIn);
		NBTTagCompound nbt = itemStackIn.getTagCompound();
		checknbt(nbt);
		int ritual = nbt.getInteger("ritual");

		ritual++;
		if (ritual == RitualRegistry.list.size()) {
			ritual = 0;
		}
		String str = "ea.ritual." + RitualRegistry.list.get(ritual).getName() + ".name";

		playerIn.addChatMessage(new TextComponentTranslation(I18n.format(str)));
		nbt.setInteger("ritual", ritual);
		itemStackIn.setTagCompound(nbt);
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemStackIn);
	}

	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if (!(entityIn instanceof EntityPlayer))
			return;
		NBTTagCompound nbt = stack.getTagCompound();
		nbt = checknbt(nbt);
		nbt.setBoolean("isplace", false);
		nbt.setString("player", entityIn.getUniqueID().toString());
		stack.setTagCompound(nbt);
	}

	public boolean onEntityItemUpdate(net.minecraft.entity.item.EntityItem entityItem) {
		NBTTagCompound nbt = entityItem.getEntityItem().getTagCompound();
		nbt = checknbt(nbt);

		Map<BlockPos, RitualStones> posDatas = RitualRegistry.list.get(nbt.getInteger("ritual")).getStoneBlockData();

		if (entityItem.worldObj.isRemote)
			return false;

		if (nbt.getBoolean("isplace"))
			return false;
		if (nbt.getString("player") == "")
			return false;
		if (entityItem.worldObj.getPlayerEntityByUUID(UUID.fromString(nbt.getString("player"))) == null)
			return false;
		if (!entityItem.onGround)
			return false;
		EntityPlayer player = entityItem.worldObj.getPlayerEntityByUUID(UUID.fromString(nbt.getString("player")));
		for (Map.Entry<BlockPos, RitualStones> dataPosEntry : posDatas.entrySet()) {
			BlockPos dataPos = dataPosEntry.getKey();
			// entityItem.worldObj
			// .setBlockState(
			// new BlockPos(dataPos.getX() + entityItem.posX, dataPos.getY() +
			// entityItem.posY,
			// dataPos.getZ() + entityItem.posZ),
			// getRitualBlock(posDatas.get(dataPos)).getDefaultState());
			setBlock(player, player.worldObj, getRitualBlock(posDatas.get(dataPos)),
					new BlockPos(dataPos.getX() + entityItem.posX, dataPos.getY() + entityItem.posY,
							dataPos.getZ() + entityItem.posZ));
		}
		nbt.setBoolean("isplace", true);
		ItemStack itemStack = entityItem.getEntityItem();
		InventoryPlayer inv = entityItem.worldObj
				.getPlayerEntityByUUID(UUID.fromString(nbt.getString("player"))).inventory;
		inv.addItemStackToInventory(entityItem.getEntityItem());
		entityItem.setDead();
		itemStack.setTagCompound(nbt);
		entityItem.setEntityItemStack(itemStack);
		return false;
	}

	public boolean setBlock(EntityPlayer player, World world, Block block, BlockPos pos) {
		if (world.getBlockState(pos).getBlock() != Blocks.AIR)
			return false;
		ItemStack itemStack = PlayerUtil.getHasPlayerItem(player, Item.getItemFromBlock(block));
		if (itemStack == null)
			return false;
		if (itemStack.stackSize < 1)
			return false;
		itemStack.stackSize--;
		if (itemStack.stackSize == 0) {
			for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
				if (player.inventory.getStackInSlot(i) == null)
					continue;
				if (player.inventory.getStackInSlot(i).getItem() == Item.getItemFromBlock(block)) {
					player.inventory.setInventorySlotContents(i, null);
				}
			}
		}
		world.setBlockState(pos, block.getDefaultState());
		return true;
	}

	public Block getRitualBlock(RitualStones ritualenum) {
		switch (ritualenum) {
		case tier1:
			return BlockRegister.ritualGlassTier1;
		case tier2:
			return BlockRegister.ritualGlassTier2;
		case tier3:
			return BlockRegister.ritualGlassTier3;
		case tier4:
			return BlockRegister.ritualGlassTier4;
		}
		return null;
	}

	public NBTTagCompound checknbt(NBTTagCompound nbt) {
		if (nbt == null) {
			nbt = new NBTTagCompound();
		}
		return nbt;
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
