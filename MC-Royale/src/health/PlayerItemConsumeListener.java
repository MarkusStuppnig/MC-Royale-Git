package health;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

import item.Colors;

public class PlayerItemConsumeListener implements Listener{

	@EventHandler
	public void onPlayerItemConsume(PlayerItemConsumeEvent e) {
		
		Player p = (Player) e.getPlayer();
		
		if(e.getItem().getItemMeta().getDisplayName().equals(Colors.green + Colors.bold + "Heal")) {
			p.setFoodLevel(p.getFoodLevel() + 5);
			p.sendMessage("healed");
			p.getInventory().removeItem(new ItemStack(Material.COOKED_CHICKEN, 1));
		}
		e.setCancelled(true);
	}
}
