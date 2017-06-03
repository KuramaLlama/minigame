package lib.gui

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent

open class UneditableInventoryGui(private val name: String) : Listener, InventoryGui(name) {

    @EventHandler
    fun inventoryClick(event: InventoryClickEvent) {
        if(event.inventory.name.equals(name))
            event.isCancelled = true
    }
}
