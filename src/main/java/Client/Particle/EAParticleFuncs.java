package Client.Particle;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import Util.Vec.Vec;
import net.minecraft.world.World;

public class EAParticleFuncs implements IEAParticleFunc {
	private int amount;
	private ArrayList<IEAParticleFunc> list;
	private IEAParticleFunc func;
	private List<Consumer<EAFandInt>> consumers;

	private int maxAge;
	private Vec posDif;

	@SafeVarargs
	public EAParticleFuncs(IEAParticleFunc func, int amount, Consumer<EAFandInt>... consumers) {
		this.amount = amount;
		this.func = func;

		this.consumers = new ArrayList<>();

		for (int i = 0; i < consumers.length; i++) {
			this.consumers.add(consumers[i]);
		}

		this.list = new ArrayList<IEAParticleFunc>();

		for (int i = 0; i < amount; i++) {
			list.add(func.copy());
		}

		for (Consumer<EAFandInt> consumer : this.consumers) {
			for (int i = 0; i < amount; i++) {
				consumer.accept(new EAFandInt(list.get(i), i));
			}
		}
	}

	public EAParticleFuncs(IEAParticleFunc func, int amount, List<Consumer<EAFandInt>> consumers) {
		this.amount = amount;
		this.func = func;

		this.consumers = consumers;

		this.list = new ArrayList<IEAParticleFunc>();

		for (int i = 0; i < amount; i++) {
			list.add(func.copy());
		}

		for (Consumer<EAFandInt> consumer : consumers) {
			for (int i = 0; i < amount; i++) {
				consumer.accept(new EAFandInt(list.get(0), i));
			}
		}
	}

	public EAParticleFuncs(IEAParticleFunc... particleFuncs) {

		this.amount = particleFuncs.length;
		this.func = new EAParticleFunc(0, 0, 0, 0);

		this.consumers = new ArrayList<>();
		this.list = new ArrayList<IEAParticleFunc>();

		for (IEAParticleFunc ieaParticleFunc : particleFuncs) {
			this.list.add(ieaParticleFunc);
		}
	}

	@Override
	public void addPosDif(Vec vec) {
		for (IEAParticleFunc eaParticleFunc : list) {
			eaParticleFunc.addPosDif(vec);
		}
		this.posDif.add(vec);
	}

	@Override
	public void addmaxAge(int age) {
		for (IEAParticleFunc eaParticleFunc : list) {
			eaParticleFunc.addmaxAge(age);
		}
		this.maxAge += age;
	}

	@Override
	public void addConsumer(Consumer<EAParticle> consumer) {
		for (IEAParticleFunc eaParticleFunc : list) {
			eaParticleFunc.addConsumer(consumer);
		}
	}

	@Override
	public void actFunc(EAParticle eap) {
		for (IEAParticleFunc eaParticleFunc : list) {
			eaParticleFunc.actFunc(eap);
		}
	}

	@Override
	public int getMaxAge() {
		return this.maxAge;
	}

	@Override
	public void spawn(World world, Vec pos) {
		for (IEAParticleFunc eaParticleFunc : list) {
			eaParticleFunc.spawn(world, pos);
		}
	}

	@Override
	public IEAParticleFunc copy() {
		return new EAParticleFuncs(func, amount, consumers);
	}

	@Override
	public Vec getPosDif() {
		return this.posDif;
	}
}
