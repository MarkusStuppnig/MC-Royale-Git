package listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;

import main.Session;
import weapon.Weapon;
import weapon.stats.WeaponStats;

public class ItemPickupListener implements Listener {

	@EventHandler
    public void OnPlayerPickupItemEvent(EntityPickupItemEvent e) {
		
		if(!(e.getEntity() instanceof Player)) return;
		
		Player p = (Player) e.getEntity();
		
		for(Weapon w : Session.weapons) {
			if(w.equals(e.getItem().getItemStack())) {
				if(p.getInventory().firstEmpty() == p.getInventory().getHeldItemSlot()) {
					p.setLevel(w.data.getInteger(WeaponStats.ammo_left_in_magazine));
					return;
				}
			}
		}
	}
}