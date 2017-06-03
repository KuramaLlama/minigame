package listeners.playerjoin

import net.md_5.bungee.api.ChatColor
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class BroadcastPlayerJoin : Listener {

    @EventHandler
    fun playerJoin(event: PlayerJoinEvent) {
        event.joinMessage = "${event.player.name}${ChatColor.YELLOW} has joined the game! " +
                "${ChatColor.GREEN}(${Bukkit.getOnlinePlayers().size}/8)"
    }
}
