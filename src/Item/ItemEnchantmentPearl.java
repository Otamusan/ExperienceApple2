package Item;

import java.util.Random;

import CauldronCraft.CauldronCore;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemEnchantmentPearl extends Item {
	@Override
	public boolean hasEffect(ItemStack itemstack) {
		return true;
	}

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		//System.out.println(Enchantment.REGISTRY.getRandomObject(new Random()));
		if (stack.getEnchantmentTagList() == null) {
			Enchantment enchantment = Enchantment.REGISTRY.getRandomObject(new Random());
			int levelmax = enchantment.getMaxLevel();
			int level = new Random().nextInt(levelmax) + 1;
			stack.addEnchantment(enchantment, level);
		}
	}

	@Override
	public boolean onEntityItemUpdate(net.minecraft.entity.item.EntityItem entityItem) {
		World world = entityItem.worldObj;
		if (world.getBlockState(entityItem.getPosition()).getBlock() != Blocks.CAULDRON) {
			return false;
		}

		if (world.getBlockState(entityItem.getPosition()).getBlock()
				.getMetaFromState(world.getBlockState(entityItem.getPosition())) != 3) {
			return false;
		}

		//world.spawnEntity(
		//		new EntityItem(world, entityItem.getPosition().getX() + 0.5, entityItem.getPosition().getY() + 0.5,
		//				entityItem.getPosition().getZ() + 0.5, new ItemStack(BlockRegister.pureExperienceBlock)));
		//world.setBlockState(entityItem.getPosition(), Blocks.CAULDRON.getDefaultState());
		//entityItem.setDead();

		CauldronCore.ritualActive(world, entityItem.getPosition().getX(), entityItem.getPosition().getY(),
				entityItem.getPosition().getZ(),
				entityItem.getEntityItem());

		return false;
	}
}
