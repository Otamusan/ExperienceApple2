package Rituals.Rituals;

import java.util.Random;

import Rituals.StonePosData;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RitualBlockCut extends Ritual {

	public RitualBlockCut(StonePosData posData, String name) {
		super(posData, name);
	}

	@Override
	public void activate(EntityPlayer player, World world, BlockPos pos) {
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		if (!world.isRemote) {

			ItemStack itemstack = new ItemStack(Item.getItemFromBlock(world.getBlockState(pos.down()).getBlock()), 1,
					world.getBlockState(pos.down()).getBlock().getMetaFromState(world.getBlockState(pos.down())));
			EntityItem itementity = new EntityItem(world, x, y - 1, z, itemstack);
			world.spawnEntity(itementity);
		}
		for (int i = 0; i < 50; i++) {
			Random r = new Random();
			world.spawnParticle(EnumParticleTypes.BLOCK_CRACK, pos.getX() + r.nextFloat(),
					pos.getY() + r.nextFloat() - 1, pos.getZ() + r.nextFloat(), 0, 0, 0,
					Block.getStateId(world.getBlockState(pos.down())));
		}
		world.playSound(x, y, z,
				world.getBlockState(pos.down()).getBlock()
						.getSoundType(world.getBlockState(pos.down()), world, pos, player).getBreakSound(),
				SoundCategory.BLOCKS, 0.5f, 1, true);
		world.setBlockToAir(new BlockPos(x, y - 1, z));
	}

	@Override
	public boolean canActivate(EntityPlayer player, World world, BlockPos pos) {
		return true;
	}

}
