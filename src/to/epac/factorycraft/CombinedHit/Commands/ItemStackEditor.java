package to.epac.factorycraft.CombinedHit.Commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemStackEditor implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if (args[0].equalsIgnoreCase("setitemlore")) {
			if (sender instanceof Player) {
				Player p = (Player) sender;
				
				if (p.getInventory().getItemInMainHand() == null) return false;
				
				ItemStack item = p.getInventory().getItemInMainHand();
				ItemMeta meta = item.getItemMeta();
				List<String> loreList = new ArrayList<String>();
				
				for (int i = 1; i< args.length; i++) {
					loreList.add(ChatColor.translateAlternateColorCodes('&', args[i]));
				}
				
				meta.setLore(loreList);
				item.setItemMeta(meta);
			}
			
		} else if (args[0].equalsIgnoreCase("removeitemlore")) {
			if (sender instanceof Player) {
				Player p = (Player) sender;
				
				ItemStack item = p.getInventory().getItemInMainHand();
				ItemMeta meta = item.getItemMeta();
				meta.setLore(null);
		        item.setItemMeta(meta);
		        p.sendMessage("Removed.");
			}
		}
		return false;
		
	}
}
