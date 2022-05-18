package commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import main.Session;
import shop.stats.ShopStats;

public class MoneyCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String string, String[] args) {
		
		if(!(sender instanceof Player)) {
			sender.sendMessage(Session.noPlayer);
			return true;
		}
		
		Player p = (Player) sender;
		
		if(args.length != 1) return false;
		
		ItemStack money = ShopStats.money;
		money.setAmount(Integer.parseInt(args[0]));
		
		p.getInventory().addItem(ShopStats.money);
		
		return true;
	}
}