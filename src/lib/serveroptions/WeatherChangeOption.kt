package lib.serveroptions

import org.bukkit.event.EventHandler
import org.bukkit.event.weather.WeatherChangeEvent

class WeatherChangeOption : ServerOption() {

    @EventHandler
    fun weatherChange(event: WeatherChangeEvent) {
        cancelIfDisabled(event)
    }
}