package Rituals;

import java.util.ArrayList;
import java.util.Map;

import Blocks.BlockRitual;
import Blocks.BlockRitualGlass;
import Blocks.BlockRitualLauncher;
import Blocks.BlockRitualStone;
import Client.Particle.EAParticleFunc;
import Client.Particle.EAParticleFuncs;
import Client.Particle.PF;
import Client.Particle.PS;
import ExperienceApple.Register.RecipeRegister;
import Rituals.Rituals.Ritual;
import Util.ExperienceUtil;
import Util.Vec.Vec;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RitualCore {
	public static void ActRitual(EntityPlayer player, World world, BlockPos pos) {
		ArrayList<Ritual> rituallist = RitualRegistry.list;
		out: for (Ritual ritualBase : rituallist) {
			boolean flag = true;
			Map<BlockPos, EnumRitualStones> posDatas = ritualBase.getStoneBlockData();

			for (Map.Entry<BlockPos, EnumRitualStones> dataPosEntry : posDatas.entrySet()) {
				BlockPos dataPos = dataPosEntry.getKey();
				BlockPos worldPos = new BlockPos(dataPos.getX() + pos.getX(), dataPos.getY() + pos.getY(),
						dataPos.getZ() + pos.getZ());
				Block block = world.getBlockState(worldPos).getBlock();
				EnumRitualStones tier = EnumRitualStones.getRitualStones(block);
				if (tier == posDatas.get(dataPos)) {

				} else {
					flag = false;
					continue out;
				}
			}
			if (flag) {
				if (ritualBase.canActivate(player, world, pos) && BlockRemove(player, world, posDatas, pos)) {
					ritualBase.activate(player, world, pos);
					world.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ENTITY_PLAYER_LEVELUP,
							SoundCategory.BLOCKS, 0.1f, 1, true);
				}
			}
		}
	}

	public static boolean BlockRemove(EntityPlayer player, World world, Map<BlockPos, EnumRitualStones> map,
			BlockPos pos) {
		for (Map.Entry<BlockPos, EnumRitualStones> dataPosEntry : map.entrySet()) {
			BlockPos dataPos = dataPosEntry.getKey();
			BlockPos worldPos = new BlockPos(dataPos.getX() + pos.getX(), dataPos.getY() + pos.getY(),
					dataPos.getZ() + pos.getZ());
			Block block = world.getBlockState(worldPos).getBlock();
			if (block instanceof BlockRitualGlass) {
				world.setBlockToAir(worldPos);
			}
			if (block instanceof BlockRitualLauncher || block instanceof BlockRitualStone) {
				BlockRitual blockRitual = (BlockRitual) block;
				if (!ExperienceUtil.experiencePull(player,
						RecipeRegister.ritualGlassCost * blockRitual.getTier().getMagnification(), world)) {
					return false;
				}
				// ParticleUtil.blockRemaining(EnumParticleTypes.FIREWORKS_SPARK,
				// world, worldPos, 10);

			}
			EAParticleFuncs funcs = new EAParticleFuncs(
					new EAParticleFunc(80, 0, 0, 0, PF.setBlink(1f), PF.setColor(1, 1, 1, 0.24f), PF.setGravity(0.005)),
					10, PS.randomBall(0.1), PS.spread(1, Vec.getZero()));
			funcs.spawn(world, Vec.getVec(worldPos));
		}
		return true;
	}

}
