package Rituals.Rituals.RitualInjection;

import java.util.Map;
import java.util.Map.Entry;

import Client.Particle.EAParticleFunc;
import Client.Particle.EAParticleFuncs;
import Client.Particle.PF;
import Client.Particle.PS;
import Rituals.StonePosData;
import Rituals.Rituals.Ritual;
import Util.Vec.Vec;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import scala.util.Random;

public class RitualInjectionCore extends Ritual {
	public RitualInjectionRegister register;

	public RitualInjectionCore(StonePosData posData, String name) {
		super(posData, name);
		register = new RitualInjectionRegister();
	}

	@Override
	public void activate(EntityPlayer player, World world, BlockPos pos) {
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		String name = getClaft(world, pos.up());

		Block block = world.getBlockState(pos.up()).getBlock();
		if (block == register.getMap().get(name).getMaterialBlock()) {
			world.setBlockToAir(pos.up());

			Block setblock = this.register.getMap().get(name).getCraftedBlock();
			world.setBlockState(pos.up(), setblock.getDefaultState());

			// ParticleUtil.verticalCircle(EnumParticleTypes.CRIT_MAGIC, world,
			// x + 0.5, y + 1.5, z + 0.5, 1, 40);
			// ParticleUtil.blockSurface(EnumParticleTypes.SMOKE_LARGE, world,
			// pos.up(), 20);
			Random rnd = new Random();

			EAParticleFuncs funcs = new EAParticleFuncs(
					new EAParticleFunc(40, 0, 1, 0, PF.setScattering(5, 1), PF.setColor(0.7f, 1f, 0.7f, 0),
							PF.setMotion((0.5 - rnd.nextDouble()) / 70, 0.02f, (0.5 - rnd.nextDouble()) / 70),
							PF.setDeceleration(2)),
					10, PS.randomSquare(1, 1, 1));
			funcs.spawn(world, Vec.getVec(pos));
			world.playSound(x, y, z, SoundEvents.ENTITY_FIREWORK_LAUNCH, SoundCategory.BLOCKS, 3, 1, false);
		}
	}

	@Override
	public boolean canActivate(EntityPlayer player, World world, BlockPos pos) {
		if (getClaft(world, pos.up()) == null)
			return false;
		return true;
	}

	public String getClaft(World world, BlockPos pos) {
		Map<String, RitualInjection> ritualdatas = this.register.getMap();

		for (Entry<String, RitualInjection> ritualdataEntry : ritualdatas.entrySet()) {
			String ritualdataname = ritualdataEntry.getKey();
			if (ritualdatas.get(ritualdataname).getMaterialBlock() == world.getBlockState(pos).getBlock()) {
				return ritualdataname;
			}
		}
		return null;
	}
}
