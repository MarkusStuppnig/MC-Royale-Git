package shop;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import item.Colors;
import shop.stats.ShopStats;
import weapon.stats.WeaponStats;

public class InventoryClickListener implements Listener{
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		if(!(e.getView().getTitle().equals(ShopStats.shop_name))) return;
		if(e.getCurrentItem() == null) return;

		e.setCancelled(true);
		
		ItemStack stack = e.getCurrentItem();
		Player p = (Player) e.getWhoClicked();
		Inventory inv = e.getInventory();
		
		//Weapon Shop
		if(item.Inventory.checkId(stack, ShopStats.weapons)) {
			
			inv.clear();
			
			inv.setItem(10, WeaponStats.ak47());
			inv.setItem(12, WeaponStats.pump());
			inv.setItem(14, WeaponStats.sniper());
			
			p.openInventory(inv);
		}
		
		ItemStack money = ShopStats.money;
		
		//AK47
		if(WeaponStats.ak47_display_name.equals(stack.getItemMeta().getDisplayName())) {
			money.setAmount(20);
			if(!(item.Inventory.remove(money, p))) return;
			p.getInventory().addItem(WeaponStats.ak47());
			p.sendMessage(Colors.aqua + "You bought an ak47 for " + Colors.yellow + Colors.bold + "20$!");
		}
		
		//Pump
		if(WeaponStats.pump_display_name.equals(stack.getItemMeta().getDisplayName())) {
			money.setAmount(40);
			if(!(item.Inventory.remove(money, p))) return;
			p.getInventory().addItem(WeaponStats.pump());
			p.sendMessage(Colors.aqua + "You bought a pump for " + Colors.yellow + Colors.bold + "40$!");
		}
		
		//Sniper
		if(WeaponStats.sniper_display_name.equals(stack.getItemMeta().getDisplayName())) {
			money.setAmount(60);
			if(!(item.Inventory.remove(money, p))) return;
			p.getInventory().addItem(WeaponStats.sniper());
			p.sendMessage(Colors.aqua + "You bought a sniper for " + Colors.yellow + Colors.bold + "60$!");
		}
	}
}