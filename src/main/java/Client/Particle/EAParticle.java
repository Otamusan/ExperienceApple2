package Client.Particle;

import Util.Vec.Vec;
import net.minecraft.world.World;

public class EAParticle {
	private Vec motion = new Vec(0, 0, 0);
	private float scale = 0;
	private EAParticleFunc func;
	private int age = 0;
	private float red = 0;
	private float blue = 0;
	private float green = 0;
	private float alpha = 0;
	private World world;
	public boolean onGround;
	private int groundtime = 1;

	public EAParticle(EAParticleFunc func, World world) {
		this.func = func;
		this.world = world;
	}

	public void addMotion(Vec vec) {
		this.motion.add(vec);
	}

	public Vec getMotion() {
		return this.motion;
	}

	public void addScale(float scale) {
		this.scale += scale;
	}

	public float getScale() {
		return this.scale;
	}

	public int getMaxAge() {
		return this.func.getMaxAge();
	}

	public int getAge() {
		return this.age;
	}

	public void addRed(float red) {
		this.red += red;
	}

	public float getRed() {
		return this.red;
	}

	public void addRGBA(float r, float g, float b, float a) {
		addRed(r);
		addBlue(b);
		addGreen(g);
		addAlpha(a);
	}

	public void addGreen(float green) {
		this.green += green;
	}

	public float getGreen() {
		return this.green;
	}

	public void addBlue(float blue) {
		this.blue += blue;
	}

	public float getBlue() {
		return this.blue;
	}

	public void addAlpha(float alpha) {
		this.alpha += alpha;
	}

	public float getAlpha() {
		return this.alpha;
	}

	public boolean isDead() {
		return this.getMaxAge() <= this.getAge();
	}

	public double getAgeRate() {
		return (double) this.getAge() / (double) this.getMaxAge();
	}

	public void onUpdate() {
		this.motion = Vec.getZero();
		this.alpha = 0;
		this.red = 0;
		this.green = 0;
		this.blue = 0;
		this.scale = 0;

		if (this.onGround) {
			this.groundtime++;
		}

		this.func.actFunc(this);
		this.motion.x = this.motion.x / groundtime;
		this.motion.z = this.motion.z / groundtime;

		this.age++;
	}
}
