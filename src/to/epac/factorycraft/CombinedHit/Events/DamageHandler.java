package to.epac.factorycraft.CombinedHit.Events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;

import to.epac.factorycraft.CombinedHit.Main;
import to.epac.factorycraft.CombinedHit.Utils.ActionBar;
import to.epac.factorycraft.CombinedHit.Utils.CastPhase;
import to.epac.factorycraft.CombinedHit.Utils.FileManager;

public class DamageHandler implements Listener {
	public static Plugin plugin = Main.instance;
	FileConfiguration file = plugin.getConfig();
	
	/*
	 * 
	 * ########## DAMAGE ##########
	 * Please beware that EVERY event in this class only apply on DAMAGE entities/players
	 * Clicking entities/players are not detected in this class
	 * ########## DAMAGE ##########
	 * 
	 */
	
	@EventHandler
	public void lv1(EntityDamageByEntityEvent event) {
		Player p = (Player) event.getDamager();
		
		if (!FileManager.isConfigFileValid()) return;
		if (p.getInventory().getItemInMainHand().getType() == Material.AIR) return;
		
		if (FileManager.getLevel(p) == 1 || !FileManager.isPlayerInDataBase(p)) {
				
			if (p.hasMetadata("CombinedHit.Lv1.1")) {
				ActionBar.sendActionBar(p, CastPhase.getPhaseText(1, 0));
				p.playSound(p.getLocation(), Sound.BLOCK_NOTE_PLING, 1, 3);
				p.removeMetadata("CombinedHit.Lv1", plugin);
				p.setMetadata("CombinedHit.Lv1.1", new FixedMetadataValue(plugin, p.getUniqueId()));
				Bukkit.getServer().getScheduler().cancelTask(MetadataHandler.schedulerId);
				MetadataHandler.erase(p, 1);
			}
		}
	}
	
	@EventHandler
	public void lv2(EntityDamageByEntityEvent event) {
		if (event.getDamager() instanceof Player) {
			Player p = (Player) event.getDamager();

			if (!FileManager.isConfigFileValid()) return;
			if (p.getInventory().getItemInMainHand().getType() == Material.AIR) return;
			
			if (FileManager.getLevel(p) == 2) {
					
					if (p.hasMetadata("CombinedHit.Lv2.1") && !p.hasMetadata("CombinedHit.Lv2.2")) {
						ActionBar.sendActionBar(p, CastPhase.getPhaseText(2, 2));
						p.playSound(p.getLocation(), Sound.BLOCK_NOTE_PLING, 1, 2);
						p.setMetadata("CombinedHit.Lv2.2", new FixedMetadataValue(plugin, p.getUniqueId()));
						
						Bukkit.getServer().getScheduler().cancelTask(MetadataHandler.schedulerId);
						MetadataHandler.erase(p, 2);
					}
					else if (p.hasMetadata("CombinedHit.Lv2.2") && !p.hasMetadata("CombinedHit.Lv2")) {
						ActionBar.sendActionBar(p, CastPhase.getPhaseText(2, 0));
						p.playSound(p.getLocation(), Sound.BLOCK_NOTE_PLING, 1, 2);
						p.setMetadata("CombinedHit.Lv2", new FixedMetadataValue(plugin, p.getUniqueId()));
						
						Bukkit.getServer().getScheduler().cancelTask(MetadataHandler.schedulerId);
						MetadataHandler.erase(p, 2);
					}
			}
		}
	}
	
	@EventHandler
	public void lv3(EntityDamageByEntityEvent event) {
		if (event.getDamager() instanceof Player) {
			Player p = (Player)event.getDamager();
		
		if (!FileManager.isConfigFileValid()) return;
		if (p.getInventory().getItemInMainHand().getType() == Material.AIR) return;
		
		if (FileManager.getLevel(p) == 3) {
			
			//if (!(p.getInventory().getHand().equals(EquipmentSlot.HAND))) return;
			//if (p.getInventory().get)
			
				if (p.hasMetadata("CombinedHit.Lv3.1") && !p.hasMetadata("CombinedHit.Lv3.2")) {
					ActionBar.sendActionBar(p, CastPhase.getPhaseText(3, 2));
					p.playSound(p.getLocation(), Sound.BLOCK_NOTE_PLING, 1, 3);
					p.setMetadata("CombinedHit.Lv3.2", new FixedMetadataValue(plugin, p.getUniqueId()));
					
					Bukkit.getServer().getScheduler().cancelTask(MetadataHandler.schedulerId);
					MetadataHandler.erase(p, 3);
				}
				else if (p.hasMetadata("CombinedHit.Lv3.3") && !p.hasMetadata("CombinedHit.Lv3")) {
					ActionBar.sendActionBar(p, CastPhase.getPhaseText(3, 0));
					p.playSound(p.getLocation(), Sound.BLOCK_NOTE_PLING, 1, 3);
					p.setMetadata("CombinedHit.Lv3", new FixedMetadataValue(plugin, p.getUniqueId()));
					
					Bukkit.getServer().getScheduler().cancelTask(MetadataHandler.schedulerId);
					MetadataHandler.erase(p, 3);
				}
				
				else if (p.hasMetadata("CombinedHit.Lv3.2") && !p.hasMetadata("CombinedHit.Lv3.3")) {
					p.removeMetadata("CombinedHit.Lv3.1", plugin);
					p.removeMetadata("CombinedHit.Lv3.2", plugin);
					p.removeMetadata("CombinedHit.Lv3.3", plugin);
					p.removeMetadata("CombinedHit.Lv3", plugin);
					
					p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_LAND, 1, 3);
					
					ActionBar.sendActionBar(p, ChatColor.GRAY + "[R] [L] [R] [L]");
					
					return;
				}
			}
		}
	}
}
