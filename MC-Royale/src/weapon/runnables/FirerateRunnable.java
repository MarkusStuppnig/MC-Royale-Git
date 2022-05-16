package weapon.runnables;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import main.Session;

public class FirerateRunnable extends BukkitRunnable {

	private final Player player;
	
	public FirerateRunnable(Player player) {
		this.player = player;
	}
	
	@Override
	public void run() {
		Session.firerate_players.remove(player.getUniqueId().toString());
	}
}