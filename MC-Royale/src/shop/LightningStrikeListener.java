package shop;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.LightningStrikeEvent;

public class LightningStrikeListener implements Listener{
	
	@EventHandler
	public void onLightningStrike(LightningStrikeEvent e) {
		e.setCancelled(true);
	}
}
