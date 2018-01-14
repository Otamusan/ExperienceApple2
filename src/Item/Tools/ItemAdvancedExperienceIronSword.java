package Item.Tools;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import Util.ParticleUtil;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemAdvancedExperienceIronSword extends ItemSword {

	public static int cooldown = 0;

	public ItemAdvancedExperienceIronSword(ToolMaterial mate) {
		super(mate);
	}

	@Override
	public void onCreated(ItemStack stack, World world, EntityPlayer player) {

	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.BOW;
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, IBlockState state, BlockPos pos,
			EntityLivingBase entityLiving) {
		ParticleUtil.blockSurface(EnumParticleTypes.FIREWORKS_SPARK, world, pos, 10);
		ParticleUtil.blockRemaining(EnumParticleTypes.FIREWORKS_SPARK, world, pos, 10);

		return false;
	}

	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
		/*
		 * Entity newentity = null; try { newentity =
		 * entity.getClass().getConstructor(World.class).newInstance(entity.
		 * worldObj); } catch (InstantiationException | IllegalAccessException |
		 * IllegalArgumentException | InvocationTargetException |
		 * NoSuchMethodException | SecurityException e) { e.printStackTrace(); }
		 * if (newentity == null) return false;
		 * entity.worldObj.loadedEntityList.add(newentity);
		 * newentity.setPosition(entity.posX, entity.posY, entity.posZ);
		 * entity.onKillCommand();
		 * 
		 * System.out.println(player.worldObj.isRemote);
		 */
		/*
		 * if (!(entity instanceof EntityLivingBase)) return false;
		 * EntityLivingBase living = (EntityLivingBase) entity;
		 * ArrayList<EntityItem> list = (ArrayList<EntityItem>)
		 * (living.capturedDrops.clone()); int i =
		 * net.minecraftforge.common.ForgeHooks.getLootingLevel(entity, player,
		 * new EntityDamageSource("player", player));
		 * 
		 * net.minecraftforge.common.ForgeHooks.onLivingDrops(living, new
		 * EntityDamageSource("player", player), list, i, true);
		 */
		if (!(entity instanceof EntityLivingBase))
			return false;
		EntityLivingBase living = (EntityLivingBase) entity;
		EntityDamageSource cause = new EntityDamageSource("player", player);
		System.out.println(living.capturedDrops);
		living.onDeath(cause);
		System.out.println(living.capturedDrops);

		ArrayList<EntityItem> list = null;
		try {
			list = deepcopy(living.capturedDrops);
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		living.capturedDrops = list;
		living.isDead = false;
		return false;
	}

	@SuppressWarnings("unchecked")
	public static <T> T deepcopy(T obj) throws IOException, ClassNotFoundException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		new ObjectOutputStream(baos).writeObject(obj);
		return (T) new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray())).readObject();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		return true;
	}
}
