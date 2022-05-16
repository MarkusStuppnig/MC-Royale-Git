package quest.stats;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import item.Colors;
import item.Inventory;

public class QuestStats {

	public static final String completed_quests = "item:completed_quests";
	public static final String running_quests = "item:running_quests";
	public static final String quests = "item:quests";
	
	public static final String inv_quests = Colors.dark_red + Colors.bold + "Quests";
	
	public static final ItemStack item_completed_quests = Inventory.createItemStack(Material.PAPER, Colors.green + Colors.bold + "Completed", QuestStats.completed_quests);
	public static final ItemStack item_running_quests = Inventory.createItemStack(Material.PAPER, Colors.gold + Colors.bold + "Running", QuestStats.running_quests);
	public static final ItemStack item_quests = Inventory.createItemStack(Material.PAPER, Colors.red + Colors.bold + "Quests", QuestStats.quests);
}