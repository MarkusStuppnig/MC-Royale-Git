package listeners;

import org.bukkit.entity.Arrow;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import main.Session;
import weapon.Weapon;

public class EntityDamageByEntityListener implements Listener {

	@EventHandler
	public void onProjectileHitEvent(EntityDamageByEntityEvent e) {
		
		if(!(e.getDamager() instanceof Arrow)) return;
		if(e.getDamager().getCustomName() == null) return;
		
		Arrow arrow = (Arrow) e.getDamager();
		
		String id = arrow.getCustomName().substring(arrow.getCustomName().indexOf(":") + 1);
		
		for(Weapon w : Session.weapons) {
			if(w.getId().equals(id)) {
				e.setDamage(w.damage);
				break;
			}
		}
	}
}