package Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ExperienceApple.EAMain;
import ExperienceApple.ITooltip;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemUniversalNutrient extends Item implements ITooltip {
	private int acc;

	public ItemUniversalNutrient(int acc) {
		this.acc = acc;
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing,
			float hitX, float hitY, float hitZ) {
		for (int ix = 0; ix < acc; ix++) {
			world.getBlockState(pos).getBlock().updateTick(world, pos, world.getBlockState(pos), new Random());
		}

		if (!EAMain.particle) {
			Random rnd = new Random();
			for (int i = 0; i < 16; i++) {
				double rx = pos.getX() + rnd.nextFloat();
				double ry = pos.getY() + rnd.nextFloat();
				double rz = pos.getZ() + rnd.nextFloat();
				double sx = rnd.nextFloat() * 3 - 1.5;
				double sy = rnd.nextFloat() * 2 - 1.5;
				double sz = rnd.nextFloat() * 3 - 1.5;
				world.spawnParticle(EnumParticleTypes.VILLAGER_HAPPY, rx, ry, rz, sx, sy, sz);

			}
		}
		player.getHeldItem(hand).shrink(1);

		return EnumActionResult.SUCCESS;
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
