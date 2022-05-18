package weapon.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import main.Session;
import weapon.stats.WeaponStats;

public class WeaponCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(!(sender instanceof Player)) {
			sender.sendMessage(Session.noPlayer);
			return true;
		}
		
		Player p = (Player) sender;
		

		if(args.length == 1 && args[0].equals("ak47")) {
			p.getInventory().addItem(WeaponStats.ak47());
			return true;
		}
		
		if(args.length == 1 && args[0].equals("pump")) {
			p.getInventory().addItem(WeaponStats.pump());
			return true;
		}
		
		if(args.length == 1 && args[0].equals("sniper")) {
			p.getInventory().addItem(WeaponStats.sniper());
			return true;
		}
		
		return false;
	}
}