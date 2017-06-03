package lib.registers

import lib.serveroptions.ServerOptions
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin

class EventsRegister (private val bukkitPlugin : JavaPlugin){

    fun registerEvents(vararg events: Listener) {
        events.forEach { event -> registerEvent(event) }
    }

    fun registerEvents(vararg options: ServerOptions) {
        options.forEach { option -> registerEvent(option.getServerOption()) }
    }

    private fun registerEvent(event: Listener) {
        bukkitPlugin.server.pluginManager.registerEvents(event, bukkitPlugin)
    }
}