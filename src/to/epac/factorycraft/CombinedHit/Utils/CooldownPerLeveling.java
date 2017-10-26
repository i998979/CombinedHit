package to.epac.factorycraft.CombinedHit.Utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

import to.epac.factorycraft.CombinedHit.Main;

public class CooldownPerLeveling {
	public static Plugin plugin = Main.instance;
	static FileConfiguration file = plugin.getConfig();
	
	/*public static double getCooldownTime (int level) {
		file = plugin.getConfig();
		double time = file.getDouble("Cooldown.Lv" + level);
		
		return time;
	}*/
}
