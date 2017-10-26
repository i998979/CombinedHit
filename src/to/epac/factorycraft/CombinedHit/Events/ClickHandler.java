package to.epac.factorycraft.CombinedHit.Events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;

import to.epac.factorycraft.CombinedHit.Main;
import to.epac.factorycraft.CombinedHit.Utils.ActionBar;
import to.epac.factorycraft.CombinedHit.Utils.CastPhase;
import to.epac.factorycraft.CombinedHit.Utils.FileManager;

public class ClickHandler implements Listener {
	private Plugin plugin = Main.instance;
	
	/*
	 * 
	 * ########## CLICK ##########
	 * Please beware that EVERY event in this class only apply on CLICK entities/players
	 * Damaging entities/players are not detected in this class
	 * ########## CLICK ##########
	 * 
	 */
	
	@EventHandler
	public void lv1(PlayerInteractEvent event) {
		Player p = (Player) event.getPlayer();
		
		/*if (!p.getItemInHand().getItemMeta().getLore().contains("")) {
			return;
		}*/
		
		if (!FileManager.isConfigFileValid()) return;
		if (p.getInventory().getItemInMainHand().getType() == Material.AIR) return;
		
		if (FileManager.getLevel(p) == 1 || !FileManager.isPlayerInDataBase(p)) {
			
			// Preventing players from no-cooldown casting and multiple casting
			if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK || 
					event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
				
				if (!(event.getHand().equals(EquipmentSlot.HAND))) return;
				
				if (p.hasMetadata("CombinedHit.Lv1")) {
					ActionBar.sendActionBar(p, ChatColor.DARK_RED + "" + ChatColor.BOLD + "Lv1 Cooling down !");
					return;
				}
			}
			
			if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
				
				if (p.hasMetadata("CombinedHit.Lv1.1")) {
					p.removeMetadata("CombinedHit.Lv1.1", plugin);
					p.removeMetadata("CombinedHit.Lv1", plugin);
					
					p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_LAND, 1, 2);
					
					ActionBar.sendActionBar(p, ChatColor.GRAY + "[R] [L]");
					
					return;
				}
					
