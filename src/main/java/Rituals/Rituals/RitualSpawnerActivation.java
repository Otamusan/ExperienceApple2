package Rituals.Rituals;

import ExperienceApple.Register.BlockRegister;
import Rituals.StonePosData;
import TileEntity.TileAwakenedSpawner;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RitualSpawnerActivation extends Ritual {

	public RitualSpawnerActivation(StonePosData posData, String name) {
		super(posData, name);
	}

	@Override
	public void activate(EntityPlayer player, World world, BlockPos pos) {
		BlockPos spawnerpos = new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ());
		TileEntityMobSpawner spawner = (TileEntityMobSpawner) world.getTileEntity(spawnerpos);
		Entity entity = spawner.getSpawnerBaseLogic().getCachedEntity();
		String entityname = EntityList.getEntityString(entity);
		// System.out.println(entityname);
		world.setBlockState(spawnerpos, BlockRegister.awakenedSpawner.getDefaultState());
		TileAwakenedSpawner spawnerlate = (TileAwakenedSpawner) world.getTileEntity(spawnerpos);
		spawnerlate.getSpawnerBaseLogic().setEntityId(new ResourceLocation(entityname));
	}

	@Override
	public boolean canActivate(EntityPlayer player, World world, BlockPos pos) {
		BlockPos spawnerpos = new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ());

		if (world.getBlockState(spawnerpos).getBlock() == Blocks.MOB_SPAWNER)
			return true;
		return false;
	}
}
