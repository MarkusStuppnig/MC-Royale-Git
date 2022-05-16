package quest;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import main.Session;
import quest.stats.QuestStats;

public class QuestItemListener implements Listener {

	@EventHandler
	public void onQuestInventoryClick(InventoryClickEvent e) {
		
		if(!(e.getView().getTitle().equals(QuestStats.inv_quests))) return;
		if(e.getCurrentItem() == null) return;
		
		e.setCancelled(true);
		
		Player p = (Player) e.getWhoClicked();
		
		if(item.Inventory.checkId(e.getCurrentItem(), QuestStats.item_completed_quests)) {
			e.getWhoClicked().sendMessage("Completed Quests");
			Session.getSession().quests.config.set(p.getName() + ".0", "1");
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