package Item.Tools;

import ExperienceApple.EAMain;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemAtomicCutter extends Item {

	public static int cooldown = 0;

	public ItemAtomicCutter() {
		super();
	}

	@Override

	public EnumActionResult onItemUse(ItemStack item, EntityPlayer player, World world, BlockPos pos,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		if (!world.isRemote) {
			ItemStack itemstack = new ItemStack(Item.getItemFromBlock(world.getBlockState(pos).getBlock()));
			world.setBlockToAir(pos);
			EntityItem itementity = new EntityItem(world, x + 0.5, y + 0.5, z + 0.5,
					itemstack);
			world.spawnEntityInWorld(itementity);

		}
		if (!EAMain.particle) {
			for (int i = 0; i < 20; i++) {
				world.spawnParticle(EnumParticleTypes.CRIT_MAGIC, x + Math.random(), y + Math.random(),
						z + Math.random(), 0.0D,
						0.0D, 0.0D);
			}
		}
		world.playSound(x, y, z, SoundEvents.ENTITY_SHEEP_SHEAR, SoundCategory.PLAYERS, 1, 1, true);
		return EnumActionResult.SUCCESS;
	}
}
