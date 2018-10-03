package Item.Tools;

import java.util.List;
import java.util.Set;

import com.google.common.collect.Sets;

import Client.Particle.EAParticleFunc;
import Client.Particle.EAParticleFuncs;
import Client.Particle.PF;
import Client.Particle.PS;
import Util.Vec.Vec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLog;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemAdvancedExperienceIronAxe extends ItemTool {

	private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(new Block[] { Blocks.PLANKS, Blocks.BOOKSHELF,
			Blocks.LOG, Blocks.LOG2, Blocks.CHEST, Blocks.PUMPKIN, Blocks.LIT_PUMPKIN, Blocks.MELON_BLOCK,
			Blocks.LADDER, Blocks.WOODEN_BUTTON, Blocks.WOODEN_PRESSURE_PLATE });

	public ItemAdvancedExperienceIronAxe(ToolMaterial mate) {
		super(mate, EFFECTIVE_ON);
		this.attackDamage = mate.getAttackDamage();
		this.attackSpeed = -3.2F;
	}

	@Override
	public float getDestroySpeed(ItemStack stack, IBlockState state) {
		Material material = state.getMaterial();
		return material != Material.WOOD && material != Material.PLANTS && material != Material.VINE
				? super.getDestroySpeed(stack, state) : this.efficiency;
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, IBlockState state, BlockPos pos,
			EntityLivingBase entityLiving) {

		EAParticleFuncs funcs = new EAParticleFuncs(
				new EAParticleFunc(20, 0, 0, 0, PF.setBlink(1), PF.setColor(1, 1, 1, 0.7f), PF.setGravity(0.02)), 10,
				PS.randomBall(0.3), PS.spread(0.5, Vec.getZero()));
		funcs.spawn(world, new Vec(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5));

		if (!entityLiving.isSneaking())
			return false;
		if (!(world.getBlockState(pos).getBlock() instanceof BlockLog)
				&& !(world.getBlockState(pos).getBlock() instanceof BlockLeaves))
			return false;

		cheinDestruction(pos, world, (EntityPlayer) entityLiving, world.getBlockState(pos).getBlock());
		world.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE,
				SoundCategory.PLAYERS, 1, 0, true);
		return false;
	}

	public static void cheinDestruction(BlockPos pos, World world, EntityPlayer player, Block block) {
		world.destroyBlock(pos, true);
		EAParticleFuncs funcs = new EAParticleFuncs(
				new EAParticleFunc(20, 0, 0, 0, PF.setBlink(1), PF.setColor(1, 1, 1, 0.7f), PF.setGravity(0.02)), 10,
				PS.randomBall(0.3), PS.spread(0.5, Vec.getZero()));
		funcs.spawn(world, new Vec(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5));
		world.getBlockState(pos).getBlock().onBlockDestroyedByPlayer(world, pos, world.getBlockState(pos));
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		if (world.getBlockState(new BlockPos(x, y, z + 1)).getBlock() == block)
			cheinDestruction(new BlockPos(x, y, z + 1), world, player, block);
		if (world.getBlockState(new BlockPos(x, y, z - 1)).getBlock() == block)
			cheinDestruction(new BlockPos(x, y, z - 1), world, player, block);

		if (world.getBlockState(new BlockPos(x, y + 1, z)).getBlock() == block)
			cheinDestruction(new BlockPos(x, y + 1, z), world, player, block);
		if (world.getBlockState(new BlockPos(x, y + 1, z + 1)).getBlock() == block)
			cheinDestruction(new BlockPos(x, y + 1, z + 1), world, player, block);
		if (world.getBlockState(new BlockPos(x, y + 1, z - 1)).getBlock() == block)
			cheinDestruction(new BlockPos(x, y + 1, z - 1), world, player, block);

		if (world.getBlockState(new BlockPos(x, y - 1, z)).getBlock() == block)
			cheinDestruction(new BlockPos(x, y - 1, z), world, player, block);
		if (world.getBlockState(new BlockPos(x, y - 1, z + 1)).getBlock() == block)
			cheinDestruction(new BlockPos(x, y - 1, z + 1), world, player, block);
		if (world.getBlockState(new BlockPos(x, y - 1, z - 1)).getBlock() == block)
			cheinDestruction(new BlockPos(x, y - 1, z - 1), world, player, block);

		if (world.getBlockState(new BlockPos(x + 1, y, z)).getBlock() == block)
			cheinDestruction(new BlockPos(x + 1, y, z), world, player, block);
		if (world.getBlockState(new BlockPos(x + 1, y, z + 1)).getBlock() == block)
			cheinDestruction(new BlockPos(x + 1, y, z + 1), world, player, block);
		if (world.getBlockState(new BlockPos(x + 1, y, z - 1)).getBlock() == block)
			cheinDestruction(new BlockPos(x + 1, y, z - 1), world, player, block);

		if (world.getBlockState(new BlockPos(x + 1, y + 1, z)).getBlock() == block)
			cheinDestruction(new BlockPos(x + 1, y + 1, z), world, player, block);
		if (world.getBlockState(new BlockPos(x + 1, y + 1, z + 1)).getBlock() == block)
			cheinDestruction(new BlockPos(x + 1, y + 1, z + 1), world, player, block);
		if (world.getBlockState(new BlockPos(x + 1, y + 1, z - 1)).getBlock() == block)
			cheinDestruction(new BlockPos(x + 1, y + 1, z - 1), world, player, block);

		if (world.getBlockState(new BlockPos(x + 1, y - 1, z)).getBlock() == block)
			cheinDestruction(new BlockPos(x + 1, y - 1, z), world, player, block);
		if (world.getBlockState(new BlockPos(x + 1, y - 1, z + 1)).getBlock() == block)
			cheinDestruction(new BlockPos(x + 1, y - 1, z + 1), world, player, block);
		if (world.getBlockState(new BlockPos(x + 1, y - 1, z - 1)).getBlock() == block)
			cheinDestruction(new BlockPos(x + 1, y - 1, z - 1), world, player, block);

		if (world.getBlockState(new BlockPos(x - 1, y, z)).getBlock() == block)
			cheinDestruction(new BlockPos(x - 1, y, z), world, player, block);
		if (world.getBlockState(new BlockPos(x - 1, y, z + 1)).getBlock() == block)
			cheinDestruction(new BlockPos(x - 1, y, z + 1), world, player, block);
		if (world.getBlockState(new BlockPos(x - 1, y, z - 1)).getBlock() == block)
			cheinDestruction(new BlockPos(x - 1, y, z - 1), world, player, block);

		if (world.getBlockState(new BlockPos(x - 1, y + 1, z)).getBlock() == block)
			cheinDestruction(new BlockPos(x - 1, y + 1, z), world, player, block);
		if (world.getBlockState(new BlockPos(x - 1, y + 1, z + 1)).getBlock() == block)
			cheinDestruction(new BlockPos(x - 1, y + 1, z + 1), world, player, block);
		if (world.getBlockState(new BlockPos(x - 1, y + 1, z - 1)).getBlock() == block)
			cheinDestruction(new BlockPos(x - 1, y + 1, z - 1), world, player, block);

		if (world.getBlockState(new BlockPos(x - 1, y - 1, z)).getBlock() == block)
			cheinDestruction(new BlockPos(x - 1, y - 1, z), world, player, block);
		if (world.getBlockState(new BlockPos(x - 1, y - 1, z + 1)).getBlock() == block)
			cheinDestruction(new BlockPos(x - 1, y - 1, z + 1), world, player, block);
		if (world.getBlockState(new BlockPos(x - 1, y - 1, z - 1)).getBlock() == block)
			cheinDestruction(new BlockPos(x - 1, y - 1, z - 1), world, player, block);

		return;
	}

	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if (!isSelected)
			return;
		if (!(entityIn instanceof EntityLivingBase))
			return;
		List<Entity> entities = worldIn.loadedEntityList;
		for (Entity entity : entities) {
			if (entityIn.getDistance(entity) < 25 && (entity instanceof EntityLivingBase) && (entityIn != entity)) {
				((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.WITHER, 10, 10, true, true));
				((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.POISON, 10, 10, true, true));
				((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 10, 10, true, true));
				// ParticleUtil.blockInjection(EnumParticleTypes.PORTAL,
				// entity.world, new BlockPos(0, 0, 0),
				// entity.getPosition(), 5);
				EAParticleFuncs funcs = new EAParticleFuncs(
						new EAParticleFunc(20, 0, 0, 0, PF.setBlink(1), PF.setColor(0.5f, 0, 1, 0.24f)), 1,
						PS.randomBall(2), PS.convergence(0.01, Vec.getZero()));
				funcs.spawn(entity.world, new Vec(entity.posX, entity.posY, entity.posZ));

			}
		}
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
		EAParticleFuncs funcs = new EAParticleFuncs(
				new EAParticleFunc(20, 0, 0, 0, PF.setBlink(1), PF.setColor(1, 1, 1, 0.7f), PF.setGravity(0.02)), 10,
				PS.randomBall(0.3), PS.spread(0.5, Vec.getZero()));
		funcs.spawn(entity.world, new Vec(entity.posX, entity.posY, entity.posZ));
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {
		return true;
	}
}
