package listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
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
		
		String[] arrowData = arrow.getCustomName().split(";");
		
		World world = Bukkit.getServer().getWorld(Session.getSession().defaultWorld);
		double x = Double.parseDouble(arrowData[1]);
		double y = Double.parseDouble(arrowData[2]);
		double z = Double.parseDouble(arrowData[3]);
		Location location = new Location(world, x, y, z);
		
		double distance = e.getEntity().getLocation().distance(location);
		
		if(!Session.getSession().gameStats.config.contains("longestShot.distance")) {
			Session.getSession().gameStats.config.set("longestShot.uuid", arrowData[4]);
			Session.getSession().gameStats.config.set("longestShot.distance", distance);
		}
		else if(distance > (double) Session.getSession().gameStats.config.get("longestShot.distance")) {
			Session.getSession().gameStats.config.set("longestShot.uuid", arrowData[4]);
			Session.getSession().gameStats.config.set("longestShot.distance", distance);
		}
	}
}