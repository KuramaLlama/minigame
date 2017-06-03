package lib.registers

import org.bukkit.command.CommandExecutor
import org.bukkit.plugin.java.JavaPlugin

class CommandsRegister(private val bukkitPlugin: JavaPlugin){

    fun registerCommands(commands : Map<String, CommandExecutor>) {
        for((name, command) in commands)
            bukkitPlugin.getCommand(name).executor = command
    }
}