				if (!p.hasMetadata("CombinedHit:Lv1") && !p.hasMetadata("CombinedHit:Lv1.1")) {
					ActionBar.sendActionBar(p, CastPhase.getPhaseText(1, 1));
					p.playSound(p.getLocation(), Sound.BLOCK_NOTE_PLING, 1, 0);
					p.removeMetadata("CombinedHit.Lv1", plugin);
					p.setMetadata("CombinedHit.Lv1.1", new FixedMetadataValue(plugin, p.getUniqueId()));
					Bukkit.getServer().getScheduler().cancelTask(MetadataHandler.schedulerId);
					MetadataHandler.erase(p, 1);
				}
			}
			if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
				if (p.hasMetadata("CombinedHit.Lv1.1")) {
					ActionBar.sendActionBar(p, CastPhase.getPhaseText(1, 2));
					p.playSound(p.getLocation(), Sound.BLOCK_NOTE_PLING, 1, 2);
					p.setMetadata("CombinedHit.Lv1", new FixedMetadataValue(plugin, p.getUniqueId()));
				}
			}
		}
	}
	
	
	
	
	
	@EventHandler
	public void lv2(PlayerInteractEvent event) {
		Player p = (Player) event.getPlayer();
		
		if (!FileManager.isConfigFileValid()) return;
		if (p.getInventory().getItemInMainHand().getType() == Material.AIR) return;
		
		if (FileManager.getLevel(p) == 2) {
			
			// Prohibiting players from no-cooldown casting and multiple right click
			if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK || 
					event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
				
		        if (!(event.getHand().equals(EquipmentSlot.HAND))) return;
				
				if (p.hasMetadata("CombinedHit.Lv2")) {
					ActionBar.sendActionBar(p, ChatColor.DARK_RED + "" + ChatColor.BOLD + "Lv2 Cooling down !");
					return;
				}
			}
			
			if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
				
				if (p.hasMetadata("CombinedHit.Lv2.1")) {
					p.removeMetadata("CombinedHit.Lv2.1", plugin);
					p.removeMetadata("CombinedHit.Lv2.2", plugin);
					p.removeMetadata("CombinedHit.Lv2", plugin);
					
					p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_LAND, 1, 2);
					
					ActionBar.sendActionBar(p, ChatColor.GRAY + "[R] [L] [L]");
					
					return;
				}
				
				if (!p.hasMetadata("CombinedHit:Lv2") && !p.hasMetadata("CombinedHit:Lv2.1")) {
					ActionBar.sendActionBar(p, CastPhase.getPhaseText(2, 1));
					p.playSound(p.getLocation(), Sound.BLOCK_NOTE_PLING, 1, 0);
					p.setMetadata("CombinedHit.Lv2.1", new FixedMetadataValue(plugin, p.getUniqueId()));
					
					Bukkit.getServer().getScheduler().cancelTask(MetadataHandler.schedulerId);
					MetadataHandler.erase(p, 2);
				}
			}
			if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
				
				if (p.hasMetadata("CombinedHit.Lv2.1") && !p.hasMetadata("CombinedHit.Lv2.2")) {
					ActionBar.sendActionBar(p, CastPhase.getPhaseText(2, 2));
					p.playSound(p.getLocation(), Sound.BLOCK_NOTE_PLING, 1, 2);
					p.setMetadata("CombinedHit.Lv2.2", new FixedMetadataValue(plugin, p.getUniqueId()));
					
					Bukkit.getServer().getScheduler().cancelTask(MetadataHandler.schedulerId);
					MetadataHandler.erase(p, 2);
				}
				else if (p.hasMetadata("CombinedHit.Lv2.2") && !p.hasMetadata("CombinedHit.Lv2")) {
					ActionBar.sendActionBar(p, CastPhase.getPhaseText(2, 3));
					p.playSound(p.getLocation(), Sound.BLOCK_NOTE_PLING, 1, 2);
					p.setMetadata("CombinedHit.Lv2", new FixedMetadataValue(plugin, p.getUniqueId()));
					
					Bukkit.getServer().getScheduler().cancelTask(MetadataHandler.schedulerId);
					MetadataHandler.erase(p, 2);
				}
			}
		}
	}
	
	
	
	
	@EventHandler
	public void lv3(PlayerInteractEvent event) {
		Player p = (Player) event.getPlayer();
		
		if (!FileManager.isConfigFileValid()) return;
		if (p.getInventory().getItemInMainHand().getType() == Material.AIR) return;
		
		if (FileManager.getLevel(p) == 3) {
			
			// Prohibiting players from no-cooldown casting and multiple right click
			if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK || 
					event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
				
		        if (!(event.getHand().equals(EquipmentSlot.HAND))) return;
				
				if (p.hasMetadata("CombinedHit.Lv3")) {
					ActionBar.sendActionBar(p, ChatColor.DARK_RED + "" + ChatColor.BOLD + "Lv3 Cooling down !");
					return;
				}
			}
			
			if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
				if (p.hasMetadata("CombinedHit.Lv3.1") && !p.hasMetadata("CombinedHit.Lv3.2")) {
					p.removeMetadata("CombinedHit.Lv3.1", plugin);
					p.removeMetadata("CombinedHit.Lv3.2", plugin);
					p.removeMetadata("CombinedHit.Lv3.3", plugin);
					p.removeMetadata("CombinedHit.Lv3", plugin);
					
					p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_LAND, 1, 3);
					
					ActionBar.sendActionBar(p, ChatColor.GRAY + "[R] [L] [R] [L]");
					
					return;
				}
				if (p.hasMetadata("CombinedHit.Lv3.3") && !p.hasMetadata("CombinedHit.Lv3")) {
					p.removeMetadata("CombinedHit.Lv3.1", plugin);
					p.removeMetadata("CombinedHit.Lv3.2", plugin);
					p.removeMetadata("CombinedHit.Lv3.3", plugin);
					p.removeMetadata("CombinedHit.Lv3", plugin);
					
					p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_LAND, 1, 3);
					
					ActionBar.sendActionBar(p, ChatColor.GRAY + "[R] [L] [R] [L]");
					
					return;
				}
				
				
				if (!p.hasMetadata("CombinedHit:Lv3") && !p.hasMetadata("CombinedHit:Lv3.1") && !p.hasMetadata("CombinedHit.Lv3.2")) {
					ActionBar.sendActionBar(p, CastPhase.getPhaseText(3, 1));
					p.playSound(p.getLocation(), Sound.BLOCK_NOTE_PLING, 1, 0);
					p.setMetadata("CombinedHit.Lv3.1", new FixedMetadataValue(plugin, p.getUniqueId()));
					
					Bukkit.getServer().getScheduler().cancelTask(MetadataHandler.schedulerId);
					MetadataHandler.erase(p, 3);
				}
				else if (!p.hasMetadata("CombinedHit:Lv3") && p.hasMetadata("CombinedHit.Lv3.2") && !p.hasMetadata("CombinedHit:Lv3.3")) {
					ActionBar.sendActionBar(p, CastPhase.getPhaseText(3, 3));
					p.playSound(p.getLocation(), Sound.BLOCK_NOTE_PLING, 1, 0);
					p.setMetadata("CombinedHit.Lv3.3", new FixedMetadataValue(plugin, p.getUniqueId()));
					
					Bukkit.getServer().getScheduler().cancelTask(MetadataHandler.schedulerId);
					MetadataHandler.erase(p, 3);
				}
			}
			if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
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
