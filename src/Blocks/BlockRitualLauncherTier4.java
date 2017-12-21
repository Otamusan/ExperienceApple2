package Blocks;
import java.util.Random;
import java.util.UUID;

import javax.annotation.Nullable;

import ExperienceApple.EAMain;
import GlassRituals.RitualCore;
import TileEntity.TileRitualLauncher;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGlass;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
public class BlockRitualLauncherTier4 extends BlockGlass implements ITileEntityProvider{


    public BlockRitualLauncherTier4(Material materialIn) {
        super(materialIn,true);
    }

    @Override
	public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rnd) {
		if (EAMain.particle == true)
			return;
		for (int i = 0; i < 16; i++) {
		double rx = pos.getX() + rnd.nextFloat();
		double ry = pos.getY() + rnd.nextFloat();
		double rz = pos.getZ() + rnd.nextFloat();
		world.spawnParticle(EnumParticleTypes.VILLAGER_HAPPY, rx, ry, rz, 0.0D, 0.0D, 0.0D);
		}
	}
    @Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileRitualLauncher();
	}
	@Override
	public int quantityDropped(Random random) {
		return 1;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return new AxisAlignedBB(0.125, 0.125, 0.125, 0.875, 0.875, 0.875);
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			@Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		TileRitualLauncher entity=(TileRitualLauncher)world.getTileEntity(pos);
		UUID UUIDPlayer=player.getPersistentID();
    	if (!player.isSneaking()){
    		entity.setString(UUIDPlayer.toString());
			player.addChatMessage(new TextComponentTranslation(player.getDisplayNameString()));

    	}else{
    		if (!world.isRemote){
    			if (entity.getString()=="null"){
    				player.addChatMessage(new TextComponentTranslation("Not registered!"));
    				return true;
    			}
    			//EntityPlayer playerE=ritualGlass.lookupPlayer(UUID.fromString(entity.getString()));
    			EntityPlayer playerE=world.getPlayerEntityByUUID(UUID.fromString(entity.getString()));
    			if (!(playerE==null)){
    				player.addChatMessage(new TextComponentTranslation(playerE.getDisplayName().getFormattedText()));
    			}else{
    				player.addChatMessage(new TextComponentTranslation("Not registered!"));
    			}
    		}
    	}
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}
	
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn)
    {
        boolean flag = worldIn.isBlockPowered(pos);
        TileEntity tileentity = worldIn.getTileEntity(pos);

        if (tileentity instanceof TileRitualLauncher)
        {
        	TileRitualLauncher tilerituallauncher = (TileRitualLauncher)tileentity;

            if (tilerituallauncher.previousRedstoneState != flag)
            {
                if (flag)
                {
                	if (tilerituallauncher.Eplayer!="null"){
        				RitualCore.ritualActive(worldIn.getPlayerEntityByUUID(UUID.fromString(tilerituallauncher.Eplayer)), worldIn, pos.getX(), pos.getY(), pos.getZ());
        			}
                }

                tilerituallauncher.previousRedstoneState = flag;
            }
        }
    }
}
