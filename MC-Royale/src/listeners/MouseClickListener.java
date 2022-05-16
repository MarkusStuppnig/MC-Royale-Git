package listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import main.Session;
import weapon.Weapon;

public class MouseClickListener implements Listener {
	
	 @EventHandler(priority = EventPriority.HIGHEST)
	 public void onPlayerInteract(PlayerInteractEvent e) {
		 if(e.getItem() == null) return;
		 
		 Player p = e.getPlayer();
		 
		 Weapon weapon = null;
		 for(Weapon w : Session.weapons) {
			 if(w.equals(e.getItem())) {
				 weapon = w;
				 break;
			 }
		 }
		 if(weapon == null) return;
		 
		 
		 if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			 weapon.shoot(p);
			 e.setCancelled(true);
			 
		 }else if(e.getAction().equals(Action.LEFT_CLICK_AIR) || e.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
			 weapon.reload(p);
		 }
	 }
}