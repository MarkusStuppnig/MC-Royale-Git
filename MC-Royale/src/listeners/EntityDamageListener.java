package listeners;

import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityDamageListener implements Listener{
	
	@EventHandler
	public void onEntityDamage(EntityDamageEvent e) {
		
		if(!(e.getEntity() instanceof Villager && e.getEntity().getCustomName().equals("Shop"))) return;
		
		e.setCancelled(true);
	}
}
