package Client.Particle;

import ExperienceApple.EAMain;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class TextureStitcherBreathFX {
	@SubscribeEvent
	public void stitcherEventPre(TextureStitchEvent.Pre event) {
		ResourceLocation lightRL = new ResourceLocation(EAMain.MOD_ID + ":" + "particles/light");
		event.getMap().registerSprite(lightRL);
	}
}
