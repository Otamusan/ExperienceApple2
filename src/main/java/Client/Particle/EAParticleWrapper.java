package Client.Particle;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.lwjgl.opengl.GL11;

import ExperienceApple.EAMain;
import Util.Vec.Vec;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EAParticleWrapper extends Particle {
	public List<Consumer<EAParticleWrapper>> consumers = new ArrayList<>();

	private final ResourceLocation lightRL = new ResourceLocation(EAMain.MOD_ID + ":" + "particles/light");
	private EAParticle particle;

	public EAParticleWrapper(World world, EAParticle particle) {
		super(world, 0, 0, 0);
		TextureAtlasSprite sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(lightRL.toString());
		this.particleTexture = sprite;
		this.particle = particle;
		this.canCollide = true;
	}

	public World getWorld() {
		return this.world;
	}

	public Vec getPos() {
		return new Vec(this.posX, this.posY, this.posZ);
	}

	@Override
	public int getFXLayer() {
		return 2;
	}

	@Override
	public int getBrightnessForRender(float partialTick) {
		final int FULL_BRIGHTNESS_VALUE = 0xf000f0;
		return FULL_BRIGHTNESS_VALUE;
	}

	@Override
	public void onUpdate() {
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;

		this.particle.onUpdate();
		this.motionX = this.particle.getMotion().x;
		this.motionY = this.particle.getMotion().y;
		this.motionZ = this.particle.getMotion().z;
		this.particleScale = this.particle.getScale();
		this.particleRed = this.particle.getRed();
		this.particleBlue = this.particle.getBlue();
		this.particleGreen = this.particle.getGreen();
		this.particleAlpha = this.particle.getAlpha();
		this.particle.onGround = this.onGround;
		this.move(this.motionX, this.motionY, this.motionZ);
		// this.setBoundingBox(new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0D, 0.0D,
		// 0.0D));
		// resetPositionToBB();
		// this.getBoundingBox().grow(4);

		this.setSize(particleScale, particleScale);

		if (this.particle.isDead()) {
			this.setExpired();
		}

	}

	@Override
	public void renderParticle(BufferBuilder bufferBuilder, Entity entity, float partialTick, float edgeLRdirectionX,
			float edgeUDdirectionY, float edgeLRdirectionZ, float edgeUDdirectionX, float edgeUDdirectionZ) {
		Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		GlStateManager.depthMask(false);
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
		GlStateManager.alphaFunc(GL11.GL_GREATER, 0.003921569F);
		GlStateManager.disableLighting();
		double minU = this.particleTexture.getMinU();
		double maxU = this.particleTexture.getMaxU();
		double minV = this.particleTexture.getMinV();
		double maxV = this.particleTexture.getMaxV();

		double scale = 0.1F * this.particleScale;
		final double scaleLR = scale;
		final double scaleUD = scale;
		double x = this.prevPosX + (this.posX - this.prevPosX) * partialTick - interpPosX;
		double y = this.prevPosY + (this.posY - this.prevPosY) * partialTick - interpPosY;
		double z = this.prevPosZ + (this.posZ - this.prevPosZ) * partialTick - interpPosZ;

		int combinedBrightness = this.getBrightnessForRender(partialTick);
		int skyLightTimes16 = combinedBrightness >> 16 & 65535;
		int blockLightTimes16 = combinedBrightness & 65535;
		bufferBuilder
				.pos(x - edgeLRdirectionX * scaleLR - edgeUDdirectionX * scaleUD, y - edgeUDdirectionY * scaleUD,
						z - edgeLRdirectionZ * scaleLR - edgeUDdirectionZ * scaleUD)
				.tex(maxU, maxV).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha)
				.lightmap(skyLightTimes16, blockLightTimes16).endVertex();
		bufferBuilder
				.pos(x - edgeLRdirectionX * scaleLR + edgeUDdirectionX * scaleUD, y + edgeUDdirectionY * scaleUD,
						z - edgeLRdirectionZ * scaleLR + edgeUDdirectionZ * scaleUD)
				.tex(maxU, minV).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha)
				.lightmap(skyLightTimes16, blockLightTimes16).endVertex();
		bufferBuilder
				.pos(x + edgeLRdirectionX * scaleLR + edgeUDdirectionX * scaleUD, y + edgeUDdirectionY * scaleUD,
						z + edgeLRdirectionZ * scaleLR + edgeUDdirectionZ * scaleUD)
				.tex(minU, minV).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha)
				.lightmap(skyLightTimes16, blockLightTimes16).endVertex();
		bufferBuilder
				.pos(x + edgeLRdirectionX * scaleLR - edgeUDdirectionX * scaleUD, y - edgeUDdirectionY * scaleUD,
						z + edgeLRdirectionZ * scaleLR - edgeUDdirectionZ * scaleUD)
				.tex(minU, maxV).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha)
				.lightmap(skyLightTimes16, blockLightTimes16).endVertex();

		Tessellator.getInstance().draw();
		bufferBuilder.begin(7, DefaultVertexFormats.PARTICLE_POSITION_TEX_COLOR_LMAP);

		GlStateManager.enableBlend();
		GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
		GlStateManager.alphaFunc(516, 0.003921569F);
		GlStateManager.depthMask(true);
	}
}
