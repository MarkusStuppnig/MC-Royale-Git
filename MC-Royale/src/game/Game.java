package game;

import java.util.UUID;

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
	
	public static void stopGame(String name) {
		
		Bukkit.broadcastMessage(Colors.aqua + "Player " + Colors.light_purple + name + Colors.aqua + " won the game.");
		
		if(Session.getSession().gameStats.config != null) {
			String uuid = (String) Session.getSession().gameStats.config.get("longestShot.uuid");
			
			if(uuid != null) {
				double distance = Math.round((double) Session.getSession().gameStats.config.get("longestShot.distance") * 100) / 100d;
				Bukkit.broadcastMessage(Colors.aqua + "Longest shot: " + Colors.light_purple + Bukkit.getPlayer(UUID.fromString(uuid)).getName() + Colors.aqua + " - Distance " + Colors.light_purple + distance + "m");
				Session.getSession().gameStats.deleteFile(true);
			}
		}
		
		Bukkit.broadcastMessage(Colors.gold + "Thank you for playing.");
		//Bukkit.broadcastMessage(System.getProperty("os.name"));
	}
}