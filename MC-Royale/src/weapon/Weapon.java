package weapon;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import item.Colors;
import item.Inventory;
import item.ItemData;
import main.Session;
import weapon.runnables.FirerateRunnable;
import weapon.runnables.ReloadRunnable;
import weapon.stats.WeaponStats;

public class Weapon extends ItemStack {

	private final String name;
	private final int ammo_max;
	private final double reload_time;
	private final float bullet_speed;
	private final float bullet_spread;
	private final int bullet_amount;
	private final int firerate;
	public final int damage;
	
	public final ItemData data;
	
	private int reload_ammo_amount = 0;
	
	private ArrayList<String> reload_ids;
	
	public Weapon(Material material, String name, int ammo_max, double reload_time, float bullet_speed, float bullet_spread, int bullet_amount, int firerate, int damage) {
		super(material);
		
		this.name = name;
		this.ammo_max = ammo_max;
		this.reload_time = reload_time;
		this.bullet_speed = bullet_speed;
		this.bullet_spread = bullet_spread;
		this.bullet_amount = bullet_amount;
		this.firerate = firerate;
		this.damage = damage;
		
		this.data = new ItemData(this);
		
		this.reload_ids = new ArrayList<String>();
		
		this.data.setInteger(WeaponStats.ammo_left_in_magazine, ammo_max);
		this.data.setString("id", UUID.randomUUID().toString());
		
		ItemMeta meta = this.getItemMeta();
		meta.setDisplayName(this.name);
		meta.setLocalizedName(this.name);
		this.setItemMeta(meta);
		this.updateLore(null);
		
		Session.weapons.add(this);
	}
	
	private void updateLore(Player p) {
		ItemMeta meta = this.getItemMeta();
		
		ArrayList<String> lore = new ArrayList<String>();
		lore.clear();
		lore.add(Colors.gray + "Munition: " + Colors.dark_aqua + this.data.getInteger(WeaponStats.ammo_left_in_magazine) + Colors.gray + "/" + this.ammo_max + Colors.reset);
		
		meta.setLore(lore);
		this.setItemMeta(meta);
		
		if(p == null) return;
		
		for(int i = 0; i < p.getInventory().getSize(); i++) {
			if(p.getInventory().getItem(i) != null) {
				if(this.equals(p.getInventory().getItem(i))) {
					p.getInventory().getItem(i).setItemMeta(meta);
					break;
				}
			}
		}
	}
	
	public boolean equals(ItemStack itemStack) {
		if(itemStack == null || itemStack.getItemMeta() == null) return false;
		String id = (new ItemData(itemStack)).getString("id");
		return id != null && id.equals(this.getId());
	}
	
	public String getId() {
		return this.data.getString("id");
	}
	
	
	public void shoot(Player p) {
		if(Session.firerate_players.contains(p.getUniqueId().toString())) return;
		
		if(Session.reload_players.containsKey(p.getUniqueId().toString()) && this.data.getInteger(WeaponStats.ammo_left_in_magazine) > 0) {
			this.reloadAbort(p);
		}
		
		if(this.data.getInteger(WeaponStats.ammo_left_in_magazine) <= 0) {
			p.sendMessage("Magazin leer.");
			return;
		}
		
		Session.firerate_players.add(p.getUniqueId().toString());
		new FirerateRunnable(p).runTaskLater(Session.getSession(), this.firerate);

		for(int i = 0; i < this.bullet_amount; i++) {
			//Spawn Arrow
			Arrow arrow = Bukkit.getWorld(Session.getSession().defaultWorld).spawnArrow(p.getEyeLocation(), p.getEyeLocation().getDirection(), this.bullet_speed, this.bullet_spread);
			arrow.setShooter(p);
			arrow.setCustomName("gunArrow;" + this.getId() + ";" + p.getLocation().getX() + ";" + p.getLocation().getY() + ";" + p.getLocation().getZ() + ";" + p.getUniqueId());
			arrow.setCustomNameVisible(false);
		}
		
		for(Player t : Bukkit.getOnlinePlayers()) {
			t.playSound(p.getLocation(), Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 0.5F, -10F);
		}
		p.spawnParticle(Particle.SMOKE_NORMAL, p.getLocation(), 30, 0.5, 1, 0.0, 0.1);

		this.data.setInteger(WeaponStats.ammo_left_in_magazine, this.data.getInteger(WeaponStats.ammo_left_in_magazine) - 1);
		this.updateLore(p);
		p.setLevel(this.data.getInteger(WeaponStats.ammo_left_in_magazine));
	}
	
