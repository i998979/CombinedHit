package to.epac.factorycraft.CombinedHit;

import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import to.epac.factorycraft.CombinedHit.Commands.ItemStackEditor;
import to.epac.factorycraft.CombinedHit.Commands.Leveling;
import to.epac.factorycraft.CombinedHit.Events.ClickHandler;
import to.epac.factorycraft.CombinedHit.Events.DamageHandler;
import to.epac.factorycraft.CombinedHit.Events.MetadataHandler;
import to.epac.factorycraft.CombinedHit.Utils.FileManager;

public class Main extends JavaPlugin{
	public static Main instance;
	
	public void onEnable() {
		instance = this;
		
		PluginManager pm = getServer().getPluginManager();
		
		pm.registerEvents(new MetadataHandler(), this);
		pm.registerEvents(new ClickHandler(), this);
		pm.registerEvents(new DamageHandler(), this);
		
		getCommand("CombinedHit").setExecutor(new Leveling());
		getCommand("itemstackeditor").setExecutor(new ItemStackEditor());
		
		if (!FileManager.configFileExist()) {
			getServer().getConsoleSender().sendMessage(ChatColor.RED + "Configuration not found. Generating the default one.");
		}
	}
	
	public void onDisable() {
		instance = null;
	}
}
