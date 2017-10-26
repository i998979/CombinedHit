package to.epac.factorycraft.CombinedHit.Utils;

public class CastPhase {
	public static String getPhaseText(int level, int phase) {
		// Phase 99: Casting fail, due to Multi casting
		// Lv1: [R] [L]
		// Lv2: [R] [R] [L]
		// Lv3: [R] [L] [R] [L]
		// Lv4: [R] [L] [R] [L] [L]
		// Lv5: [R] [L] [L] [R] [R] [L]
		
		if (level == 1 && phase == 1) return TranslateColors.getText("&2[R] &7[L]");
		if (level == 1 && phase == 0) return TranslateColors.getText("&4[R] [L]");
		if (level == 1 && phase == 99) return TranslateColors.getText("&2&l[R] [L]");
		
		if (level == 2 && phase == 1) return TranslateColors.getText("&2[R] &7[L] [L]");
		if (level == 2 && phase == 2) return TranslateColors.getText("&2[R] [L] &7[L]");
		if (level == 2 && phase == 0) return TranslateColors.getText("&4[R] [L] [L]");
		if (level == 2 && phase == 99) return TranslateColors.getText("&2&l[R] [L] [L]");
		
		if (level == 3 && phase == 1) return TranslateColors.getText("&2[R] &7[L] [R] [L]");
		if (level == 3 && phase == 2) return TranslateColors.getText("&2[R] [L] &7[R] [L]");
		if (level == 3 && phase == 3) return TranslateColors.getText("&2[R] [L] [R] &7[L]");
		if (level == 3 && phase == 0) return TranslateColors.getText("&4[R] [L] [R] [L]");
		if (level == 3 && phase == 99) return TranslateColors.getText("&2&l[R] [L] [R] [L]");
		
		if (level == 4 && phase == 1) return TranslateColors.getText("&2[R] &7[L] [R] [L] [L]");
		if (level == 4 && phase == 2) return TranslateColors.getText("&2[R] [L] &7[R] [L] [L]");
		if (level == 4 && phase == 3) return TranslateColors.getText("&2[R] [L] [R] &7[L] [L]");
		if (level == 4 && phase == 4) return TranslateColors.getText("&2[R] [L] [R] [L] &7[L]");
		if (level == 4 && phase == 0) return TranslateColors.getText("&4[R] [L] [R] [L] [L]");
		if (level == 4 && phase == 99) return TranslateColors.getText("&2&l[R] [L] [R] [L] [L]");
		
		if (level == 5 && phase == 1) return TranslateColors.getText("&2[R] &7[L] [L] [R] [R] [L]");
		if (level == 5 && phase == 2) return TranslateColors.getText("&2[R] [L] &7[L] [R] [R] [L]");
		if (level == 5 && phase == 3) return TranslateColors.getText("&2[R] [L] [L] &7[R] [R] [L]");
		if (level == 5 && phase == 4) return TranslateColors.getText("&2[R] [L] [L] [R] &7[R] [L]");
		if (level == 5 && phase == 5) return TranslateColors.getText("&2[R] [L] [L] [R] [R] &7[L]");
		if (level == 5 && phase == 0) return TranslateColors.getText("&4[R] [L] [L] [R] [R] [L]");
		if (level == 5 && phase == 99) return TranslateColors.getText("&2&l[R] [L] [L] [R] [R] [L]");
		
		return TranslateColors.getText("&4&lINTERNAL ERROR !");
	}
}
