package to.epac.factorycraft.CombinedHit.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import to.epac.factorycraft.CombinedHit.Main;
import to.epac.factorycraft.CombinedHit.Events.MetadataHandler;
import to.epac.factorycraft.CombinedHit.Utils.FileManager;
import to.epac.factorycraft.CombinedHit.Utils.MathUtils;
import to.epac.factorycraft.CombinedHit.Utils.Variables;

public class Leveling implements CommandExecutor {
	private static Plugin plugin = Main.instance;
	FileConfiguration file = plugin.getConfig();
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (args.length == 0) {
			HelpPage(sender);
		}
		
		// Do condition test in EVERY using arguments
		else if (args[0].equalsIgnoreCase("level")) {
			if (args[1].length() > 0) {
				if (MathUtils.isNumber(args[2])) {
					Player p = Bukkit.getServer().getPlayer(args[1]);
						String uuid = p.getUniqueId().toString();
						p.sendMessage(uuid + "'s leveling was set to " + args[2]);
						plugin.getConfig().set("Players." + uuid + ".Level", Integer.valueOf(args[2]));
						plugin.saveConfig();
						
						MetadataHandler.eraseNoDelay(p, Variables.getMaxLevel());
				}
			}
				
		} else if (args[0].equalsIgnoreCase("reset")) {
			if (args[1].length() > 0) {
				Player p = Bukkit.getServer().getPlayer(args[1]);
				
				MetadataHandler.eraseNoDelay(p, Variables.getMaxLevel());
			}
			
		} else if (args[0].equalsIgnoreCase("reload")) {
			if (FileManager.configFileEmpty())
				sender.sendMessage(ChatColor.GREEN + "Configuration file empty, coping the default one.");
			
			plugin.reloadConfig();
			sender.sendMessage(ChatColor.GREEN + "Configuration reloaded.");
			
		} /*else if (args[0].equalsIgnoreCase("getcooldowntime")) {
			if (MathUtils.isNumber(args[1])) {
				int level = Integer.valueOf(args[1]);
				sender.sendMessage("" + CooldownPerLeveling.getCooldownTime(level));
			}
			
		}*/ else {
			HelpPage(sender);
		}
		
		return false;
	}

	public void HelpPage(CommandSender sender) {
		sender.sendMessage(ChatColor.DARK_PURPLE + "--------------------" + ChatColor.AQUA + "CombinedHit" + ChatColor.DARK_PURPLE + "--------------------");
		sender.sendMessage(ChatColor.LIGHT_PURPLE + "Main command: /CombinedHit, /chit, /ch");
		sender.sendMessage(ChatColor.GOLD + "<>: Required []: Optional");
		sender.sendMessage(ChatColor.AQUA + "/ch level <player> <level>: Set player's level");
		sender.sendMessage(ChatColor.AQUA + "/ch reset <player>: Reset player's data");
		sender.sendMessage(ChatColor.AQUA + "/ch reload: Reload configuration");
		//sender.sendMessage(ChatColor.LIGHT_PURPLE + "");
		//sender.sendMessage(ChatColor.LIGHT_PURPLE + "");
		//sender.sendMessage(ChatColor.LIGHT_PURPLE + "");
		//sender.sendMessage(ChatColor.LIGHT_PURPLE + "");
		sender.sendMessage(ChatColor.DARK_PURPLE + "--------------------" + ChatColor.AQUA + "CombinedHit" + ChatColor.DARK_PURPLE + "--------------------");
	
	}
}
/*
 * 
 * 
 * 
§Ş¯à´¡¥ó
==========================
[ª«²z³¡¤À]
§Ş¯à¥HªZ¾¹ºØÃş¤À¶}
¥H¹CÀ¸¤J­±ïexp bar ¾¤ show cool down
·|¥H·Æ¹«¥ªÁä©ÎªÌ¥kÁäÄ²µo
§Ş¯àlvl¶V«l¶V½ÆÂø
E.g ¼C lvl 1 ¥kÁä+¥ªÁä
            lvl5 ¥k¥ª¥k¥ª
°O¦í¨C­Ó§Ş¯à«Y¤À¶}ï¡A ©j«Y¼C§Ş¤w¸glevel5ïª±®a¥i¥H°µªğlevel1ï§Ş¯à
¨C¦¸ª±®a¥ªÁä©ÎªÌ¥kÁä³£­nöaction bar«×show¥X‘Á
Â²³æ²É¤l®ÄªG¤w¸g¥i¥H
³Ì¦n¥i¥H¦³database
¬°÷¤è«K§A
¨C±ş¤@°¦©Ç³£¥i¥H¥[¬Û¦P¸gÅç
¦ı«Y§A¥ÎõºØªZ¾¹¥u·|¥[õºØªZ¾¹ï¸gÅç
¤§«áõºØªZ¾¹°÷¸gÅç­È´N¤Élevel 
 * 
 * 
 * 
 * 
 */
