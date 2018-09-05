package Common;

import java.util.Random;

import ExperienceApple.Register.ItemRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class DeathEnderDragon {
	@SubscribeEvent
	public void EventSubscriber(LivingDeathEvent event) {
		Entity entity = event.getEntity();
		if (entity.getClass() == EntityDragon.class && !entity.world.isRemote) {

			if (new Random().nextInt(11) == 0) {
				entity.world.spawnEntity(new EntityItem(entity.world, entity.posX + 0.5, entity.posY + 0.5,
						entity.posZ + 0.5, new ItemStack(ItemRegister.featherSword)));
			}
			if (new Random().nextInt(11) == 0) {
				entity.world.spawnEntity(new EntityItem(entity.world, entity.posX + 0.5, entity.posY + 0.5,
						entity.posZ + 0.5, new ItemStack(ItemRegister.magicBow)));
			}
			if (new Random().nextInt(11) == 0) {
				entity.world.spawnEntity(new EntityItem(entity.world, entity.posX + 0.5, entity.posY + 0.5,
						entity.posZ + 0.5, new ItemStack(ItemRegister.unstableAxe)));
			}

			if (new Random().nextInt(11) == 0) {
				ItemStack item = new ItemStack(ItemRegister.enchantmentPearl);
				Enchantment enchantment = Enchantment.REGISTRY.getRandomObject(new Random());
				item.addEnchantment(enchantment, 10);
				event.getEntity().world.spawnEntity(
						new EntityItem(event.getEntity().world, entity.posX, entity.posY, entity.posZ, item));
			}

		}
	}

}