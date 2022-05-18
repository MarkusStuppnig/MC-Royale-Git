package commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HealCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
		if(!(sender instanceof Player)) return true;
		if(!(args.length == 0)) return false;
		
		Player p = (Player) sender;
		
		try {
			p.setHealth(p.getHealth() + Double.parseDouble(args[0]));
			return true;
		}catch (NumberFormatException e) {
			return false;
		}
	}
	
	
	
}
