package Item.Tools;

import java.util.List;
import java.util.Set;

import com.google.common.collect.Sets;

import ExperienceApple.EAMain;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import scala.util.Random;

public class ItemUnstableAxe extends ItemTool {

	private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(new Block[] { Blocks.PLANKS, Blocks.BOOKSHELF,
			Blocks.LOG, Blocks.LOG2, Blocks.CHEST, Blocks.PUMPKIN, Blocks.LIT_PUMPKIN, Blocks.MELON_BLOCK,
			Blocks.LADDER, Blocks.WOODEN_BUTTON, Blocks.WOODEN_PRESSURE_PLATE });

	public ItemUnstableAxe() {
		super(ToolMaterial.DIAMOND, EFFECTIVE_ON);
		this.attackDamage = 10;
		this.attackSpeed = -4.0F;
	}

	@Override
	public float getDestroySpeed(ItemStack stack, net.minecraft.block.state.IBlockState state) {
		Material material = state.getMaterial();
		return material != Material.WOOD && material != Material.PLANTS && material != Material.VINE
				? super.getDestroySpeed(stack, state) : this.efficiency;
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, IBlockState state, BlockPos pos,
			EntityLivingBase entityLiving) {
		world.createExplosion(entityLiving, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 4, false);

		return false;
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
		player.world.createExplosion(player, entity.posX, entity.posY + entity.height / 2, entity.posZ + 0.5, 3, false);

		return false;
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
		if (isSelected && new Random().nextDouble() > 0.95) {
			world.createExplosion(entity, entity.posX, entity.posY + entity.height / 2, entity.posZ, 2, false);
		}
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		List<Entity> entities = worldIn.loadedEntityList;
		for (Entity entity : entities) {
			if (playerIn.getDistance(entity) < 50 && entity instanceof EntityLivingBase && entity != playerIn) {

				double x = (-entity.posX + playerIn.posX) / 5;
				double y = (-entity.posY + playerIn.posY) / 5;
				double z = (-entity.posZ + playerIn.posZ) / 5;
				entity.setVelocity(x, y, z);
				if (!EAMain.particle) {
					for (int i = 0; i < 10; i++) {
						Random random = new Random();
						worldIn.spawnParticle(EnumParticleTypes.END_ROD, entity.posX + random.nextFloat(),
								entity.posY + random.nextFloat(), entity.posZ + random.nextFloat(),
								0.5 - random.nextFloat(), 0.5 - random.nextFloat(), 0.5 - random.nextFloat());
					}
				}
			}
		}
		worldIn.playSound(playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_ENDERMEN_TELEPORT,
				SoundCategory.PLAYERS, 1, 1, true);

		return new ActionResult<ItemStack>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
	}

}
