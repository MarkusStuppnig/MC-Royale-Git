package config;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Config {

	private File file;
	public FileConfiguration config;
	
	public Config(String filename) {
		this.file = new File(filename);
		this.config = YamlConfiguration.loadConfiguration(file);
		
		this.saveConfig();
	}
	
	private void saveConfig() {
		try {
			if(!file.exists()) {
				file.createNewFile();
			}
	
			this.config.save(file);
		}catch(IOException e) {}
	}
	
	public Location getLocation(String name) {
		//Get Values
		String world = this.config.getString(name + ".world");
		double x = this.config.getDouble(name + ".x");
		double y = this.config.getDouble(name + ".y");
		double z = this.config.getDouble(name + ".z");
		double yaw = this.config.getDouble(name + ".yaw");
		double pitch = this.config.getDouble(name + ".pitch");
		
		//Make Location
		Location loc = new Location(Bukkit.getWorld(world), x, y, z);
		loc.setYaw((float) yaw);
		loc.setPitch((float) pitch);
		return loc;
	}
	
	public void saveLocation(String name, Location loc) {
		config.set(name + ".x", loc.getX());
		config.set(name + ".y", loc.getY());
		config.set(name + ".z", loc.getZ());
		config.set(name + ".world", loc.getWorld().getName());
		config.set(name + ".yaw", (double) loc.getYaw());
		config.set(name + ".pitch", (double) loc.getPitch());
		saveConfig();
	}
}