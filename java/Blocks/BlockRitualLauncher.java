package Blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;

import ExperienceApple.ITooltip;
import Rituals.RitualCore;
import Rituals.EnumRitualStones;
import TileEntity.TileRitualLauncher;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class BlockRitualLauncher extends BlockRitual implements ITileEntityProvider, ITooltip {

	public BlockRitualLauncher(Material materialIn, int particleAmount, EnumRitualStones tier) {
		super(materialIn, particleAmount, tier);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileRitualLauncher();
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			@Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		TileRitualLauncher entity = (TileRitualLauncher) world.getTileEntity(pos);
		UUID UUIDPlayer = player.getPersistentID();
		if (!player.isSneaking()) {
			entity.setString(UUIDPlayer.toString());
			player.addChatMessage(new TextComponentTranslation(player.getDisplayNameString()));

		} else {
			if (!world.isRemote) {
				if (entity.getString() == "null") {
					player.addChatMessage(new TextComponentTranslation("Not registered!"));
					return true;
				}
				// EntityPlayer
				// playerE=ritualGlass.lookupPlayer(UUID.fromString(entity.getString()));
				EntityPlayer playerE = world.getPlayerEntityByUUID(UUID.fromString(entity.getString()));
				if (!(playerE == null)) {
					player.addChatMessage(new TextComponentTranslation(playerE.getDisplayName().getFormattedText()));
				} else {
					player.addChatMessage(new TextComponentTranslation("Not registered!"));
				}
			}
		}
		return true;
	}

	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn) {
		boolean flag = worldIn.isBlockPowered(pos);
		TileEntity tileentity = worldIn.getTileEntity(pos);

		if (tileentity instanceof TileRitualLauncher) {
			TileRitualLauncher tilerituallauncher = (TileRitualLauncher) tileentity;

			if (tilerituallauncher.previousRedstoneState != flag) {
				if (flag) {
					if (tilerituallauncher.Eplayer != "null") {
						tilerituallauncher.activate();
					}
				}

				tilerituallauncher.previousRedstoneState = flag;
			}
		}
	}

	public boolean eventReceived(IBlockState state, World worldIn, BlockPos pos, int id, int param) {
		TileEntity tileentity = worldIn.getTileEntity(pos);
		TileRitualLauncher tilerituallauncher = (TileRitualLauncher) tileentity;

		RitualCore.ActRitual(worldIn.getPlayerEntityByUUID(UUID.fromString(tilerituallauncher.Eplayer)), worldIn, pos);

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
