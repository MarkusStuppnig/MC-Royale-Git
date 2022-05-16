package listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import main.Session;
import weapon.Weapon;
import weapon.stats.WeaponStats;

public class ItemDropListener implements Listener {
	
	@EventHandler
    public void onPlayerDropItemEvent(PlayerDropItemEvent e) {
		
		Player p = e.getPlayer();
		
		if(Session.reload_players.containsKey(p.getUniqueId().toString())) {
			
			for(Weapon w : Session.weapons) {
				if(w.equals(e.getItemDrop().getItemStack())) {
					w.reloadAbort(p);
					break;
				}
			}
		}
		
		for(Weapon w : Session.weapons) {
			if(w.equals(p.getInventory().getItemInMainHand())) {
				p.setLevel(w.data.getInteger(WeaponStats.ammo_left_in_magazine));
				return;
			}
		}
		p.setLevel(0);
	}
}