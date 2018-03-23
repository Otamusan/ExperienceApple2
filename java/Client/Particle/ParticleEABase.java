package Client.Particle;

import java.util.function.Consumer;

import net.minecraft.client.particle.Particle;
import net.minecraft.world.World;

public class ParticleEABase extends Particle {
	public Consumer<ParticleEABase> consumer;

	public ParticleEABase(World world, double posX, double posY, double posZ) {
		super(world, posX, posY, posZ);
		this.consumer = new Consumer<ParticleEABase>() {
			public void accept(ParticleEABase particle) {
				particle.prevPosX = particle.posX;
				particle.prevPosY = particle.posY;
				particle.prevPosZ = particle.posZ;

				if (particle.particleAge++ >= particle.particleMaxAge) {
					particle.setExpired();
				}

				particle.motionY -= 0.04D * (double) particle.particleGravity;
				particle.moveEntity(particle.motionX, particle.motionY, particle.motionZ);
				particle.motionX *= 0.9800000190734863D;
				particle.motionY *= 0.9800000190734863D;
				particle.motionZ *= 0.9800000190734863D;

				if (particle.isCollided) {
					particle.motionX *= 0.699999988079071D;
					particle.motionZ *= 0.699999988079071D;
				}
			}
		};
	}

	public ParticleEABase(World world, double posX, double posY, double posZ, Consumer<ParticleEABase> consumer) {
		super(world, posX, posY, posZ);
		this.consumer = consumer;
	}

	public void setScale(float scale) {
		this.particleScale = scale;
	}

	@Override
	public void onUpdate() {
		this.consumer.accept(this);
	}

}
