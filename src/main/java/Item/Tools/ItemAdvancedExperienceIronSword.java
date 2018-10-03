package Item.Tools;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import Client.Particle.EAParticleFunc;
import Client.Particle.EAParticleFuncs;
import Client.Particle.PF;
import Client.Particle.PS;
import Util.ExperienceUtil;
import Util.Vec.Vec;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemAdvancedExperienceIronSword extends ItemSword {
	public ItemAdvancedExperienceIronSword(ToolMaterial mate) {
		super(mate);
	}

	@Override
	public void onUsingTick(ItemStack stack, EntityLivingBase player, int count) {
		if (!(player instanceof EntityPlayer))
			return;
		if (count % 5 == 0) {
			ExperienceUtil.experiencePull((EntityPlayer) player, 5, player.world);
		}
	}

	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase player, int timeLeft) {
		if (!(player instanceof EntityPlayer))
			return;

		List<Entity> list = (List<Entity>) ((ArrayList<Entity>) worldIn.getLoadedEntityList()).clone();

		for (Entity entity : list) {
			if (entity instanceof EntityLivingBase && player.getDistance(entity) < 20
					&& !(entity instanceof EntityPlayer)) {
				entity.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) player), (400 - timeLeft));

				EAParticleFuncs funcs = new EAParticleFuncs(new EAParticleFunc(20, 0, 0, 0, PF.setBlink(1),
						PF.setColor(1, 1, 1, 0.7f), PF.setGravity(0.05)), 20, PS.randomBall(0.3),
						PS.spread(0.7, Vec.getZero()));
				funcs.spawn(worldIn, new Vec(entity.posX, entity.posY, entity.posZ));
			}
		}
		worldIn.playSound(player.posX, player.posY, player.posZ, SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE,
				SoundCategory.PLAYERS, 1, 1, true);

		// ParticleUtil.ball(EnumParticleTypes.FIREWORKS_SPARK, worldIn,
		// player.posX, player.posY, player.posZ, 20,
		// (400 - timeLeft) * 20);

		EAParticleFuncs funcs = new EAParticleFuncs(
				new EAParticleFunc(20, 0, 0, 0, PF.setBlink(1), PF.setColor(1, 1, 1, 0.7f)), (400 - timeLeft) * 20,
				PS.randomBall(0.3), PS.spread(2, Vec.getZero()));
		funcs.spawn(worldIn, new Vec(player.posX, player.posY, player.posZ));
	}

	@Nonnull
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nonnull EnumHand hand) {
		player.setActiveHand(hand);
		ItemStack stack = player.getHeldItem(hand);
		return ActionResult.newResult(EnumActionResult.SUCCESS, stack);
	}

	public int getMaxItemUseDuration(ItemStack stack) {
		return 400;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.BOW;
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, IBlockState state, BlockPos pos,
			EntityLivingBase entityLiving) {
		EAParticleFuncs funcs = new EAParticleFuncs(
				new EAParticleFunc(20, 0, 0, 0, PF.setBlink(1), PF.setColor(1, 1, 1, 0.7f), PF.setGravity(0.02)), 10,
				PS.randomBall(0.3), PS.spread(0.5, Vec.getZero()));
		funcs.spawn(world, new Vec(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5));
		return false;
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
		Entity newentity = null;
		if (entity.hurtResistantTime != 0)
			return true;
		if (entity.isDead)
			return true;
		try {
			newentity = entity.getClass().getConstructor(World.class).newInstance(entity.world);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		if (newentity == null)
			return false;
		if (!entity.world.isRemote) {
			entity.world.spawnEntity(newentity);
		}
		newentity.setPosition(entity.posX, entity.posY, entity.posZ);
		newentity.onKillCommand();
		newentity.setDead();
		entity.attackEntityFrom(DamageSource.causePlayerDamage(player), 2);
		player.world.playSound(entity.posX, entity.posY, entity.posZ, SoundEvents.ENTITY_SHEEP_SHEAR,
				SoundCategory.PLAYERS, 1, 1, false);
		EAParticleFuncs funcs = new EAParticleFuncs(
				new EAParticleFunc(20, 0, 0, 0, PF.setBlink(1), PF.setColor(1, 1, 1, 0.7f), PF.setGravity(0.02)), 10,
				PS.randomBall(0.3), PS.spread(0.5, Vec.getZero()));
		funcs.spawn(entity.world, new Vec(entity.posX, entity.posY, entity.posZ));
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
