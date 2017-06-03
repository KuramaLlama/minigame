package listeners.chat

import org.bukkit.ChatColor
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent

class ChatFormat : Listener {

    @EventHandler
    fun asyncPlayerChat(event: AsyncPlayerChatEvent) {
        event.format = "${ChatColor.AQUA}${event.player.displayName}${ChatColor.WHITE}: ${event.message}"
    }
}
