package health;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;

public class EntityRegainHealthListener implements Listener {
	
	@EventHandler 
	public void onEntityRegainHealt(EntityRegainHealthEvent e) {
		
		if(!(e.getEntity() instanceof Player)) return;
		
		e.setCancelled(true);
	}
}
