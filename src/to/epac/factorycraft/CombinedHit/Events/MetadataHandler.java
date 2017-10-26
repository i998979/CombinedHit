package to.epac.factorycraft.CombinedHit.Events;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import to.epac.factorycraft.CombinedHit.Main;

public class MetadataHandler implements Listener{
	public static int schedulerId;
	
	public static Plugin plugin = Main.instance;
	FileConfiguration file = plugin.getConfig();
	
	public static void erase(Player p, int level) {
		schedulerId = Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			@Override
			public void run() {
				for (int i = level; i > 0; i--) {
					if (p.hasMetadata("CombinedHit.Lv" + i)) {
						p.removeMetadata("CombinedHit.Lv" + i, plugin);
					}
					for (int sublvl = level; sublvl > 0; sublvl--) {
						p.removeMetadata("CombinedHit.Lv" + i + "." + sublvl, plugin);
					}
				}
			}
		}, 60L);
	}
	
	public static void eraseNoDelay(Player p, int level) {
		for (int i = level; i > 0; i--) {
			if (p.hasMetadata("CombinedHit.Lv" + i)) {
				p.removeMetadata("CombinedHit.Lv" + i, plugin);
			}
			for (int sublvl = level; sublvl > 0; sublvl--) {
				p.removeMetadata("CombinedHit.Lv" + i + "." + sublvl, plugin);
			}
		}
	}
}
