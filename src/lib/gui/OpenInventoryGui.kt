package lib.gui

import org.bukkit.entity.HumanEntity
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.Inventory

class OpenInventoryGui(private val controllerItemName: String,
                       private val inventoryToOpen: Inventory): Listener {
    @EventHandler
    fun inventoryClick(event: InventoryClickEvent) {
        openInventoryIfClicked(event.currentItem?.itemMeta?.displayName, event.whoClicked)
    }

    @EventHandler
    fun playerInteract(event: PlayerInteractEvent) {
        openInventoryIfClicked(event.item?.itemMeta?.displayName, event.player)
    }

    private fun openInventoryIfClicked(itemName: String?, player: HumanEntity?) {
        if (itemName != null && player != null)
            if(itemName.contains(controllerItemName))
                player.openInventory(inventoryToOpen)
    }
}

