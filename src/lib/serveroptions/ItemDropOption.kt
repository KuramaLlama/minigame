package lib.serveroptions

import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerDropItemEvent

class ItemDropOption : ServerOption() {
    @EventHandler
    fun plyerDrop(event: PlayerDropItemEvent) {
        cancelIfDisabled(event)
    }
}