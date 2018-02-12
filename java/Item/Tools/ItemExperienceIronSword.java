package Item.Tools;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import ExperienceApple.ITooltip;
import Item.ExperienceRepair;
import Item.IExperienceRepair;
import Util.ParticleUtil;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemExperienceIronSword extends ItemSword implements IExperienceRepair, ITooltip {

	public static int cooldown = 0;
	private ExperienceRepair experienceRepair;

	public ItemExperienceIronSword(ToolMaterial mate, int cooltime, int cost) {
		super(mate);
		this.experienceRepair = new ExperienceRepair(cooltime, cost);
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, IBlockState state, BlockPos pos,
			EntityLivingBase entityLiving) {
		ParticleUtil.blockSurface(EnumParticleTypes.VILLAGER_HAPPY, world, pos, 10);
		ParticleUtil.blockRemaining(EnumParticleTypes.VILLAGER_HAPPY, world, pos, 10);

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
		player.worldObj.playSound(entity.posX, entity.posY, entity.posZ, SoundEvents.ENTITY_SHEEP_SHEAR,
				SoundCategory.PLAYERS, 1, 1, false);
		ParticleUtil.ball(EnumParticleTypes.END_ROD, entity.worldObj, entity.posX, entity.posY, entity.posZ, 2, 50);
		return false;
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		if (!GuiScreen.isShiftKeyDown())
			return;
		tooltip.add(I18n.format("item.experienceIron.cooltime") + " : " + this.experienceRepair.getCooltime());
		tooltip.add(I18n.format("item.experienceIron.cost") + " : " + this.experienceRepair.getCost());

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
