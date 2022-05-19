package health;

import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityDamageListener implements Listener{
	
	@EventHandler
	public void onEntityDamage(EntityDamageEvent e) {
		
		if(e.getEntity() instanceof Villager) e.setCancelled(true);
		if(!(e.getEntity() instanceof Player)) return;
		
		Player p = (Player) e.getEntity();
		
		if(p.getHealth() - e.getDamage() <= 1) {

			int toRemove = (int) e.getDamage();
			toRemove -= p.getHealth();	
			p.setHealth(1);

			e.setDamage(0);
			
			p.setFoodLevel(p.getFoodLevel() - toRemove);
			
			if(p.getFoodLevel() - toRemove <= 0) {
				
				p.setHealth(0);
			}
		} 
	}
}

