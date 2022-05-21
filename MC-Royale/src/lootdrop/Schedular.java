package lootdrop;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;

import item.Colors;
import main.Session;

public class Schedular implements Listener {
    
    @SuppressWarnings("deprecation")
	@EventHandler
    public void onFallBlock(EntityChangeBlockEvent e) {
    	
    	if(!(e.getEntity() instanceof FallingBlock)) return;
	    if(e.getTo() != Material.SAND) return;
	    
	    Block block_sand = e.getBlock();
	    block_sand.getLocation().getBlock().setType(Material.BEACON);
        
        World world = Bukkit.getWorld(Session.getSession().defaultWorld);
        int x = (int) block_sand.getLocation().getX();
        int y = (int) block_sand.getLocation().getY();
        int z = (int) block_sand.getLocation().getZ();
        
        for(Player t : Bukkit.getOnlinePlayers()) {
        	if(t.getEyeLocation().distance(block_sand.getLocation()) < 40) {
        		t.playSound(block_sand.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 5F, 10F);
        	}
        }
        
        e.getEntity().remove();
        e.getEntity().eject();
        e.setCancelled(true);
        
        new Location(world, x, y, z).getBlock().setType(Material.BEACON);
        
        new Location(world, x, y + 2, z).getBlock().setType(Material.WHITE_STAINED_GLASS);
        
        new Location(world, x - 1, y - 1, z + 1).getBlock().setType(Material.IRON_BLOCK);
        new Location(world, x - 1, y - 1, z + 0).getBlock().setType(Material.IRON_BLOCK);
        new Location(world, x - 1, y - 1, z - 1).getBlock().setType(Material.IRON_BLOCK);
        new Location(world, x + 0, y - 1, z + 1).getBlock().setType(Material.IRON_BLOCK);
        new Location(world, x + 0, y - 1, z + 0).getBlock().setType(Material.IRON_BLOCK);
        new Location(world, x + 0, y - 1, z - 1).getBlock().setType(Material.IRON_BLOCK);
        new Location(world, x + 1, y - 1, z + 1).getBlock().setType(Material.IRON_BLOCK);
        new Location(world, x + 1, y - 1, z + 0).getBlock().setType(Material.IRON_BLOCK);
        new Location(world, x + 1, y - 1, z - 1).getBlock().setType(Material.IRON_BLOCK);
        
        new Location(world, x - 1, y, z).getBlock().setType(Material.CHEST);
        new Location(world, x + 1, y, z).getBlock().setType(Material.CHEST);
        new Location(world, x, y, z - 1).getBlock().setType(Material.CHEST);
        new Location(world, x, y, z + 1).getBlock().setType(Material.CHEST);
       
        Block block = new Location(world, x - 1, y, z).getBlock();
        block.setBlockData(Bukkit.getUnsafe().fromLegacy(block.getType(), (byte) 4), true);
        
        block = new Location(world, x + 1, y, z).getBlock();
        block.setBlockData(Bukkit.getUnsafe().fromLegacy(block.getType(), (byte) 5), true);
        
        block = new Location(world, x, y, z - 1).getBlock();
        block.setBlockData(Bukkit.getUnsafe().fromLegacy(block.getType(), (byte) 1), true);
        
        block = new Location(world, x, y, z + 1).getBlock();
        block.setBlockData(Bukkit.getUnsafe().fromLegacy(block.getType(), (byte) 3), true);
        
        new Location(world, x - 1, y, z - 1).getBlock().setType(Material.COBBLESTONE_WALL);
        new Location(world, x - 1, y, z + 1).getBlock().setType(Material.COBBLESTONE_WALL);
        new Location(world, x + 1, y, z - 1).getBlock().setType(Material.COBBLESTONE_WALL);
        new Location(world, x + 1, y, z + 1).getBlock().setType(Material.COBBLESTONE_WALL);
        
        new Location(world, x - 1, y + 1, z - 1).getBlock().setType(Material.OAK_FENCE);
        new Location(world, x - 1, y + 1, z + 1).getBlock().setType(Material.OAK_FENCE);
        new Location(world, x + 1, y + 1, z - 1).getBlock().setType(Material.OAK_FENCE);
        new Location(world, x + 1, y + 1, z + 1).getBlock().setType(Material.OAK_FENCE);
        
        new Location(world, x - 1, y + 2, z - 1).getBlock().setType(Material.SPRUCE_SLAB);
        new Location(world, x - 1, y + 2, z + 1).getBlock().setType(Material.SPRUCE_SLAB);
        new Location(world, x + 1, y + 2, z - 1).getBlock().setType(Material.SPRUCE_SLAB);
        new Location(world, x + 1, y + 2, z + 1).getBlock().setType(Material.SPRUCE_SLAB);
        
        new Location(world, x - 1, y + 2, z).getBlock().setType(Material.SPRUCE_SLAB);
        new Location(world, x + 1, y + 2, z).getBlock().setType(Material.SPRUCE_SLAB);
        new Location(world, x, y + 2, z - 1).getBlock().setType(Material.SPRUCE_SLAB);
        new Location(world, x, y + 2, z + 1).getBlock().setType(Material.SPRUCE_SLAB);
        
        block =  new Location(world, x - 1, y + 2, z).getBlock();
        block.setBlockData(Bukkit.getUnsafe().fromLegacy(block.getType(), (byte) 9), true);
        
        block =  new Location(world, x + 1, y + 2, z).getBlock();
        block.setBlockData(Bukkit.getUnsafe().fromLegacy(block.getType(), (byte) 9), true);
        
        block =  new Location(world, x, y + 2, z - 1).getBlock();
        block.setBlockData(Bukkit.getUnsafe().fromLegacy(block.getType(), (byte) 9), true);
        
        block =  new Location(world, x, y + 2, z + 1).getBlock();
        block.setBlockData(Bukkit.getUnsafe().fromLegacy(block.getType(), (byte) 9), true);
        
        Bukkit.broadcastMessage(Colors.green + "A Lootdrop summonned");
    }
}