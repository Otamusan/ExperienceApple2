package Client.Particle;

import java.util.Random;
import java.util.function.Consumer;

import Util.Vec.Vec;

public class PS {
	public static Consumer<EAFandInt> randomSquare(double xleng, double yleng, double zleng) {
		return new Consumer<EAFandInt>() {
			@Override
			public void accept(EAFandInt t) {
				IEAParticleFunc func = t.getParticle();
				// int i = t.getAmount();
				Random rnd = new Random();
				func.addPosDif(new Vec(rnd.nextDouble() * xleng, rnd.nextDouble() * yleng, rnd.nextDouble() * zleng));
			}
		};
	}

	public static Consumer<EAFandInt> randomBall(double range) {
		return new Consumer<EAFandInt>() {
			@Override
			public void accept(EAFandInt t) {
				Random rnd = new Random();
				t.getParticle()
						.addPosDif(Vec.getVec(range, rnd.nextDouble() * Math.PI * 2, rnd.nextDouble() * Math.PI));
			}
		};
	}

	public static Consumer<EAFandInt> convergence(double speed, Vec vec) {
		return new Consumer<EAFandInt>() {
			@Override
			public void accept(EAFandInt t) {
				IEAParticleFunc func = t.getParticle();
				Vec sub = func.getPosDif().copy();
				sub.subtract(vec);
				sub.Multiply(-speed);
				t.getParticle().addConsumer(PF.setMotion(sub.x, sub.y, sub.z));
			}
		};
	}

	public static Consumer<EAFandInt> spread(double speed, Vec vec) {
		return new Consumer<EAFandInt>() {
			@Override
			public void accept(EAFandInt t) {
				IEAParticleFunc func = t.getParticle();
				Vec sub = func.getPosDif().copy();
				sub.subtract(vec);
				sub.Multiply(speed);
				t.getParticle().addConsumer(PF.setMotion(sub.x, sub.y, sub.z));
			}
		};
	}
}
