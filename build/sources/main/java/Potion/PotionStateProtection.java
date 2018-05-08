package Potion;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class PotionStateProtection extends Potion {
	public ResourceLocation resourceLocation;

	public PotionStateProtection(ResourceLocation resourceLocation) {
		super(false, 0xAA44AA);
		setPotionName("effect.stateProtection");
		this.resourceLocation = resourceLocation;
	}

	@Override
	public void performEffect(EntityLivingBase entityLivingBaseIn, int p_76394_2_) {

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