package Potion;

import java.util.Collection;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;

public class PotionRetention extends Potion {
	public ResourceLocation resourceLocation;

	public PotionRetention(ResourceLocation resourceLocation) {
		super(false, 0xEEEEFF);
		setPotionName("effect.retention");
		this.resourceLocation = resourceLocation;
	}

	@Override
	public void performEffect(EntityLivingBase entityLivingBaseIn, int p_76394_2_) {

		Collection<PotionEffect> map = (Collection<PotionEffect>) entityLivingBaseIn.getActivePotionEffects();
		int du = 0;
		for (PotionEffect potionEffect : map) {
			if (potionEffect.getPotion().getName() == this.getName()) {
				du = potionEffect.getDuration();
			}
		}
		for (PotionEffect potionEffect : map) {
			PotionEffect potionEffectnew = new PotionEffect(potionEffect.getPotion(), du, potionEffect.getAmplifier(),
					potionEffect.getIsAmbient(), potionEffect.doesShowParticles());
			if (potionEffect.getPotion().getName() != this.getName()) {
				potionEffect.combine(potionEffectnew);
			}
		}
	}

	@Override
	public boolean isReady(int duration, int amplifier) {
		return true;
	}

	@Override
	public int getStatusIconIndex() {
		Minecraft.getMinecraft().getTextureManager().bindTexture(resourceLocation);
		return 0;
	}

	@Override
	public boolean hasStatusIcon() {
		return true;
	}
}