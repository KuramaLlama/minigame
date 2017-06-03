package lib.serveroptions

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.block.BlockPlaceEvent

class BuildOption : ServerOption() {

    @EventHandler
    fun onBlockBreak(event: BlockBreakEvent) {
        cancelIfDisabled(event)
    }

    @EventHandler
    fun onBlockPlace(event: BlockPlaceEvent) {
        cancelIfDisabled(event)
    }
}