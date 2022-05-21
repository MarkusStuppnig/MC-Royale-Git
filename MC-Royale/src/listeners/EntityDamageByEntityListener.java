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
		String[] arrowData = arrow.getCustomName().split(";");
		
		if(!arrowData[0].equals("gunArrow")) return;


		for(Weapon w : Session.weapons) {
			if(w.getId().equals(arrowData[1])) {
				e.setDamage(w.damage);
				break;
			}
		}
		
		World world = Bukkit.getServer().getWorld(Session.getSession().defaultWorld);
		double x = Double.parseDouble(arrowData[2]);
		double y = Double.parseDouble(arrowData[3]);
		double z = Double.parseDouble(arrowData[4]);
		Location location = new Location(world, x, y, z);
		
		double distance = e.getEntity().getLocation().distance(location);
		
		if(!Session.getSession().gameStats.config.contains("longestShot.distance")) {
			Session.getSession().gameStats.config.set("longestShot.uuid", arrowData[1]);
			Session.getSession().gameStats.config.set("longestShot.distance", distance);
		}
		else if(distance > (double) Session.getSession().gameStats.config.get("longestShot.distance")) {
			Session.getSession().gameStats.config.set("longestShot.uuid", arrowData[1]);
			Session.getSession().gameStats.config.set("longestShot.distance", distance);
		}
	}
}