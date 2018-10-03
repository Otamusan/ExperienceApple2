package Rituals.Rituals.RitualCraft;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RitualCraft {
	public ArrayList<ItemStack> itemlist;
	public ItemStack item;

	public RitualCraft(ArrayList<ItemStack> itemlist, ItemStack createitem) {
		this.itemlist = itemlist;
		this.item = createitem;
	}

	public boolean canActivate(EntityPlayer player, World world, BlockPos pos) {
		return true;
	}

	public ArrayList<ItemStack> getItemlist() {
		return itemlist;
	}

	public void setItemlist(ArrayList<ItemStack> itemlist) {
		this.itemlist = itemlist;
	}

	public void setIteminList(ItemStack itemStack) {
		this.itemlist.add(itemStack);
	}

	public ItemStack getItem() {
		return item;
	}

	public void setItem(ItemStack item) {
		this.item = item;
	}
}
