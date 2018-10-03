package Item.Tools;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import Client.Particle.EAParticleFunc;
import Client.Particle.EAParticleFuncs;
import Client.Particle.PF;
import Client.Particle.PS;
import Item.ExperienceRepair;
import Item.IExperienceRepair;
import Util.Vec.Vec;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemExperienceIronSword extends ItemSword implements IExperienceRepair {

	private ExperienceRepair experienceRepair;

	public ItemExperienceIronSword(ToolMaterial mate, int cooltime, int cost) {
		super(mate);
		this.experienceRepair = new ExperienceRepair(cooltime, cost);
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, IBlockState state, BlockPos pos,
			EntityLivingBase entityLiving) {
		EAParticleFuncs funcs = new EAParticleFuncs(
				new EAParticleFunc(20, 0, 0, 0, PF.setBlink(1), PF.setColor(0, 1, 0, 0.5f)), 3,
				PS.randomSquare(1, 1, 1));
		funcs.spawn(world, new Vec(pos.getX(), pos.getY(), pos.getZ()));
		if (!world.isRemote && state.getBlockHardness(world, pos) != 0.0D) {
			stack.damageItem(1, entityLiving);
		}
		return false;
	}

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
				new EAParticleFunc(20, 0, 0, 0, PF.setBlink(1), PF.setColor(0, 1, 0, 0.5f)), 3,
				PS.randomSquare(1, 1, 1));
		funcs.spawn(entity.world, new Vec(entity.posX, entity.posY, entity.posZ));
		return false;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add("§3" + I18n.format("item.experienceIron.cooltime") + " : " + this.experienceRepair.getCooltime());
		tooltip.add("§3" + I18n.format("item.experienceIron.cost") + " : " + this.experienceRepair.getCost());

	}

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entity, int itemSlot, boolean isSelected) {
		this.experienceRepair.onUpdate(stack, worldIn, entity, itemSlot, isSelected);
	}

	@Override
	public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
		return false;
	}

	@Override
	public int getItemEnchantability() {
		return 0;
	}

}
