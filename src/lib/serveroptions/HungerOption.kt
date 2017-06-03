package lib.serveroptions

import org.bukkit.event.EventHandler
import org.bukkit.event.entity.FoodLevelChangeEvent

class HungerOption : ServerOption() {

    @EventHandler
    fun onFoodLvlChange(event : FoodLevelChangeEvent) {
        cancelIfDisabled(event)
    }
}