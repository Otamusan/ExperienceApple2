package Item.Tools;

import java.util.ArrayList;
import java.util.List;

import ExperienceApple.ITooltip;
import Util.ParticleUtil;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemAdvancedExperienceIronPickaxe extends ItemPickaxe implements ITooltip {

	public static int cooldown = 0;
	public Integer range = 3;

	public ItemAdvancedExperienceIronPickaxe(ToolMaterial mate) {
		super(mate);
	}

	public float getStrVsBlock(ItemStack stack, IBlockState state) {
		return this.toolMaterial.getEfficiencyOnProperMaterial();
	}

	public boolean canHarvestBlock(IBlockState blockIn) {
		return true;
	}

	@Override
	public void onCreated(ItemStack stack, World world, EntityPlayer player) {
		stack.addEnchantment(Enchantments.FORTUNE, 7);
		stack.addEnchantment(Enchantments.EFFICIENCY, 5);
		stack.addEnchantment(Enchantments.UNBREAKING, 7);
	}

	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn,
			EnumHand hand) {
		if (playerIn.isSneaking()) {
			range--;
		} else {
			range++;
		}
		if (range < 0) {
			range = 0;
		}
		playerIn.addChatMessage(new TextComponentTranslation(range.toString()));
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemStackIn);
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, IBlockState state, BlockPos pos,
			EntityLivingBase entityLiving) {
		ParticleUtil.blockSurface(EnumParticleTypes.FIREWORKS_SPARK, world, pos, 10);
		ParticleUtil.blockRemaining(EnumParticleTypes.FIREWORKS_SPARK, world, pos, 10);
		if (!entityLiving.isSneaking())
			return false;
		for (int ix = -range; ix < range; ix++) {
			for (int iy = -range; iy < range; iy++) {
				for (int iz = -range; iz < range; iz++) {
					BlockPos xpPos = new BlockPos(ix + pos.getX(), iy + pos.getY(), iz + pos.getZ());
					// state.getBlock().harvestBlock(world, (EntityPlayer)
					// entityLiving, xpPos,
					// world.getBlockState(xpPos).getBlock().getDefaultState(),
					// world.getTileEntity(xpPos), stack);
					world.destroyBlock(xpPos, true);
					// state.getBlock().breakBlock(world, xpPos, state);
					// world.setBlockToAir(xpPos);
					// System.out.println(world.isRemote);
				}
			}
		}
		return false;
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
		ParticleUtil.verticalCircle(EnumParticleTypes.FIREWORKS_SPARK, entity.getEntityWorld(), entity.posX,
				entity.posY + entity.getMaxFallHeight() / 2, entity.posZ, 1, 12);
		return false;
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
