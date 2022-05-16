package commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Villager.Profession;

public class ShopCommand implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(!(sender instanceof Player)) return false;
        
        Player p = (Player) sender;
        
        Location l = new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ(), p.getLocation().getYaw(),  1);
        Villager v = (Villager) p.getWorld().spawnEntity(l, EntityType.VILLAGER);
        
        LivingEntity le = (LivingEntity) v;
        
        v.setCustomName("Shop");
        v.setProfession(Profession.ARMORER);

        v.setAI(false);
        v.setSilent(true);
        le.setInvulnerable(true);
        v.setInvulnerable(true);
        
        
        return false;
	}
}
