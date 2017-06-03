package commands

import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Kys : CommandExecutor{
    override fun onCommand(sender: CommandSender?, p1: Command?, p2: String?, p3: Array<out String>?): Boolean {
        if(sender is Player) {
            sender.sendMessage("${ChatColor.RED}NO! NO KYS!!! DIEEEE >:L")
            sender.world.createExplosion(sender.location, 4F)
            return true
        }
        return false
    }
}