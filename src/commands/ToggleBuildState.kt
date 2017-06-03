package commands

import lib.serveroptions.BuildOption
import net.md_5.bungee.api.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class ToggleBuildState(private val buildState: BuildOption) : CommandExecutor {
    override fun onCommand(sender: CommandSender?, command: Command?, message: String?,
                           strings: Array<out String>?): Boolean {
        buildState.setDisabled(!buildState.getDisabled())
        val response = if(buildState.getDisabled()) "${ChatColor.RED}Build is OFF" else "${ChatColor.RED}Build is ON"
        sender?.sendMessage(response)
        return true
    }
}