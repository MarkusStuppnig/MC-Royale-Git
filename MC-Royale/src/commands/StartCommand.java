package commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import item.Colors;
import main.Session;

public class StartCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String string, String[] args) {
		
		if(!(sender instanceof Player)) {
			sender.sendMessage(Session.noPlayer);
			return true;
		}
		
		if(args.length == 0) return false;
		
		Player p = (Player) sender;
		Location loc = Session.getSession().locations.getLocation("start_location");
		
		for(Player t : Bukkit.getOnlinePlayers()) {
			t.teleport(loc);
			t.sendMessage(Colors.blue + "Player " + Colors.dark_aqua + p.getName() + Colors.black + " started a game.");
			t.sendMessage(Colors.blue + "You were teleported to start location.");
			t.sendMessage(Colors.blue + "You have 1 minute spawn protection...");
		}
		
		return true;
	}
}