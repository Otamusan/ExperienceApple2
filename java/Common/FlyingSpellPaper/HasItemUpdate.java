package Common.FlyingSpellPaper;

import ExperienceApple.Register.ItemRegister;
import Util.PlayerUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class HasItemUpdate {
	public static boolean hasFlyPaper = false;
	public static boolean bhasFlyPaper = false;

	@SubscribeEvent
	public void EventSubscriber(TickEvent.PlayerTickEvent event) {
		EntityPlayer player = event.player;
		Entity entity = player;
		PlayerCapabilities capabilities = ((EntityPlayer) entity).capabilities;
		hasFlyPaper = PlayerUtil.hasPlayerItem(player, ItemRegister.flyingSpellPaper);

		if (hasFlyPaper && !bhasFlyPaper) {
			capabilities.allowFlying = true;
			player.sendPlayerAbilities();
		}

		if (!hasFlyPaper && bhasFlyPaper) {
			capabilities.allowFlying = false;
			capabilities.isFlying = false;
			player.sendPlayerAbilities();
		}
		bhasFlyPaper = hasFlyPaper;
	}

}