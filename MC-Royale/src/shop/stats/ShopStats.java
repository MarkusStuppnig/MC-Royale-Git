package shop.stats;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import item.Colors;
import item.Inventory;

public class ShopStats {

	public static final String shop_name = "Shop";
	
	public static final String weapons_id = "item:weapons";
	public static final String heal_id = "item:heal";
	public static final String money_id = "item:money";
	
	public static final ItemStack weapons = Inventory.createItemStack(Material.DIAMOND, Colors.aqua + Colors.bold + "Weapons", ShopStats.weapons_id);
	public static final ItemStack heal = Inventory.createItemStack(Material.APPLE, Colors.green + Colors.bold + "Heal", ShopStats.heal_id);
	
	public static final ItemStack money = Inventory.createItemStack(Material.GOLD_NUGGET, Colors.yellow + Colors.bold + "Money", money_id);
}