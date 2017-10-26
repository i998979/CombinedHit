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
�ޯഡ��
==========================
[���z����]
�ޯ�H�Z���������}
�H�C���J����exp bar �� show cool down
�|�H�ƹ�����Ϊ̥k��Ĳ�o
�ޯ�lvl�V�l�V����
E.g �C lvl 1 �k��+����
            lvl5 �k���k��
�O��C�ӧޯ�Y���}��A �j�Y�C�ޤw�glevel5�缾�a�i�H����level1��ޯ�
�C�����a����Ϊ̥k�䳣�n��action bar��show�X��
²��ɤl�ĪG�w�g�i�H
�̦n�i�H��database
������K�A
�C���@���ǳ��i�H�[�ۦP�g��
���Y�A�Ν��تZ���u�|�[���تZ����g��
������تZ�����g��ȴN��level 
 * 
 * 
 * 
 * 
 */
