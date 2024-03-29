package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import config.Config;

import listeners.*;
import commands.*;

import item.Colors;

import health.*;
import health.commands.*;
import lootdrop.*;
import quest.*;
import quest.commands.*;
import shop.*;
import shop.commands.*;
import weapon.*;
import weapon.commands.*;

public class Session extends JavaPlugin {

	private static Session session;
	
	public final String name;
	public final String version;
	
	public final String defaultWorld;
	
	public static ArrayList<Weapon> weapons;
	public static HashMap<String, String> reload_players;
	public static ArrayList<String> firerate_players;
	
	public Config gameStats;
	public Config quests;
	public Config locations;
	
	public Random random;
	
	public Wind wind;
	
	public static final String noPlayer = Colors.red + "You need to be a Player to run this command.";
	
	public Session() {
		this.name = "MC-Royale";
		this.version = "0.1.1";
		
		this.defaultWorld = "world";
		
		weapons = new ArrayList<Weapon>();
		reload_players = new HashMap<String, String>();
		firerate_players = new ArrayList<String>();
		
		this.gameStats = new Config("./config/gameStats.yaml");
		this.quests = new Config("./config/quests.yaml");
		this.locations = new Config("./config/locations.yaml");
		
		this.random = new Random();
	}
	
	public static Session getSession() {
		return session;
	}
	
	@Override
	public void onEnable() {
		super.onEnable();
		
		session = this;
		session.print(Colors.green + "Enabled: " + session.name + " v" + session.version);
		
		this.wind = new Wind();
		this.wind.runTaskTimer(Session.getSession(), 0, 5);
		
		this.getCommand("weapon").setExecutor(new WeaponCommand());
		this.getCommand("shop").setExecutor(new ShopCommand());
		this.getCommand("quests").setExecutor(new QuestCommand());
		this.getCommand("health").setExecutor(new HealthCommand());
		this.getCommand("heal").setExecutor(new HealCommand());
		this.getCommand("money").setExecutor(new MoneyCommand());
		
		this.getCommand("setspawn").setExecutor(new SetSpawnCommand());
		this.getCommand("start").setExecutor(new StartCommand());
		this.getCommand("lootdrop").setExecutor(new LootDropCommand());
		
		this.getServer().getPluginManager().registerEvents(new MouseClickListener(), this);
		this.getServer().getPluginManager().registerEvents(new ArrowListener(), this);
		this.getServer().getPluginManager().registerEvents(new HotbarSwitchListener(), this);
		this.getServer().getPluginManager().registerEvents(new ItemDropListener(), this);
		this.getServer().getPluginManager().registerEvents(new ItemPickupListener(), this);
		this.getServer().getPluginManager().registerEvents(new PlayerInterectEntityListener(), this);
		this.getServer().getPluginManager().registerEvents(new InventoryClickListener(), this);
		this.getServer().getPluginManager().registerEvents(new EntityDamageByEntityListener(), this);
		this.getServer().getPluginManager().registerEvents(new EntityDeathListener(), this);
		this.getServer().getPluginManager().registerEvents(new EntityTransformListener(), this);
		
		this.getServer().getPluginManager().registerEvents(new EntityDamageListener(), this);
		this.getServer().getPluginManager().registerEvents(new FoodLevelChangeListener(), this);
		this.getServer().getPluginManager().registerEvents(new PlayerItemConsumeListener(), this);
		this.getServer().getPluginManager().registerEvents(new EntityRegainHealthListener(), this);
		
		this.getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
		
		//Lootdrop
		this.getServer().getPluginManager().registerEvents(new Schedular(), this);
		
		//Quests
		this.getServer().getPluginManager().registerEvents(new QuestItemListener(), this);
		
		for(Player t : Bukkit.getOnlinePlayers()) {
			if(t.isOp()) {
				t.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, Integer.MAX_VALUE, 250));
				t.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 250));
				t.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, Integer.MAX_VALUE, 250));
			}
		}
	}
	
	@Override
	public void onDisable() {
		super.onDisable();
		
		session.print("Disabled: " + session.name + " v" + session.version);
	}
	
	public void print(String str) {
		Bukkit.getServer().getConsoleSender().sendMessage(str);
	}
}