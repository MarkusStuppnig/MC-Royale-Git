package health.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import item.Colors;
import main.Session;

public class HealCommand implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(!(sender instanceof Player)) {
			sender.sendMessage(Session.noPlayer);
			return true;
		}
		
		Player p = (Player) sender;
		
		ItemStack heal = new ItemStack(Material.COOKED_CHICKEN, 1);
		
		ItemMeta im = heal.getItemMeta();
		im.setDisplayName(Colors.green + Colors.bold + "Heal");
		heal.setItemMeta(im);
		
		p.getInventory().addItem(heal);
		
		return true;
	}
}