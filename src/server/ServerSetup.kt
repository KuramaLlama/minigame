package server

import lib.registers.CommandsRegister
import lib.registers.EventsRegister

import org.bukkit.command.CommandExecutor
import org.bukkit.plugin.java.JavaPlugin
import commands.*
import lib.serveroptions.BuildOption
import lib.serveroptions.ServerOptions
import listeners.chat.ChatFormat
import listeners.playerjoin.BroadcastPlayerJoin
import listeners.playerjoin.BroadcastPlayerLeft
import java.util.*

class ServerSetup(private val bukkitPlugin: JavaPlugin,
                  private val commandsRegister: CommandsRegister,
                  private val eventsRegister: EventsRegister){

    fun setupWorlds() {
        bukkitPlugin.server.worlds.forEach { world ->
            world.setStorm(false)
            world.isAutoSave = false
            world.setSpawnLocation(0, 11, 0)
        }
    }

    fun registerCommands() {
        val commands = HashMap<String, CommandExecutor>()
        commands.put("hello", Hello("Why hello there!"))
        commands.put("build", ToggleBuildState(ServerOptions.BUILDOPTION.getServerOption() as BuildOption))
        commands.put("kys", Kys())

        commandsRegister.registerCommands(commands)
    }

    fun registerOptions() {
        eventsRegister.registerEvents(
                ServerOptions.BUILDOPTION,
                ServerOptions.BLOCKEXPLODE,
                ServerOptions.HUNGEROPTION,
                ServerOptions.DAMAGEOPTION,
                ServerOptions.WEATHERCHANGEOPTION,
                ServerOptions.MUTABLEINVENTORYOPTION,
                ServerOptions.DROPITEMONDEATHOPTION,
                ServerOptions.ITEMDROPOPTION
        )
    }

    fun registerEvents() {
        eventsRegister.registerEvents(BroadcastPlayerJoin(), BroadcastPlayerLeft(), ChatFormat())
    }
}