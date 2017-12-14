package Item;

import net.minecraft.item.Item;

public class ItemDebug extends Item {
	/*
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
	
		return new ActionResult<ItemStack>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
	}
	
	@Override
	public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer player, EntityLivingBase target,
			EnumHand hand) {
		NBTTagCompound nbt = new NBTTagCompound();
	
		nbt.setString("entity", EntityList.getKey(target).toString());
		stack.setTagCompound(nbt);
		return true;
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack itemStack = player.getHeldItem(hand);
		NBTTagCompound nbt = itemStack.getTagCompound();
		if (nbt == null) {
			return EnumActionResult.PASS;
		}
	
		EntityLivingBase entity = (EntityLivingBase) worldIn.getEntityByID(nbt.getInteger("entity"));
		entity.setPosition(pos.getX(), pos.getY(), pos.getZ());
	
		worldIn.spawnEntity(entity);
		return EnumActionResult.SUCCESS;
	
	}
	*/

}
