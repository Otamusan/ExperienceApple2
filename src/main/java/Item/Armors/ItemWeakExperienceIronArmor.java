package Item.Armors;

import java.util.Random;

import Client.Particle.EAParticleFunc;
import Client.Particle.EAParticleFuncs;
import Client.Particle.PF;
import Client.Particle.PS;
import Util.Vec.Vec;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemWeakExperienceIronArmor extends ItemArmor {

	public ItemWeakExperienceIronArmor(ArmorMaterial materialIn, int renderIndexIn,
			EntityEquipmentSlot equipmentSlotIn) {
		super(materialIn, renderIndexIn, equipmentSlotIn);
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
		Random random = new Random();
		if (random.nextInt(50) == 1) {
			EAParticleFuncs funcs = new EAParticleFuncs(
					new EAParticleFunc(10, 0, 0, 0, PF.setBlink(1), PF.setColor(0, 1, 0, 0.24f)), 1,
					PS.randomSquare(1, 2, 1));
			funcs.spawn(world, new Vec(player.posX - 0.5, player.posY, player.posZ - 0.5));
		}
	}

	@Override
	public void onCreated(ItemStack stack, World world, EntityPlayer player) {
		world.playSound(player, new BlockPos(player), SoundEvents.ENTITY_BLAZE_HURT, SoundCategory.PLAYERS, 1, 1);
	}

}
