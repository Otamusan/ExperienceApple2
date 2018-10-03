package Common;

import ExperienceApple.Register.BlockRegister;
import ExperienceApple.Register.ItemRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityZombieVillager;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class DeathVillager {
	@SubscribeEvent
	public void EventSubscriber(LivingDeathEvent event) {
		Entity entity = event.getEntity();
		if (entity.getClass() == EntityZombieVillager.class && !entity.world.isRemote) {
			if (entity.world.getBlockState(entity.getPosition()).getBlock() == BlockRegister.pureExperienceBlock) {
				entity.world.spawnEntity(new EntityItem(entity.world, entity.posX + 0.5, entity.posY + 0.5,
						entity.posZ + 0.5, new ItemStack(ItemRegister.fragmentOfTheBrain)));
			}

		}
	}
}