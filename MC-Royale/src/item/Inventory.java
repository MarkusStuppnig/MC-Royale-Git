package item;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Inventory {

	public static boolean remove(ItemStack item, Player p) {
        
        int count = item.getAmount();
        
        if(!p.getInventory().containsAtLeast(item, count)) return false;
        
        
        for (int i = 0; i < p.getInventory().getSize(); i++) {
            ItemStack stack = p.getInventory().getItem(i);

            if(stack == null) continue;
            if(!stack.getType().equals(item.getType())) continue;
            
            int removed = Math.min(count, stack.getAmount());
            count -= removed;

            if(stack.getAmount() == removed) {
                p.getInventory().setItem(i, null);
            }else {
                stack.setAmount(stack.getAmount() - removed);
            }
            
            if (count <= 0) {
                break;
            }
        }
        if(count <= 0) {
            return true;
        }else {
            return false;
        }
    }
	
	public static int getAmount(ItemStack item, Player p) {
		
		int amount = 0;
		
		for (int i = 0; i < p.getInventory().getSize(); i++) {
            ItemStack stack = p.getInventory().getItem(i);

            if(stack == null) continue;
            if(!stack.getType().equals(item.getType())) continue;
            
            amount += stack.getAmount();
        }
		return amount;
	}
	
	public static ItemStack createItemStack(Material material, String displayname, String id) {
		
		ItemStack stack = new ItemStack(material);
		ItemData data = new ItemData(stack);
		ItemMeta meta = stack.getItemMeta();
		
		meta.setDisplayName(displayname);
		stack.setItemMeta(meta);
		
		data.setString("id", id);
		
		return stack;
	}
	
	public static boolean checkId(ItemStack first, ItemStack second) {
		ItemData first_data = new ItemData(first);
		ItemData second_data = new ItemData(second);
		
		return first_data.getString("id").equals(second_data.getString("id"));
	}
}
