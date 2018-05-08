package Client.Particle;

import java.util.function.Consumer;

import ExperienceApple.EAMain;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ParticleEABase extends Particle {
	public Consumer<ParticleEABase> consumer;

	private final ResourceLocation lightRL = new ResourceLocation(EAMain.MOD_ID + ":" + "particles/light");

	public ParticleEABase(World world, double posX, double posY, double posZ) {
		super(world, posX, posY, posZ);
		this.consumer = new Consumer<ParticleEABase>() {
			public void accept(ParticleEABase particle) {
				particle.prevPosX = particle.posX;
				particle.prevPosY = particle.posY;
				particle.prevPosZ = particle.posZ;
				particle.move(particle.motionX, particle.motionY, particle.motionZ);
				// if (particle.canCollide) {
				// particle.setExpired();
				// }
				if (particle.particleMaxAge-- <= 0) {
					particle.setExpired();
				}
			}
		};
		TextureAtlasSprite sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(lightRL.toString());
		setParticleTexture(sprite);
	}

	public void setmotion(double x, double y, double z) {
		this.motionX = x;
		this.motionY = y;
		this.motionZ = z;

	}

	@Override
	public int getFXLayer() {
		return 1;
	}

	@Override
	public int getBrightnessForRender(float partialTick) {
		final int FULL_BRIGHTNESS_VALUE = 0xf000f0;
		return FULL_BRIGHTNESS_VALUE;
	}

	public ParticleEABase(World world, double posX, double posY, double posZ, Consumer<ParticleEABase> consumer) {
		super(world, posX, posY, posZ);
		this.consumer = consumer;
		TextureAtlasSprite sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(lightRL.toString());
		setParticleTexture(sprite);
	}

	public void setScale(float scale) {
		this.particleScale = scale;
	}

	@Override
	public void onUpdate() {
		this.consumer.accept(this);
	}

	@Override
	public void renderParticle(BufferBuilder vertexBuffer, Entity entity, float partialTick, float edgeLRdirectionX,
			float edgeUDdirectionY, float edgeLRdirectionZ, float edgeUDdirectionX, float edgeUDdirectionZ) {
		double minU = this.particleTexture.getMinU();
		double maxU = this.particleTexture.getMaxU();
		double minV = this.particleTexture.getMinV();
		double maxV = this.particleTexture.getMaxV();

		double scale = 0.1F * this.particleScale; // vanilla scaling factor
		final double scaleLR = scale;
		final double scaleUD = scale;
		double x = this.prevPosX + (this.posX - this.prevPosX) * partialTick - interpPosX;
		double y = this.prevPosY + (this.posY - this.prevPosY) * partialTick - interpPosY;
		double z = this.prevPosZ + (this.posZ - this.prevPosZ) * partialTick - interpPosZ;

		int combinedBrightness = this.getBrightnessForRender(partialTick);
		int skyLightTimes16 = combinedBrightness >> 16 & 65535;
		int blockLightTimes16 = combinedBrightness & 65535;

		vertexBuffer
				.pos(x - edgeLRdirectionX * scaleLR - edgeUDdirectionX * scaleUD, y - edgeUDdirectionY * scaleUD,
						z - edgeLRdirectionZ * scaleLR - edgeUDdirectionZ * scaleUD)
				.tex(maxU, maxV).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha)
				.lightmap(skyLightTimes16, blockLightTimes16).endVertex();
		vertexBuffer
				.pos(x - edgeLRdirectionX * scaleLR + edgeUDdirectionX * scaleUD, y + edgeUDdirectionY * scaleUD,
						z - edgeLRdirectionZ * scaleLR + edgeUDdirectionZ * scaleUD)
				.tex(maxU, minV).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha)
				.lightmap(skyLightTimes16, blockLightTimes16).endVertex();
		vertexBuffer
				.pos(x + edgeLRdirectionX * scaleLR + edgeUDdirectionX * scaleUD, y + edgeUDdirectionY * scaleUD,
						z + edgeLRdirectionZ * scaleLR + edgeUDdirectionZ * scaleUD)
				.tex(minU, minV).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha)
				.lightmap(skyLightTimes16, blockLightTimes16).endVertex();
		vertexBuffer
				.pos(x + edgeLRdirectionX * scaleLR - edgeUDdirectionX * scaleUD, y - edgeUDdirectionY * scaleUD,
						z + edgeLRdirectionZ * scaleLR - edgeUDdirectionZ * scaleUD)
				.tex(minU, maxV).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha)
				.lightmap(skyLightTimes16, blockLightTimes16).endVertex();

	}

}
