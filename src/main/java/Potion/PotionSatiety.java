package Potion;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class PotionSatiety extends Potion {
	public ResourceLocation resourceLocation;

	public PotionSatiety(ResourceLocation resourceLocation) {
		super(false, 0xEEEEFF);
		setPotionName("effect.satiety");
		this.resourceLocation = resourceLocation;
	}

	@Override
	public void performEffect(EntityLivingBase entityLivingBaseIn, int p_76394_2_) {
		if (entityLivingBaseIn instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entityLivingBaseIn;
			player.getFoodStats().setFoodLevel(20);
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