	//Reload
	public void reload(Player p) {
		if(Session.reload_players.containsKey(p.getUniqueId().toString())) return;
		if(this.data.getInteger(WeaponStats.ammo_left_in_magazine) == this.ammo_max) return;
		
		ItemStack ammo = new ItemStack(Material.IRON_NUGGET);
		ammo.setAmount(this.ammo_max - this.data.getInteger(WeaponStats.ammo_left_in_magazine));
		
		if(Inventory.getAmount(ammo, p) < ammo.getAmount()) {
			ammo.setAmount(Inventory.getAmount(ammo, p));
		}
		
		if(!Inventory.remove(ammo, p) || ammo.getAmount() == 0) {
			p.sendMessage("Keine Ammo mehr.");
			return;
		}
		
		//Reload
		this.reload_ammo_amount = ammo.getAmount();
		
		String reload_id = UUID.randomUUID().toString();
		this.reload_ids.add(reload_id);
		
		p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, (int) (this.reload_time * 20), 4));
		p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, (int) (this.reload_time * 20), 250));
		
		Session.reload_players.put(p.getUniqueId().toString(), this.getId());
		new ReloadRunnable(p, this, ammo.getAmount() + this.data.getInteger(WeaponStats.ammo_left_in_magazine), reload_id).runTaskLater(Session.getSession(), (int) (this.reload_time * 20));
		
		double seconds = this.reload_time;
		long wait = (long) (((double) (seconds - (int) seconds)) * 20);
    	
        p.setLevel((int) Math.ceil(seconds));
        
        new BukkitRunnable() {
        	
        	private int counter = 1;
        	private int wait = (int) (((double) (seconds - (int) seconds)) * 20);
        	
            @Override
            public void run() {
            	if(!Session.reload_players.containsKey(p.getUniqueId().toString())) {
            		cancel();
            		return;
            	}
            	
            	this.counter++;
            	
            	if(counter >= wait) {
            	
	                if(p.getLevel() >= 1) p.setLevel(p.getLevel() - 1);
	                if(p.getLevel() == 0) {
	                    cancel();
	                }
	                
	                this.counter = 1;
	                this.wait = 20;
            	}
            }
        }.runTaskTimer(Session.getSession(), wait == 0 ? 20 : wait, 1);
        
        p.setExp(1.0F);
        
        new BukkitRunnable() {
        	
            @Override
            public void run() {
            	if(!Session.reload_players.containsKey(p.getUniqueId().toString())) {
            		p.setExp(0.0F);
            		cancel();
            		return;
            	}
            	
                float remove = 1.0F / (float) (seconds * 20);
                float xp = p.getExp();
                xp -= remove;
                if(xp > 0.0F) {
                    p.setExp(xp);
                }else {
                    p.setExp(0.0F);
                    cancel();
                }
            }
        }.runTaskTimer(Session.getSession(), 1, 1);
	}
	
	//End of Reload
	public void reloadEnd(Player p, int amount, String reload_id) {
		if(!Session.reload_players.containsKey(p.getUniqueId().toString())) return;
		if(!this.reload_ids.contains(reload_id)) return;
		
		this.reload_ids.remove(reload_id);
		
		Session.reload_players.remove(p.getUniqueId().toString());
		
        this.data.setInteger(WeaponStats.ammo_left_in_magazine, amount);
        this.updateLore(p);
        p.setLevel(amount);
	}
	
	public void reloadAbort(Player p) {
		Session.reload_players.remove(p.getUniqueId().toString());
		this.reload_ids.clear();
		
		for(PotionEffect potion : p.getActivePotionEffects()) {
			p.removePotionEffect(potion.getType());
		}
		
		ItemStack ammo = new ItemStack(Material.IRON_NUGGET);
		ammo.setAmount(this.reload_ammo_amount);
		p.getInventory().addItem(ammo);
	}
}