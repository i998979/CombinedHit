package to.epac.factorycraft.CombinedHit.Utils;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import to.epac.factorycraft.CombinedHit.Main;

public class FileManager {
	private static Plugin plugin = Main.instance;
	static FileConfiguration file = plugin.getConfig();
	
	public static boolean configFileExist() {		
		File configFile = new File(plugin.getDataFolder(), "config.yml");
		
		if (!configFile.exists()) {
			plugin.saveDefaultConfig();
			plugin.getConfig().options().copyDefaults(true);
			plugin.saveConfig();
			
			return false;
		}
		return true;
	}
	
	public static boolean configFileEmpty() {
		if (file.getString("Players") == null) {
			plugin.saveDefaultConfig();
			plugin.getConfig().options().copyDefaults(true);
			plugin.saveConfig();
			
			return true;
		}
		return false;
	}
	
	public static int getLevel(Player p) {
		// Update config to prevent modified data not applying to the plugin
		file = plugin.getConfig();
		
		// If the config file is not vaild,
		// to prevent any potential damage,
		// return 0 to stop all 
		if (!isConfigFileValid()) return 0;
		// If player is not in database,
		// which means that he is new to the server,
		// then return default level (1)
		if (!isPlayerInDataBase(p)) return 1;
		
		return file.getInt("Players." + p.getUniqueId() + ".Level");
	}
	
	public static boolean isPlayerInDataBase(Player p) {
		// Update config to prevent modified data not applying to the plugin
		file = plugin.getConfig();
		
		return file.contains("Players." + p.getUniqueId() + ".Level");
	}
	
	public static boolean isConfigFileValid() {
		// Update config to prevent modified data not applying to the plugin
		file = plugin.getConfig();
		
		if (!file.contains("Players")) return false;
		//if (!file.getConfigurationSection("Players").contains(uuid)) return false;
		//if (!file.getConfigurationSection("Players").getConfigurationSection(uuid).contains("Level")) return false;
		
		return true;
		
			
	}
	
	
}
