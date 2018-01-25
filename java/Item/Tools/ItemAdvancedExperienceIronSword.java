package Item.Tools;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import ExperienceApple.ITooltip;
import Util.ParticleUtil;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemAdvancedExperienceIronSword extends ItemSword implements ITooltip {

	public static int cooldown = 0;
	public int par = 0;

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

	// public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player,
	// Entity entity) {
	/*
	 * Entity newentity = null; try { newentity =
	 * entity.getClass().getConstructor(World.class).newInstance(entity.
	 * worldObj); } catch (InstantiationException | IllegalAccessException |
	 * IllegalArgumentException | InvocationTargetException |
	 * NoSuchMethodException | SecurityException e) { e.printStackTrace(); } if
	 * (newentity == null) return false;
	 * entity.worldObj.loadedEntityList.add(newentity);
	 * newentity.setPosition(entity.posX, entity.posY, entity.posZ);
	 * entity.onKillCommand();
	 * 
	 * System.out.println(player.worldObj.isRemote);
	 */
	/*
	 * if (!(entity instanceof EntityLivingBase)) return false; EntityLivingBase
	 * living = (EntityLivingBase) entity; ArrayList<EntityItem> list =
	 * (ArrayList<EntityItem>) (living.capturedDrops.clone()); int i =
	 * net.minecraftforge.common.ForgeHooks.getLootingLevel(entity, player, new
	 * EntityDamageSource("player", player));
	 * 
	 * net.minecraftforge.common.ForgeHooks.onLivingDrops(living, new
	 * EntityDamageSource("player", player), list, i, true);
	 */
	/*
	 * if (!(entity instanceof EntityLivingBase)) return false; EntityLivingBase
	 * living = (EntityLivingBase) entity; EntityDamageSource cause = new
	 * EntityDamageSource("player", player);
	 * System.out.println(living.capturedDrops); living.onDeath(cause);
	 * System.out.println(living.capturedDrops);
	 * 
	 * ArrayList<EntityItem> list = null; try { list =
	 * deepcopy(living.capturedDrops); } catch (ClassNotFoundException |
	 * IOException e) { e.printStackTrace(); } living.capturedDrops = list;
	 * living.isDead = false; return false;
	 */
	// }
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
		Entity newentity = null;
		if (entity.hurtResistantTime != 0)
			return true;
		if (entity.isDead)
			return true;
		try {
			newentity = entity.getClass().getConstructor(World.class).newInstance(entity.worldObj);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		if (newentity == null)
			return false;
		if (!entity.worldObj.isRemote) {
			entity.worldObj.spawnEntityInWorld(newentity);
		}
		newentity.setPosition(entity.posX, entity.posY, entity.posZ);
		newentity.onKillCommand();
		newentity.setDead();
		entity.attackEntityFrom(DamageSource.causePlayerDamage(player), 2);
		// entity.hurtResistantTime = 0;
		player.worldObj.playSound(entity.posX, entity.posY, entity.posZ, SoundEvents.ENTITY_SHEEP_SHEAR,
				SoundCategory.PLAYERS, 1, 1, false);
		// System.out.println(player.worldObj.isRemote);
		ParticleUtil.ball(EnumParticleTypes.END_ROD, entity.worldObj, entity.posX, entity.posY, entity.posZ, 2, 50);
		return false;
	}

	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn,
			EnumHand hand) {
		playerIn.addPotionEffect(
				new PotionEffect(Potion.getPotionFromResourceLocation("resistance"), 10, 10, false, false));
		playerIn.addPotionEffect(
				new PotionEffect(Potion.getPotionFromResourceLocation("slowness"), 10, 10, false, false));
		guardParticle(playerIn, 0.5, par);
		par += 5;
		return new ActionResult<ItemStack>(EnumActionResult.PASS, itemStackIn);
	}

	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		playerIn.addPotionEffect(
				new PotionEffect(Potion.getPotionFromResourceLocation("resistance"), 10, 10, false, false));
		playerIn.addPotionEffect(
				new PotionEffect(Potion.getPotionFromResourceLocation("slowness"), 10, 10, false, false));
		guardParticle(playerIn, 0.5, par);
		par += 5;
		return EnumActionResult.PASS;
	}

	public void guardParticle(EntityPlayer player, double r, double count) {
		ParticleUtil.partofCircle(EnumParticleTypes.FIREWORKS_SPARK, player.worldObj, player.posX, player.posY + 2,
				player.posZ, r, count);
		ParticleUtil.partofCircle(EnumParticleTypes.FIREWORKS_SPARK, player.worldObj, player.posX, player.posY + 2,
				player.posZ, r, -count);

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

	public List<String> Tooltip = new ArrayList<String>();

	@Override
	public List<String> getTooltip() {
		return Tooltip;
	}

	@Override
	public void addTooltip(String str) {
		Tooltip.add(str);
	}
}
