package Rituals.Rituals.RitualCraft;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import Rituals.StonePosData;
import Rituals.Rituals.Ritual;
import Util.EntityItemUtil;
import Util.ItemList;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RitualCraftCore extends Ritual {
	public RitualCraftRegister register;

	public RitualCraftCore(StonePosData posData, String name) {
		super(posData, name);
		register = new RitualCraftRegister();
	}

	@Override
	public void activate(EntityPlayer player, World world, BlockPos pos) {
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		String name = getClaft(world, new BlockPos(x, y + 1, z));

		List<EntityItem> entityitemlist = EntityItemUtil.getEntityItemListFromPos(world, new BlockPos(x, y + 1, z));
		for (ItemStack item : this.register.getList().get(name).itemlist) {

			for (EntityItem entityItem : entityitemlist) {
				if (item.isItemEqual(entityItem.getItem())) {
					entityItem.setDead();
				}
			}
		}

		if (!world.isRemote) {
			ItemStack itemstack = this.register.getList().get(name).item.copy();
			EntityItem itementity = new EntityItem(world, x + 0.5, y + 2.5, z + 0.5, itemstack);
			world.spawnEntity(itementity);
		}
		world.setBlockState(new BlockPos(x, y + 1, z), Blocks.CAULDRON.getDefaultState());
		for (int i = 0; i < 10; i++) {
			world.spawnParticle(EnumParticleTypes.LAVA, x + 0.5, y + 1.5, z + 0.5, 0, 0, 0);
		}
		world.playSound(x, y, z, SoundEvents.ENTITY_FIREWORK_LAUNCH, SoundCategory.BLOCKS, 3, 1, false);
	}

	@Override
	public boolean canActivate(EntityPlayer player, World world, BlockPos pos) {
		if (world.getBlockState(new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ())).getBlock() != Blocks.CAULDRON)
			return false;

		if (world.getBlockState(new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ())).getBlock()
				.getMetaFromState(world.getBlockState(new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ()))) != 3)
			return false;
		if (getClaft(world, new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ())) == null)
			return false;
		if (!this.register.getList().get(getClaft(world, new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ())))
				.canActivate(player, world, new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ())))
			return false;

		return true;
	}

	public String getClaft(World world, BlockPos pos) {
		Map<String, RitualCraft> ritualdatas = this.register.getList();

		for (Entry<String, RitualCraft> ritualdataEntry : ritualdatas.entrySet()) {
			String ritualdataname = ritualdataEntry.getKey();
			ArrayList<ItemStack> data = ritualdatas.get(ritualdataname).itemlist;
			ArrayList<ItemStack> list = EntityItemUtil.getItemListFromPos(world, pos);

			if (ItemList.isItemListEqual(data, list)) {
				return ritualdataname;
			}
		}
		return null;
	}
}
