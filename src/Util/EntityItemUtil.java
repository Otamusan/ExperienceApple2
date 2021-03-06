package Util;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityItemUtil {
	public static ItemList getItemListFromPos(World world, BlockPos pos) {

		List<Entity> entitylist = world.loadedEntityList;
		ItemList itemList = new ItemList(new ArrayList<ItemStack>());
		for (Entity entity : entitylist) {
			if (entity.getClass() == EntityItem.class) {
				EntityItem entityItem = (EntityItem) entity;

				if (entity.getPosition().getX() == pos.getX() && entity.getPosition().getY() == pos.getY()
						&& entity.getPosition().getZ() == pos.getZ()) {
					ItemStack newitem = entityItem.getEntityItem();
					itemList.itemlist.add(newitem);
				}
			}
		}
		return itemList;
	}

	public static List<EntityItem> getEntityItemListFromPos(World world, BlockPos pos) {
		List<Entity> entitylist = world.loadedEntityList;
		List<EntityItem> itemList = new ArrayList<EntityItem>();
		for (Entity entity : entitylist) {
			if (entity.getClass() == EntityItem.class) {
				EntityItem entityItem = (EntityItem) entity;

				if (entity.getPosition().getX() == pos.getX() && entity.getPosition().getY() == pos.getY()
						&& entity.getPosition().getZ() == pos.getZ()) {
					itemList.add(entityItem);
				}
			}
		}
		return itemList;
	}
}
