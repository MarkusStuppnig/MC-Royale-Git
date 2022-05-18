package commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import item.Colors;
import main.Session;

public class SetSpawnCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String string, String[] args) {
		
		if(!(sender instanceof Player)) {
			sender.sendMessage(Session.noPlayer);
			return true;
		}
		
		if(args.length != 0) return false;
		
		Player p = (Player) sender;
		Session.getSession().locations.setLocation("start_location", p.getLocation());
		
		p.sendMessage(Colors.blue + "Successfully set start location to:");
		p.sendMessage(Colors.dark_aqua + "X: " + p.getLocation().getX());
		p.sendMessage(Colors.dark_aqua + "Y: " + p.getLocation().getY());
		p.sendMessage(Colors.dark_aqua + "Z: " + p.getLocation().getZ());
		
		return true;
	}
}