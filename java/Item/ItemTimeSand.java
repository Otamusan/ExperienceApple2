package Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ExperienceApple.EAMain;
import ExperienceApple.ITooltip;
import ExperienceApple.Register.BlockRegister;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemTimeSand extends Item implements ITooltip {
	private int acc;

	public ItemTimeSand(int acc) {
		this.acc = acc;
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		if (!GuiScreen.isShiftKeyDown())
			return;
		tooltip.add(I18n.format("item.tick.name") + " : " + this.acc);
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		TileEntity entity = world.getTileEntity(pos);

		// if (world.isRemote)
		// return EnumActionResult.PASS;
		if (world.getBlockState(pos).getBlock() == BlockRegister.accelerateStone)
			return EnumActionResult.PASS;
		if (world.getBlockState(pos).getBlock() == BlockRegister.advancedAccelerateStone)
			return EnumActionResult.PASS;
		if (entity == null)
			return EnumActionResult.PASS;
		if (entity.isInvalid())
			return EnumActionResult.PASS;

		if (!(entity instanceof ITickable))
			return EnumActionResult.PASS;

		for (int ix = 0; ix < acc; ix++) {
			((ITickable) entity).update();
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
				world.spawnParticle(EnumParticleTypes.PORTAL, rx, ry, rz, sx, sy, sz);
			}
		}

		player.getHeldItem(hand).stackSize--;

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