package quest.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import main.Session;
import quest.stats.QuestStats;

public class QuestCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String string, String[] args) {
		
		if(!(sender instanceof Player)) {
			sender.sendMessage(Session.noPlayer);
			return true;
		}
		
		Player p = (Player) sender;
		
		Inventory questInv = Bukkit.createInventory(null, 27, QuestStats.inv_quests);
		questInv.setItem(11, QuestStats.item_completed_quests);
		questInv.setItem(13, QuestStats.item_running_quests);
		questInv.setItem(15, QuestStats.item_quests);
		
        p.openInventory(questInv);
		
		return true;
	}
}