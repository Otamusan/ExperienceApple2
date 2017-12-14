package Item.SpellPapers;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemAcceleratedSpellPaper extends Item {

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int Slot, boolean isSelected) {
		if (new Random().nextFloat() > 0.9) {
			for (int i = 0; i < 10; i++) {
				entity.onUpdate();
				entity.onEntityUpdate();
			}

		}
	}
}
