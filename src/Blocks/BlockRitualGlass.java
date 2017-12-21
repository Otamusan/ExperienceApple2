package Blocks;

import javax.annotation.Nullable;

import Rituals.RitualCore;
import Rituals.RitualStones;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockRitualGlass extends BlockRitual {

	public BlockRitualGlass(Material materialIn, int particleAmount, RitualStones tier) {
		super(materialIn, particleAmount, tier);
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			@Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		RitualCore.ActRitual(player, world, pos);
		return true;
	}
}
