package to.epac.factorycraft.CombinedHit.Utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

import to.epac.factorycraft.CombinedHit.Main;

public class Variables {
	private static Plugin plugin = Main.instance;
	private static FileConfiguration file = plugin.getConfig();
	
	public static int getMaxLevel() {
		return file.getInt("MaxLevel");
	}
	
	public static String getPrefix() {
		
		return file.getString("Prefix");
	}
}
