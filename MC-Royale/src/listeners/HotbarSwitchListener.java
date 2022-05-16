package listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;

import main.Session;
import weapon.Weapon;
import weapon.stats.WeaponStats;

public class HotbarSwitchListener implements Listener{
	
	@EventHandler
	public void onPlayerItemHeldEvent(PlayerItemHeldEvent e) {
		Player p = e.getPlayer();
		
		for(int i = 0; i < Session.reload_players.size(); i++) {
			if(Session.reload_players.containsKey(p.getUniqueId().toString())) {
				for(Weapon w : Session.weapons) {
					if(w.equals(p.getInventory().getItemInMainHand())) {
						w.reloadAbort(p);
						break;
					}
				}
				break;
			}
		}
		
		for(Weapon w : Session.weapons) {
			if(w.equals(p.getInventory().getItem(e.getNewSlot()))) {
				p.setLevel(w.data.getInteger(WeaponStats.ammo_left_in_magazine));
				return;
			}
		}
		p.setLevel(0);
	}
}