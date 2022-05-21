package lootdrop;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import main.Session;

public class LootDropCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String string, String[] args) {
		
		if(!(sender instanceof Player)) {
			sender.sendMessage(Session.noPlayer);
			return true;
		}

		Player p = (Player) sender;
		Location loc = p.getLocation().add(new Location(Bukkit.getServer().getWorld(Session.getSession().defaultWorld), 0.0, 250.0, 0.0));
		
		@SuppressWarnings("deprecation")
		FallingBlock block = (FallingBlock) loc.getWorld().spawnFallingBlock(loc, Material.SAND, (byte) 0);
		block.setCustomName("Lootbox");
		block.setCustomNameVisible(true);
		block.setGlowing(true);
		block.setVelocity(new Vector(0F, 0.5F, 0F));
		
		return true;
	}
}