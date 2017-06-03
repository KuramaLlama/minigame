package commands

import net.md_5.bungee.api.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class Hello (private val message: String): CommandExecutor{
    override fun onCommand(sender: CommandSender?, p1: Command?, p2: String?, p3: Array<out String>?): Boolean {
        sender?.sendMessage("${ChatColor.AQUA}$message")
        return true
    }
}