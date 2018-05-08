package Item.Tools;

import java.util.ArrayList;
import java.util.List;

import ExperienceApple.EAMain;
import ExperienceApple.ITooltip;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemWeakExperienceIronAxe extends ItemAxe implements ITooltip {
	public ItemWeakExperienceIronAxe() {
		super(Item.ToolMaterial.IRON);
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, IBlockState state, BlockPos pos,
			EntityLivingBase entityLiving) {
		if (!EAMain.particle) {
			for (int n = 0; n < 3; n++) {
				world.spawnParticle(EnumParticleTypes.VILLAGER_HAPPY, pos.getX() + Math.random(),
						pos.getY() + Math.random(), pos.getZ() + Math.random(), 0.0D, 0.0D, 0.0D);
			}
		}

		if (!world.isRemote && state.getBlockHardness(world, pos) != 0.0D) {
			stack.damageItem(1, entityLiving);
		}
		return false;
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
		for (int n = 0; n < 3; n++) {
			if (!EAMain.particle) {
				entity.getEntityWorld().spawnParticle(EnumParticleTypes.VILLAGER_HAPPY,
						entity.posX + Math.random() - 0.5, entity.posY + Math.random() * 2,
						entity.posZ + Math.random() - 0.5, 0.0D, 0.0D, 0.0D);
			}
		}
		return false;
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
