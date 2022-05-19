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
			if(!this.file.exists()) {
				this.file.createNewFile();
			}
	
			this.config.save(this.file);
			
		}catch(IOException e) {}
	}
	
	public void deleteFile(boolean delete) {
		if(!delete) return;
		
		this.config = null;
		this.file.delete();
	}
	
	public Location getLocation(String key) {
		//Get Values
		String world = this.config.getString(key + ".world");
		double x = this.config.getDouble(key + ".x");
		double y = this.config.getDouble(key + ".y");
		double z = this.config.getDouble(key + ".z");
		double yaw = this.config.getDouble(key + ".yaw");
		double pitch = this.config.getDouble(key + ".pitch");
		
		//Make Location
		Location loc = new Location(Bukkit.getWorld(world), x, y, z);
		loc.setYaw((float) yaw);
		loc.setPitch((float) pitch);
		return loc;
	}
	
	public void setLocation(String name, Location loc) {
		config.set(name + ".x", loc.getX());
		config.set(name + ".y", loc.getY());
		config.set(name + ".z", loc.getZ());
		config.set(name + ".world", loc.getWorld().getName());
		config.set(name + ".yaw", (double) loc.getYaw());
		config.set(name + ".pitch", (double) loc.getPitch());
		this.saveConfig();
	}
	
	public void set(String key, Object obj) {
		config.set(key, obj);
		this.saveConfig();
	}
}