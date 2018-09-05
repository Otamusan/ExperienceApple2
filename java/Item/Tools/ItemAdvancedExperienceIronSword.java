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

import Util.ExperienceUtil;
import Util.ParticleUtil;
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
import net.minecraft.util.EnumParticleTypes;
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

				ParticleUtil.playerRemaining(EnumParticleTypes.FIREWORKS_SPARK, entity, 20);
			}
		}
		worldIn.playSound(player.posX, player.posY, player.posZ, SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE,
				SoundCategory.PLAYERS, 1, 1, true);
		ParticleUtil.ball(EnumParticleTypes.FIREWORKS_SPARK, worldIn, player.posX, player.posY, player.posZ, 20,
				(400 - timeLeft) * 20);
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
		ParticleUtil.blockRemaining(EnumParticleTypes.FIREWORKS_SPARK, world, pos, 10);

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
		ParticleUtil.ball(EnumParticleTypes.END_ROD, entity.world, entity.posX, entity.posY, entity.posZ, 2, 50);
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
