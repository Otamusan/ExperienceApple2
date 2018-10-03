package Common;

import Blocks.ILeftClick;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.LeftClickBlock;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BlockLeftClick {
	@SubscribeEvent
	public void EventSubscriber(LeftClickBlock event) {

		World world = event.getWorld();
		BlockPos pos = event.getPos();
		Block block = world.getBlockState(pos).getBlock();

		EntityPlayer player = event.getEntityPlayer();
		if (block instanceof ILeftClick) {
			ILeftClick leftClick = (ILeftClick) block;
			leftClick.onLeftClick(player, pos, world, event.getFace());
		}

	}

}