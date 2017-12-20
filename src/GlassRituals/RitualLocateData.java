package GlassRituals;

import ExperienceApple.Register.BlockRegister;
import net.minecraft.block.Block;

public class RitualLocateData {
	public static final int ritualAmount = 30;
	public static final int ritualsize = 4;
	public static String ritualname[] = new String[ritualAmount];

	public static final Block ritualLocate[][][][] = new Block[ritualAmount][ritualsize - 1][ritualsize - 1][ritualsize
			- 1];

	public static Block Data(int n, int x, int y, int z) {

		for (int in = 0; in < 3; in++) {
			for (int ix = 0; ix < 3; ix++) {
				for (int iy = 0; iy < 3; iy++) {
					for (int iz = 0; iz < 3; iz++) {
						ritualLocate[in][ix][iy][iz] = null;
					}
				}
			}
		}
		ritualname[0] = "Explosion";
		
		ritualLocate[0][0][1][1] = BlockRegister.ritualGlassTier1;
		ritualLocate[0][1][1][0] = BlockRegister.ritualGlassTier1;
		ritualLocate[0][1][1][1] = BlockRegister.ritualGlassTier1;
		ritualLocate[0][1][1][2] = BlockRegister.ritualGlassTier1;
		ritualLocate[0][2][1][1] = BlockRegister.ritualGlassTier1;

		ritualname[1] = "CutBlock";

		ritualLocate[1][0][0][1] = BlockRegister.ritualGlassTier1;
		ritualLocate[1][1][0][0] = BlockRegister.ritualGlassTier1;
		ritualLocate[1][1][0][2] = BlockRegister.ritualGlassTier1;
		ritualLocate[1][2][0][1] = BlockRegister.ritualGlassTier1;
		ritualLocate[1][1][1][1] = BlockRegister.ritualGlassTier1;

		ritualname[2] = "CreateWater";

		ritualLocate[2][0][1][0] = BlockRegister.ritualGlassTier1;
		ritualLocate[2][0][1][2] = BlockRegister.ritualGlassTier1;
		ritualLocate[2][1][1][1] = BlockRegister.ritualGlassTier1;
		ritualLocate[2][2][1][0] = BlockRegister.ritualGlassTier1;
		ritualLocate[2][2][1][2] = BlockRegister.ritualGlassTier1;

		ritualname[3] = "CreateLava";

		ritualLocate[3][0][1][0] = BlockRegister.ritualGlassTier1;
		ritualLocate[3][0][2][1] = BlockRegister.ritualGlassTier2;
		ritualLocate[3][0][1][2] = BlockRegister.ritualGlassTier1;
		ritualLocate[3][1][2][0] = BlockRegister.ritualGlassTier2;
		ritualLocate[3][1][1][1] = BlockRegister.ritualGlassTier1;
		ritualLocate[3][1][2][2] = BlockRegister.ritualGlassTier2;
		ritualLocate[3][2][1][0] = BlockRegister.ritualGlassTier1;
		ritualLocate[3][2][2][1] = BlockRegister.ritualGlassTier2;
		ritualLocate[3][2][1][2] = BlockRegister.ritualGlassTier1;

		ritualname[4] = "CreateEI";

		ritualLocate[4][0][1][0] = BlockRegister.ritualGlassTier2;
		ritualLocate[4][0][1][2] = BlockRegister.ritualGlassTier2;
		ritualLocate[4][2][1][0] = BlockRegister.ritualGlassTier2;
		ritualLocate[4][2][1][2] = BlockRegister.ritualGlassTier2;
		ritualLocate[4][1][1][1] = BlockRegister.ritualGlassTier2;
		ritualLocate[4][0][1][1] = BlockRegister.weakExperienceIronBlock;
		ritualLocate[4][1][1][0] = BlockRegister.weakExperienceIronBlock;
		ritualLocate[4][1][1][2] = BlockRegister.weakExperienceIronBlock;
		ritualLocate[4][2][1][1] = BlockRegister.weakExperienceIronBlock;

		ritualname[5] = "BlockFalling";

		ritualLocate[5][0][2][0] = BlockRegister.ritualGlassTier2;
		ritualLocate[5][0][2][2] = BlockRegister.ritualGlassTier2;
		ritualLocate[5][1][2][1] = BlockRegister.ritualGlassTier2;
		ritualLocate[5][2][2][0] = BlockRegister.ritualGlassTier2;
		ritualLocate[5][2][2][2] = BlockRegister.ritualGlassTier2;
		ritualLocate[5][1][1][1] = BlockRegister.ritualGlassTier1;
		ritualLocate[5][1][0][1] = BlockRegister.ritualGlassTier1;

		return ritualLocate[n][x][y][z];
	}
}