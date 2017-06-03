package listeners.playerjoin

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerQuitEvent

class BroadcastPlayerLeft : Listener {

    @EventHandler
    fun playerQuit(event: PlayerQuitEvent) {
        val player = event.player
        event.quitMessage = "${player.name} ${ChatColor.YELLOW}has left the game! ${ChatColor.GREEN}(${Bukkit.getOnlinePlayers().size - 1}/8)"
    }
}
