package Rituals.Rituals;

import java.util.ArrayList;

import Rituals.StonePosData;
import Util.ParticleUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RitualEggization extends Ritual {

	public RitualEggization(StonePosData posData, String name) {
		super(posData, name);
	}

	@Override
	public void activate(EntityPlayer player, World world, BlockPos pos) {
		ArrayList<Entity> list = new ArrayList<Entity>();
		list.addAll(world.getLoadedEntityList());
		for (Entity entity : list) {
			if (entity instanceof EntityLivingBase && entity.getDistanceSq(pos) <= 5) {
				EntityLivingBase entityLivingBase = (EntityLivingBase) entity;
				ItemStack itemStack = new ItemStack(Items.SPAWN_EGG);
				NBTTagCompound EntityTag = new NBTTagCompound();
				NBTTagCompound nbt = new NBTTagCompound();

				if (EntityList.ENTITY_EGGS.containsKey(EntityList.getEntityString(entityLivingBase))) {
					world.playSound(entity.posX, entity.posY, entity.posZ, SoundEvents.ENTITY_CHICKEN_EGG,
							SoundCategory.BLOCKS, 1, 1, true);
					ParticleUtil.verticalCircle(EnumParticleTypes.ENCHANTMENT_TABLE, world, entity.posX, entity.posY,
							entity.posZ, 1, 20);
					EntityTag.setString("id",
							EntityList.ENTITY_EGGS.get(EntityList.getEntityString(entityLivingBase)).spawnedID);
					nbt.setTag("EntityTag", EntityTag);
					itemStack.setTagCompound(nbt);
					if (!world.isRemote) {
						world.spawnEntityInWorld(new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), itemStack));
					}
					entityLivingBase.setDead();
				}
			}
		}
	}

	@Override
	public boolean canActivate(EntityPlayer player, World world, BlockPos pos) {
		ArrayList<Entity> list = new ArrayList<Entity>();
		list.addAll(world.getLoadedEntityList());
		for (Entity entity : list) {
			if (entity instanceof EntityLivingBase && entity.getDistanceSq(pos) <= 5) {
				EntityLivingBase entityLivingBase = (EntityLivingBase) entity;
				if (EntityList.ENTITY_EGGS.containsKey(EntityList.getEntityString(entityLivingBase))) {
					return true;
				}
			}
		}
		return false;
	}
}
