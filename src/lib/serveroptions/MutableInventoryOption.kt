package lib.serveroptions

import org.bukkit.event.EventHandler
import org.bukkit.event.inventory.InventoryClickEvent

class MutableInventoryOption : ServerOption() {
    @EventHandler
    fun inventoryClick(event: InventoryClickEvent) {
        if(event.currentItem != null)
            cancelIfDisabled(event)
    }
}