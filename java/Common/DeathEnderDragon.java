package Common;

import java.util.Random;

import ExperienceApple.Register.ItemRegister;
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
		if (entity.getClass() == EntityDragon.class && !entity.worldObj.isRemote) {

			if (new Random().nextInt(11) == 0) {
				entity.worldObj.spawnEntityInWorld(new EntityItem(entity.worldObj, entity.posX + 0.5, entity.posY + 0.5,
						entity.posZ + 0.5, new ItemStack(ItemRegister.featherSword)));
			}
			if (new Random().nextInt(11) == 0) {
				entity.worldObj.spawnEntityInWorld(new EntityItem(entity.worldObj, entity.posX + 0.5, entity.posY + 0.5,
						entity.posZ + 0.5, new ItemStack(ItemRegister.magicBow)));
			}
			if (new Random().nextInt(11) == 0) {
				entity.worldObj.spawnEntityInWorld(new EntityItem(entity.worldObj, entity.posX + 0.5, entity.posY + 0.5,
						entity.posZ + 0.5, new ItemStack(ItemRegister.unstableAxe)));
			}
		}
	}

}