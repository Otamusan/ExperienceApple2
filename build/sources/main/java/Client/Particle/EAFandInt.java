package Client.Particle;

public class EAFandInt {
	private IEAParticleFunc particle;
	private int amount;

	public EAFandInt(IEAParticleFunc ieaParticleFunc, int amount) {
		this.particle = ieaParticleFunc;
		this.amount = amount;
	}

	public int getAmount() {
		return amount;
	}

	public IEAParticleFunc getParticle() {
		return particle;
	}
}
