package gui.kit.controllers

import kits.KitAssigner
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.Inventory
import scoreboard.PlayerScoreboardHandler
import scoreboard.WaitingScoreboardBuilder

class SelectKitController(private val inventory: Inventory,
                          private val playerScoreboardHandler: PlayerScoreboardHandler,
                          private val kitAssigner: KitAssigner) : Listener {

    @EventHandler
    fun inventoryClick(event: InventoryClickEvent) {
        if(event.inventory.name.equals(inventory.name))
            selectKit(event)
    }

    private fun selectKit(event: InventoryClickEvent) {
        val selectedKit = kitAssigner.getKitByIcon(event.currentItem)

        if(selectedKit != null) {
            event.whoClicked.sendMessage("${ChatColor.YELLOW}You selected the ${ChatColor.LIGHT_PURPLE}${selectedKit.getName()}" +
                    "${ChatColor.YELLOW} Kit!")
            kitAssigner.assignPlayerKit(event.whoClicked as Player, selectedKit)
            event.whoClicked.closeInventory()
            updateScoreboardKit(selectedKit.getName(), event.whoClicked as Player)
        }
    }

    private fun updateScoreboardKit(kit: String, player: Player) {
        val weabooScoreboardBuilder = playerScoreboardHandler.getPlayerScoreboardMap()[player]
        if(weabooScoreboardBuilder != null && weabooScoreboardBuilder is WaitingScoreboardBuilder) {
            weabooScoreboardBuilder.setKitSelected(kit)
            weabooScoreboardBuilder.revalidate()
            player.scoreboard = weabooScoreboardBuilder.getScoreboard()
        }
    }
}
