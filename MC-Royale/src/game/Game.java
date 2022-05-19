package game;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import item.Colors;
import main.Session;

public class Game {

	public static void startGame(String name) {
		
		Location loc = Session.getSession().locations.getLocation("start_location");
		
		for(Player t : Bukkit.getOnlinePlayers()) {
			t.teleport(loc);
		}
		
		if(name != null) {
			Bukkit.broadcastMessage(Colors.aqua + "Player " + Colors.dark_aqua + name + Colors.aqua + " started a game.");
		}else {
			Bukkit.broadcastMessage(Colors.aqua + "A game started.");
		}
		
		Bukkit.broadcastMessage(Colors.aqua + "You were teleported to start location.");
		Bukkit.broadcastMessage(Colors.aqua + "You have 1 minute spawn protection...");
	}
	
	public static void stopGame(Player p) {
		
		Bukkit.broadcastMessage(Colors.aqua + "Player " + p.getName() + " won the game.");
		
		String uuid = (String) Session.getSession().gameStats.config.get("longestShot.uuid");
		int distance = (int) Session.getSession().gameStats.config.get("longestShot.distance");
		
		Bukkit.broadcastMessage(Colors.aqua + "Logest shot: " + Colors.dark_aqua + Bukkit.getPlayer(uuid) + Colors.aqua + " - Distance " + Colors.dark_aqua + distance);
	}
}