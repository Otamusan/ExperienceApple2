package Item.Tools;

import Client.Particle.EAParticleFunc;
import Client.Particle.EAParticleFuncs;
import Client.Particle.PF;
import Client.Particle.PS;
import Util.Vec.Vec;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemWeakExperienceIronShovel extends ItemSpade {
	public ItemWeakExperienceIronShovel() {
		super(Item.ToolMaterial.IRON);
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, IBlockState state, BlockPos pos,
			EntityLivingBase entityLiving) {
		EAParticleFuncs funcs = new EAParticleFuncs(
				new EAParticleFunc(10, 0, 0, 0, PF.setBlink(1), PF.setColor(0, 1, 0, 0.24f)), 1,
				PS.randomSquare(1, 1, 1));
		funcs.spawn(world, Vec.getVec(pos));
		if (!world.isRemote && state.getBlockHardness(world, pos) != 0.0D) {
			stack.damageItem(1, entityLiving);
		}
		return false;
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
		EAParticleFuncs funcs = new EAParticleFuncs(
				new EAParticleFunc(10, 0, 0, 0, PF.setBlink(1), PF.setColor(0, 1, 0, 0.24f)), 1,
				PS.randomSquare(1, 1, 1));
		funcs.spawn(entity.world, new Vec(entity.posX, entity.posY, entity.posZ));
		return false;
	}
}
