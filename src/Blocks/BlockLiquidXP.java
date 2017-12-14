package Blocks;

import ExperienceApple.EAMain;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class BlockLiquidXP extends BlockFluidClassic {
	public BlockLiquidXP() {
		super(liquidXPFluid, Material.WATER);
		this.setUnlocalizedName("LiquidXP");
		this.setCreativeTab(EAMain.tabAdd);
	}

	public static ModelResourceLocation liquidXPLocation = new ModelResourceLocation(EAMain.MOD_ID + ":LiquidXP",
			"liquidxp");

	public static Fluid liquidXPFluid = new Fluid("liquidxp",
			new ResourceLocation(EAMain.MOD_ID, "blocks/liquidxpstill"),
			new ResourceLocation(EAMain.MOD_ID, "blocks/liquidxpflow"));
}