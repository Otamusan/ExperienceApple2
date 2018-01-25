package Rituals;

import java.util.ArrayList;
import java.util.Map;

import Blocks.BlockRitual;
import Blocks.BlockRitualGlass;
import Blocks.BlockRitualLauncher;
import Blocks.BlockRitualStone;
import ExperienceApple.Register.RecipeRegister;
import Rituals.Rituals.Ritual;
import Util.ExperienceUtil;
import Util.ParticleUtil;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RitualCore {
	public static void ActRitual(EntityPlayer player, World world, BlockPos pos) {
		ArrayList<Ritual> rituallist = RitualRegistry.list;
		out: for (Ritual ritualBase : rituallist) {
			boolean flag = true;
			Map<BlockPos, RitualStones> posDatas = ritualBase.getStoneBlockData();

			for (Map.Entry<BlockPos, RitualStones> dataPosEntry : posDatas.entrySet()) {
				BlockPos dataPos = dataPosEntry.getKey();
				BlockPos worldPos = new BlockPos(dataPos.getX() + pos.getX(), dataPos.getY() + pos.getY(),
						dataPos.getZ() + pos.getZ());
				Block block = world.getBlockState(worldPos).getBlock();
				RitualStones tier = RitualStones.getRitualStones(block);
				if (tier == posDatas.get(dataPos)) {

				} else {
					flag = false;
					continue out;
				}
			}
			if (flag) {
				if (ritualBase.canActivate(player, world, pos) && BlockRemove(player, world, posDatas, pos)) {
					ritualBase.activate(player, world, pos);
				}
			}
		}
	}

	public static boolean BlockRemove(EntityPlayer player, World world, Map<BlockPos, RitualStones> map, BlockPos pos) {
		for (Map.Entry<BlockPos, RitualStones> dataPosEntry : map.entrySet()) {
			BlockPos dataPos = dataPosEntry.getKey();
			BlockPos worldPos = new BlockPos(dataPos.getX() + pos.getX(), dataPos.getY() + pos.getY(),
					dataPos.getZ() + pos.getZ());
			Block block = world.getBlockState(worldPos).getBlock();
			if (block instanceof BlockRitualGlass) {
				world.setBlockToAir(worldPos);
				ParticleUtil.blockRemaining(EnumParticleTypes.FIREWORKS_SPARK, world, worldPos, 10);
			}
			if (block instanceof BlockRitualLauncher || block instanceof BlockRitualStone) {
				BlockRitual blockRitual = (BlockRitual) block;
				if (!ExperienceUtil.experiencePull(player,
						RecipeRegister.ritualGlassCost * blockRitual.getTier().getMagnification(), world)) {
					return false;
				}
				ParticleUtil.blockRemaining(EnumParticleTypes.FIREWORKS_SPARK, world, worldPos, 10);

			}
		}
		return true;
	}

}
