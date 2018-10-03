package Blocks;

import javax.annotation.Nonnull;

import Client.Particle.EAParticleFunc;
import Client.Particle.EAParticleFuncs;
import Client.Particle.PF;
import Client.Particle.PS;
import ExperienceApple.Register.RecipeRegister;
import Rituals.EnumRitualStones;
import Rituals.RitualCore;
import Util.Vec.Vec;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockRitualGlass extends BlockRitual {

	public BlockRitualGlass(Material materialIn, int particleAmount, EnumRitualStones tier) {
		super(materialIn, particleAmount, tier);
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			EnumFacing side, float hitX, float hitY, float hitZ) {
		if (!player.isSneaking()) {
			RitualCore.ActRitual(player, world, pos);
		} else {
			int value = EnumRitualStones.getRitualStones(this).getMagnification() * RecipeRegister.ritualGlassCost;
			EntityXPOrb xpOrb = new EntityXPOrb(world, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ(), value);
			if (!world.isRemote) {
				world.spawnEntity(xpOrb);
			}

			// ParticleUtil.blockRemaining(EnumParticleTypes.CRIT, world,
			// pos.add(0.5, 0.5, 0.5), 10);

			EAParticleFuncs funcs = new EAParticleFuncs(
					new EAParticleFunc(40, 0, 0, 0, PF.setBlink(2), PF.setColor(0, 1, 0, 0.7f), PF.setGravity(0.002)),
					10, PS.randomBall(0.3), PS.spread(0.2, Vec.getZero()));
			funcs.spawn(world, new Vec(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5));

			world.setBlockToAir(pos);
		}
		return true;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public boolean shouldSideBeRendered(IBlockState state, @Nonnull IBlockAccess world, @Nonnull BlockPos pos,
			EnumFacing side) {
		return true;
	}

	@SideOnly(Side.CLIENT)
	@Nonnull
	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

}
