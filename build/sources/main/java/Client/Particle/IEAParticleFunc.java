package Client.Particle;

import java.util.function.Consumer;

import Util.Vec.Vec;
import net.minecraft.world.World;

public interface IEAParticleFunc {
	public void addPosDif(Vec vec);

	public Vec getPosDif();

	public void addmaxAge(int age);

	public void addConsumer(Consumer<EAParticle> consumer);

	public void actFunc(EAParticle eap);

	public int getMaxAge();

	public void spawn(World world, Vec pos);

	public IEAParticleFunc copy();

}
