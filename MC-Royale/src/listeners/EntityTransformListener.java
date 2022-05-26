package listeners;

import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTransformEvent;
import org.bukkit.event.entity.EntityTransformEvent.TransformReason;


public class EntityTransformListener implements Listener {

    @EventHandler
    public void onEntityTransformEvent(EntityTransformEvent event) {
        if(event.getTransformedEntity() instanceof Villager && event.getTransformReason().equals(TransformReason.LIGHTNING)) {
            event.setCancelled(true);
        }
    }
    
}
