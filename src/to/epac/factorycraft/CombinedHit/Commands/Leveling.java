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
技能插件
==========================
[物理部分]
技能以武器種類分開
以遊戲入面�髾xp bar 黎 show cool down
會以滑鼠左鍵或者右鍵觸發
技能lvl越勁越複雜
E.g 劍 lvl 1 右鍵+左鍵
            lvl5 右左右左
記住每個技能係分開�鵅A 姐係劍技已經level5�麊戛a可以做返level1�鴽獊�
每次玩家左鍵或者右鍵都要�鐩ction bar度show出��
簡單粒子效果已經可以
最好可以有database
為�髐隢K你
每殺一隻怪都可以加相同經驗
但係你用�齛堛Z器只會加�齛堛Z器�儭g驗
之後�齛堛Z器夠經驗值就升level 
 * 
 * 
 * 
 * 
 */
