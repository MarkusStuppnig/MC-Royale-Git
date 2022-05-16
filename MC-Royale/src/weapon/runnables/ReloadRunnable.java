package weapon.runnables;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import weapon.Weapon;

public class ReloadRunnable extends BukkitRunnable {

	private final Player player;
	private final Weapon weapon;
	private final int amount;
	private final String reload_id;
	
	public ReloadRunnable(Player player, Weapon weapon, int amount, String reload_id) {
		this.player = player;
		this.weapon = weapon;
		this.amount = amount;
		this.reload_id = reload_id;
	}
	
	@Override
	public void run() {
        this.weapon.reloadEnd(this.player, this.amount, this.reload_id);
	}
}