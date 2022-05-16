package listeners;

import org.bukkit.entity.Arrow;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

public class ArrowListener implements Listener {
	
	@EventHandler
	public void onProjectileHitEvent(ProjectileHitEvent e) {
		
		if(!(e.getEntity() instanceof Arrow)) return;
		if(e.getEntity().getCustomName() == null) return;
		if(!(e.getEntity().getCustomName().startsWith("arrow")))
		
		((Arrow) e.getEntity()).eject();
		((Arrow) e.getEntity()).remove();
	}
}