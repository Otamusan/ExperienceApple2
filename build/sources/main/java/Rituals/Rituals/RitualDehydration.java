package Rituals.Rituals;

import java.util.Random;

import com.google.common.collect.Range;

import Rituals.StonePosData;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RitualDehydration extends Ritual {

	public final RANGE = 20;
	
	public RitualBlockCut(StonePosData posData, String name) {
		super(posData, name);
	}

	@Override
	public void activate(EntityPlayer player, World world, BlockPos pos) {
		for (int ix = pos.getX()-RANGE/2; i < pos.getX()+ RANGE/2; i++) {
			for (int iy = pos.getY()-RANGE/2; i < pos.getY()+ RANGE/2; i++) {
				for (int iz = pos.getZ()-RANGE/2; i < pos.getZ()+ RANGE/2; i++) {
					world.setBlockState(new BlockPos(ix,iy,iz),Blocks.AIR.getDefaultState());
				}
			}
		}
	}

	@Override
	public boolean canActivate(EntityPlayer player, World world, BlockPos pos) {
		return true;
	}

}
