package Client.Particle;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import Util.Vec.Vec;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;

public class EAParticleFunc implements IEAParticleFunc {
	protected List<Consumer<EAParticle>> consumers = new ArrayList<>();
	private Vec posDifference;
	private int maxAge;

	@SafeVarargs
	public EAParticleFunc(int maxage, double x, double y, double z, Consumer<EAParticle>... consumer) {
		posDifference = new Vec(x, y, z);
		maxAge = maxage;
		for (Consumer<EAParticle> consumer2 : consumer) {
			this.consumers.add(consumer2);
		}
	}

	public EAParticleFunc(int maxage, double x, double y, double z) {
		posDifference = new Vec(x, y, z);
		maxAge = maxage;
	}

	public void addPosDif(Vec vec) {
		this.posDifference.add(vec);
	}

	public void addmaxAge(int age) {
		this.maxAge += age;
		if (this.maxAge < 0) {
			this.maxAge = 0;
		}
	}

	public void addConsumer(Consumer<EAParticle> consumer) {
		this.consumers.add(consumer);
	}

	public void actFunc(EAParticle eap) {
		for (Consumer<EAParticle> consumer : consumers) {
			consumer.accept(eap);
		}
	}

	public int getMaxAge() {
		return maxAge;
	}

	public void addConsumers(List<Consumer<EAParticle>> consumers) {
		this.consumers.addAll(consumers);
	}

	public void spawn(World world, Vec pos) {
		EAParticle eaParticle = new EAParticle(this, world);
		EAParticleWrapper wrapper = new EAParticleWrapper(world, eaParticle);
		wrapper.setPosition(pos.x + this.posDifference.x, pos.y + this.posDifference.y, pos.z + this.posDifference.z);
		Minecraft.getMinecraft().effectRenderer.addEffect(wrapper);
	}

	public IEAParticleFunc copy() {
		EAParticleFunc particleFunc = new EAParticleFunc(this.maxAge, this.posDifference.x, this.posDifference.y,
				this.posDifference.z);
		particleFunc.addConsumers(this.consumers);
		return particleFunc;
	}

	@Override
	public Vec getPosDif() {
		return this.posDifference;
	}
}
