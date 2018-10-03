package Rituals.Rituals.RitualDehydration;

import java.util.ArrayList;
import java.util.List;

import Rituals.StonePosData;
import Rituals.Rituals.Ritual;
import Util.EntityItemUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RitualDehydration extends Ritual {

	public final int RANGE = 20;
	public List<RitualDehydrationRecipe> list;

	public RitualDehydration(StonePosData posData, String name) {
		super(posData, name);
		list = new ArrayList<>();
	}

	@Override
	public void activate(EntityPlayer player, World world, BlockPos pos) {
		List<Entity> list = world.getLoadedEntityList();
		for (Entity entity : list) {
			if (!(entity instanceof EntityItem))
				break;
			ItemStack itemStack = ((EntityItem) entity).getItem();
			for (RitualDehydrationRecipe recipe : this.list) {
				System.out.println(recipe.getSource());
				if (!itemStack.isItemEqual(recipe.getSource()))
					break;
				EntityItemUtil.spawnItem(world, recipe.getItem().copy(), entity.posX, entity.posY, entity.posZ);
			}

		}
	}

	public void setRecipe(ItemStack source, ItemStack item) {
		this.list.add(new RitualDehydrationRecipe(source, item));
	}

	public void setRecipeAll(List<ItemStack> source, ItemStack item) {
		for (ItemStack sourceitem : source) {
			this.list.add(new RitualDehydrationRecipe(sourceitem, item));
		}
	}

	@Override
	public boolean canActivate(EntityPlayer player, World world, BlockPos pos) {
		return true;
	}
}
