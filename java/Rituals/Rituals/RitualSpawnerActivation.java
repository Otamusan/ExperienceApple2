package Rituals.Rituals;

import java.util.Random;

import Rituals.StonePosData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RitualSpawnerActivation extends Ritual {

	public RitualSpawnerActivation(StonePosData posData, String name) {
		super(posData, name);
	}

	@Override
	public void activate(EntityPlayer player, World world, BlockPos pos) {
		BlockPos spawnerpos = new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ());
		if (world.getBlockState(spawnerpos).getBlock() == Blocks.MOB_SPAWNER) {
			TileEntityMobSpawner spawner = (TileEntityMobSpawner) world.getTileEntity(spawnerpos);
			String entityname = EntityList.getEntityString(spawner.getSpawnerBaseLogic().getCachedEntity());
			System.out.println(entityname);
			Entity entity = EntityList.createEntityByName(entityname, world);
			System.out.println(entity);
			entity.setPosition(pos.getX() + new Random().nextFloat(), pos.getY() + 2,
					pos.getZ() + new Random().nextFloat());
			if (!world.isRemote) {
				world.spawnEntityInWorld(entity);
			}
		}
	}

	@Override
	public boolean canActivate(EntityPlayer player, World world, BlockPos pos) {
		return true;
	}
}
