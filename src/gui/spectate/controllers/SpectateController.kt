package gui.spectate.controllers

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.Inventory

class SpectateController(private val inventory: Inventory): Listener {
    @EventHandler
    fun inventoryClick(event: InventoryClickEvent) {
        if(event.inventory.name.equals(inventory.name))
            teleToPlayer(event.whoClicked as Player, event.currentItem.itemMeta.displayName)
    }

    private fun teleToPlayer(player: Player, playerName: String) {
        Bukkit.getOnlinePlayers().forEach { onlinePlayer ->
            if(onlinePlayer.displayName.equals(playerName))
                onlinePlayer.teleport(onlinePlayer.location)
        }
    }
}