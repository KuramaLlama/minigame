package lib.serveroptions

import org.bukkit.event.EventHandler
import org.bukkit.event.entity.PlayerDeathEvent

class DropItemOnDeathOption : ServerOption() {
    @EventHandler
    fun playerDeathDrop(event: PlayerDeathEvent) {
        if(getDisabled()) {
            event.drops.clear()
        }
    }
}
