package Util.Vec;

import net.minecraft.util.math.BlockPos;

public class Vec {
	public double x;
	public double y;
	public double z;

	public Vec(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public String toString() {
		return "[" + x + "," + y + "," + z + "]";
	}

	public static Vec getVec(double r, double p, double t) {
		double x = r * Math.sin(t) * Math.cos(p);
		double y = r * Math.sin(t) * Math.sin(p);
		double z = r * Math.cos(t);
		return new Vec(x, y, z);
	}

	public static Vec getZero() {
		return new Vec(0, 0, 0);
	}

	public boolean isZero() {
		return this.x == 0 && this.y == 0 && this.y == 0;
	}

	public double getLength() {
		return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2) + Math.pow(this.z, 2));
	}

	public BlockPos getBlockPos() {
		return new BlockPos(x, y, z);
	}

	public Vec getUnitVec() {
		if (isZero())
			return getZero();

		double length = this.getLength();
		double x = this.x / length;
		double y = this.y / length;
		double z = this.z / length;
		return new Vec(x, y, z);
	}

	public void setLength(double length) {
		Vec vec = this.getUnitVec();
		this.x = vec.x * length;
		this.y = vec.y * length;
		this.z = vec.z * length;
	}

	public void Multiply(double n) {
		this.x *= n;
		this.y *= n;
		this.z *= n;
	}

	public void divide(double n) {
		if (isZero())
			return;
		if (n == 0)
			return;
		this.x /= n;
		this.y /= n;
		this.z /= n;
	}

	public void add(Vec vec) {
		this.x += vec.x;
		this.y += vec.y;
		this.z += vec.z;
	}

	public void subtract(Vec vec) {
		this.x -= vec.x;
		this.y -= vec.y;
		this.z -= vec.z;
	}

	public double getTheta() {
		return Math.acos(this.z / getLength());
	}

	public double getPhi() {
		return Math.acos(Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2)));
	}

	public Vec copy() {
		return new Vec(x, y, z);
	}

	public static Vec getVec(BlockPos pos) {
		return new Vec(pos.getX(), pos.getY(), pos.getZ());
	}
}
