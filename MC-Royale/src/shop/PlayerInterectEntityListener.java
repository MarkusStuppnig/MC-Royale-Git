package shop;

import org.bukkit.Bukkit;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;

import shop.stats.ShopStats;

public class PlayerInterectEntityListener implements Listener{
	
	@EventHandler
	public void onPlayerInteractEntity(PlayerInteractEntityEvent e) {
		
		if(!(e.getRightClicked() instanceof Villager)) return;
		if(!(e.getRightClicked().getCustomName().equals("Shop"))) return;
		
		e.setCancelled(true);
		
        Inventory iv = Bukkit.createInventory(null, 27, ShopStats.shop_name);
		
        iv.setItem(12, ShopStats.weapons);
        iv.setItem(14, ShopStats.heal);
        
        e.getPlayer().openInventory(iv);
	}
}
