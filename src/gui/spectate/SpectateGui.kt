package gui.spectate

import lib.gui.UneditableInventoryGui
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.SkullMeta
import java.util.*

class SpectateGui(private val alivePlayers: MutableList<Player>): UneditableInventoryGui("Spectate") {
    fun setPlayerHeadIcons() {
        alivePlayers.forEach { player ->
            addItemIcon(makePlayerHead(player))
        }
    }

    private fun makePlayerHead(player: Player): ItemStack {
        val playerHead = ItemStack(Material.SKULL_ITEM, 1, 3)

        val skullMeta = playerHead.itemMeta
        if(skullMeta is SkullMeta) {
            skullMeta.owner = player.name
            skullMeta.displayName = player.name
            skullMeta.lore = Arrays.asList("Teleport to ${player.name}")
            playerHead.itemMeta = skullMeta
        }
        return playerHead
    }
}
