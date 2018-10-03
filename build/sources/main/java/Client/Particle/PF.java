package Client.Particle;

import java.util.function.Consumer;

import Util.Vec.Vec;

public class PF {
	public static Consumer<EAParticle> setColor(float r, float g, float b, float a) {
		return new Consumer<EAParticle>() {
			@Override
			public void accept(EAParticle t) {
				t.addRGBA(r, g, b, a);
			}
		};
	}

	public static Consumer<EAParticle> setMotion(double x, double y, double z) {
		return new Consumer<EAParticle>() {
			@Override
			public void accept(EAParticle t) {
				t.addMotion(new Vec(x, y, z));
			}
		};
	}

	public static Consumer<EAParticle> setScale(float s) {
		return new Consumer<EAParticle>() {
			@Override
			public void accept(EAParticle t) {
				t.addScale(s);
			}
		};
	}

	public static Consumer<EAParticle> setBlink(float maxsize) {
		return new Consumer<EAParticle>() {
			@Override
			public void accept(EAParticle t) {
				t.addScale((float) Math.sin(t.getAgeRate() * Math.PI) * maxsize);
			}
		};
	}

	public static Consumer<EAParticle> setGravity(double a) {
		return new Consumer<EAParticle>() {
			@Override
			public void accept(EAParticle t) {
				t.addMotion(new Vec(0, -t.getAge() * a, 0));
			}
		};
	}

	public static Consumer<EAParticle> setScattering(double maxscale, double maxalpha) {
		return new Consumer<EAParticle>() {
			@Override
			public void accept(EAParticle t) {
				// t.addMotion(new Vec(0, -t.getAge() * a, 0));
				t.addScale((float) (t.getAgeRate() * maxscale));
				t.addAlpha((float) ((1 / t.getAgeRate()) * maxalpha));
			}
		};
	}

	public static Consumer<EAParticle> setDeceleration(double a) {
		return new Consumer<EAParticle>() {
			@Override
			public void accept(EAParticle t) {

				Vec vec = t.getMotion();
				vec.divide(a * t.getAgeRate());
			}
		};
	}
}
