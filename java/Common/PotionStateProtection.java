package Common;

import java.util.Collection;

import ExperienceApple.Register.PotionRegister;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class PotionStateProtection {
	@SubscribeEvent
	public void EventSubscriber(TickEvent.PlayerTickEvent event) {
		boolean can = false;
		Collection<PotionEffect> list = event.player.getActivePotionEffects();
		for (PotionEffect potionEffect : list) {
			if (potionEffect.getPotion() == PotionRegister.potionStateProtection) {
				can = true;
			}
		}
		if (can) {
			for (PotionEffect potionEffect : list) {
				if (potionEffect.getPotion().isBadEffect()) {
					list.remove(potionEffect);
					return;
				}
			}
		}
	}

}