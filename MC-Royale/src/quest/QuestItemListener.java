package quest;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import quest.stats.QuestStats;

public class QuestItemListener implements Listener {

	@EventHandler
	public void onQuestInventoryClick(InventoryClickEvent e) {
		
		if(!(e.getView().getTitle().equals(QuestStats.inv_quests))) return;
		if(e.getCurrentItem() == null) return;
		
		e.setCancelled(true);
		
		if(item.Inventory.checkId(e.getCurrentItem(), QuestStats.item_completed_quests)) {
			e.getWhoClicked().sendMessage("Completed Quests");
			return;
		}
		
		if(item.Inventory.checkId(e.getCurrentItem(), QuestStats.item_running_quests)) {
			e.getWhoClicked().sendMessage("Running Quests");
			return;
		}
		
		if(item.Inventory.checkId(e.getCurrentItem(), QuestStats.item_quests)) {
			e.getWhoClicked().sendMessage("Quests");
			return;
		}
	}
}