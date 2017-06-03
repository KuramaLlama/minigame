package listeners.playerjoin

import lib.messages.titletext.TitleText
import lib.player.PlayerSetup
import lib.player.PlayerWelcomer
import net.md_5.bungee.api.ChatColor
import org.bukkit.GameMode
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class PlayerJoinWaitingSetup : Listener {

    private val welcomeTitle = TitleText("{\"text\":\"Weaboo Wars\",\"color\":\"aqua\"}",
            "{\"text\":\"Weaboos UNITE\",\"color\":\"gold\"}", 20, 40, 30)
    private val playerWelcomer = PlayerWelcomer(welcomeTitle)

    @EventHandler
    fun playerJoinWaiting(event: PlayerJoinEvent) {
        initPlayer(PlayerSetup(event.player))
    }

    private fun initPlayer(playerSetup: PlayerSetup) {
        playerSetup.welcomePlayer(playerWelcomer)
        playerSetup.teleportPlayer(0.5, 11.0, 0.5)
        playerSetup.setPlayerGameMode(GameMode.ADVENTURE)
        playerSetup.removeAllEffects()
        playerSetup.setEffects(
                PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 5),
                PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 3))
        playerSetup.setupInventory({player -> setupInventory(player)})
    }

    private fun setupInventory(player: Player) {
        val item = ItemStack(Material.NETHER_STAR)
        val itemMeta = item.itemMeta

        player.inventory.clear()
        player.inventory.armorContents = kotlin.arrayOfNulls(4)
        itemMeta.displayName = "${ChatColor.LIGHT_PURPLE}Kit Selector"
        item.itemMeta = itemMeta
        player.inventory.setItem(0, item)
    }
}