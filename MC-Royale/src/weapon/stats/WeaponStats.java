package weapon.stats;

import org.bukkit.Material;

import item.Colors;
import weapon.Weapon;

public class WeaponStats {

	public static final String ammo_left_in_magazine = "ammo_left_in_magazine";
	
	public static final String ak47_display_name = Colors.aqua + Colors.bold + "AK74";
	public static final String pump_display_name = Colors.dark_aqua + Colors.bold + "Pump";
	public static final String sniper_display_name = Colors.dark_gray + Colors.bold + "Sniper";
	
	public static Weapon ak47() {
		return new Weapon(Material.DIAMOND, ak47_display_name, 20, 2.3, 5F, 5F, 1, 1, 8);
	}
	
	public static Weapon pump() {
		return new Weapon(Material.EMERALD, pump_display_name, 6, 3.0, 1.5F, 50F, 200, 10, 10);
	}
	
	public static Weapon sniper() {
		return new Weapon(Material.IRON_INGOT, sniper_display_name, 6, 5.0, 12F, 0.02F, 1, 20, 42);
	}
}