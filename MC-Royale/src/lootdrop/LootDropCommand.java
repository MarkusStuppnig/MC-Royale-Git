package lootdrop;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import main.Session;

public class LootDropCommand implements CommandExecutor {
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command command, String string, String[] args) {
		
		if(!(sender instanceof Player)) {
			sender.sendMessage(Session.noPlayer);
			return true;
		}

		Player p = (Player) sender;
		Location loc = p.getLocation().add(new Location(Bukkit.getServer().getWorld(Session.getSession().defaultWorld), 0.0, 25, 0.0));
		
		FallingBlock block = (FallingBlock) loc.getWorld().spawnFallingBlock(loc, Material.SAND, (byte) 0);
		block.setCustomName("Lootbox");
		block.setCustomNameVisible(true);
		block.setGlowing(true);
		
		new BukkitRunnable() {
			
			@Override
            public void run() {
            	if(block.isDead()) cancel();
            	
            	block.setVelocity(new Vector(Session.getSession().wind.x, -0.1F, Session.getSession().wind.z));
            }
        }.runTaskTimer(Session.getSession(), 0, 1);
		
		return true;
	}
}