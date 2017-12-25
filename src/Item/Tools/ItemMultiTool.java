package Item.Tools;

import java.util.Set;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;

public class ItemMultiTool extends ItemTool {
	private ToolFunction tool;
	private String toolClass;

	public ItemMultiTool(ToolMaterial materialIn, ToolFunction tool) {
		super(materialIn, tool.getEffectiveBlocks());
		this.tool = tool;
		this.toolClass = "pickaxe";
	}

	@Override
	public int getHarvestLevel(ItemStack stack, String toolClass) {
		int level = super.getHarvestLevel(stack, toolClass);
		if (level == -1 && toolClass != null && toolClass.equals(this.toolClass)) {
			return this.toolMaterial.getHarvestLevel();
		} else {
			return level;
		}
	}

	@Override
	public Set<String> getToolClasses(ItemStack stack) {
		return toolClass != null ? com.google.common.collect.ImmutableSet.of(toolClass) : super.getToolClasses(stack);
	}

	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		if (!tool.isSword())
			return false;
		for (EntityLivingBase entitylivingbase : attacker.worldObj.getEntitiesWithinAABB(EntityLivingBase.class,
				target.getEntityBoundingBox().expand(1.0D, 0.25D, 1.0D))) {
			if (entitylivingbase != attacker && entitylivingbase != target && !attacker.isOnSameTeam(entitylivingbase)
					&& attacker.getDistanceSqToEntity(entitylivingbase) < 9.0D) {
				entitylivingbase.knockBack(attacker, 0.4F, (double) MathHelper.sin(attacker.rotationYaw * 0.017453292F),
						(double) (-MathHelper.cos(attacker.rotationYaw * 0.017453292F)));
				entitylivingbase.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) attacker), 1.0F);
			}
		}

		attacker.worldObj.playSound((EntityPlayer) null, attacker.posX, attacker.posY, attacker.posZ,
				SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, attacker.getSoundCategory(), 1.0F, 1.0F);
		((EntityPlayer) attacker).spawnSweepParticles();
		return true;
	}

	public ToolFunction getTool() {
		return tool;
	}

	public void setTool(ToolFunction tool) {
		this.tool = tool;
	}
}
