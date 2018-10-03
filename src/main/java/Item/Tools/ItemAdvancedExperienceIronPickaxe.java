package Item.Tools;

import java.util.ArrayList;
import java.util.List;

import Client.Particle.EAParticleFunc;
import Client.Particle.EAParticleFuncs;
import Client.Particle.PF;
import Client.Particle.PS;
import Util.Vec.Vec;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemAdvancedExperienceIronPickaxe extends ItemPickaxe {
	public ItemAdvancedExperienceIronPickaxe(ToolMaterial mate) {
		super(mate);
	}

	public float getStrVsBlock(ItemStack stack, IBlockState state) {
		return this.toolMaterial.getEfficiency();
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

	public int getRange(ItemStack itemStack) {
		NBTTagCompound nbt;
		if (itemStack.getTagCompound() == null) {
			nbt = new NBTTagCompound();
			nbt.setInteger("range", 0);
			itemStack.setTagCompound(nbt);
			return 0;
		} else {
			nbt = itemStack.getTagCompound();
			return nbt.getInteger("range");
		}
	}

	public void setRange(ItemStack itemStack, int ritual) {
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setInteger("range", ritual);
		itemStack.setTagCompound(nbt);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand) {
		ItemStack itemStackIn = playerIn.getHeldItem(hand);
		int range = getRange(itemStackIn);
		if (worldIn.isRemote)
			return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemStackIn);

		if (playerIn.isSneaking()) {
			range--;
		} else {
			range++;
		}
		if (range < 0) {
			range = 0;
		}
		playerIn.sendMessage(new TextComponentTranslation(
				I18n.format("item.advancedExperienceIronPickaxe.range") + " : " + new Integer(range).toString()));
		setRange(itemStackIn, range);
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemStackIn);
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, IBlockState state, BlockPos pos,
			EntityLivingBase entityLiving) {

		if (!entityLiving.isSneaking())
			return false;
		world.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE,
				SoundCategory.PLAYERS, 1, 0, true);

		int range = getRange(stack);
		for (int ix = -range; ix < range + 1; ix++) {
			for (int iy = -range; iy < range + 1; iy++) {
				for (int iz = -range; iz < range + 1; iz++) {
					BlockPos xpPos = new BlockPos(ix + pos.getX(), iy + pos.getY(), iz + pos.getZ());
					Block block = world.getBlockState(xpPos).getBlock();
					block.onBlockDestroyedByPlayer(world, xpPos, world.getBlockState(xpPos));
					block.dropXpOnBlockBreak(world, pos, block.getExpDrop(world.getBlockState(xpPos), world, xpPos, 0));
					world.destroyBlock(xpPos, true);
					EAParticleFuncs funcs = new EAParticleFuncs(new EAParticleFunc(20, 0, 0, 0, PF.setBlink(1),
							PF.setColor(1, 1, 1, 0.7f), PF.setGravity(0.02)), 10, PS.randomBall(0.3),
							PS.spread(0.5, Vec.getZero()));
					funcs.spawn(world, new Vec(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5));
				}
			}
		}

		return false;
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
		EAParticleFuncs funcs = new EAParticleFuncs(
				new EAParticleFunc(20, 0, 0, 0, PF.setBlink(1), PF.setColor(1, 1, 1, 0.7f)), 3,
				PS.randomSquare(1, 1, 1));
		funcs.spawn(entity.world, new Vec(entity.posX, entity.posY, entity.posZ));
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		return true;
	}

	public List<String> Tooltip = new ArrayList<String>();
}
