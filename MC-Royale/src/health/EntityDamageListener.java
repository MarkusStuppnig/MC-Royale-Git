package health;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityDamageListener implements Listener{
	
	@EventHandler
	public void onEntityDamage(EntityDamageEvent e) {
		
		if(!(e.getEntity() instanceof Player)) return;
		
		Player p = (Player) e.getEntity();
		
		p.sendMessage("" + p.getHealth());
		
		if(p.getHealth() - e.getDamage() <= 1) {

			int toRemove = (int) e.getDamage();
			toRemove -= p.getHealth();	
			p.setHealth(1);

			e.setCancelled(true);
			
			p.setFoodLevel(p.getFoodLevel() - toRemove);
			
			if(p.getFoodLevel() - toRemove <= 0) {
				
				p.setHealth(0);
			}
		} 
	}
}

