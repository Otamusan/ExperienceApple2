package Common.FlyingSpellPaper;

import java.util.Map;

import ExperienceApple.Register.PotionRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class HasItemUpdate {
	public static boolean currentEffect = false;
	public static boolean prevEffect = false;
	public static boolean playerfly = false;

	@SubscribeEvent
	public void EventSubscriber(TickEvent.PlayerTickEvent event) {
		EntityPlayer player = event.player;
		Entity entity = player;
		PlayerCapabilities capabilities = ((EntityPlayer) entity).capabilities;
		currentEffect = hasFlyEffect(player);

		if (currentEffect && !prevEffect) {
			capabilities.allowFlying = true;
			player.sendPlayerAbilities();
		}

		if (!currentEffect && prevEffect) {
			capabilities.allowFlying = false;
			capabilities.isFlying = false;
			player.sendPlayerAbilities();
		}

		prevEffect = currentEffect;
	}

	public static boolean hasFlyEffect(EntityPlayer player) {
		Map<Potion, PotionEffect> list = player.getActivePotionMap();
		if (list.containsKey(PotionRegister.potionFly))
			return true;
		return false;
	}

}