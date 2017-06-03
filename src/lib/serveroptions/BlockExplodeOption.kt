package lib.serveroptions

import org.bukkit.event.Event
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockExplodeEvent
import org.bukkit.event.entity.EntityExplodeEvent

class BlockExplodeOption : ServerOption(), Listener {

    private var animationDisplayed = true

    @EventHandler
    fun onBlockExplode(event: BlockExplodeEvent) {
        cancelExplodedBlocksIfDisabled(event)
    }

    @EventHandler
    fun onEntityExplode(event: EntityExplodeEvent) {
        cancelExplodedBlocksIfDisabled(event)
    }

    private fun cancelExplodedBlocksIfDisabled(event: Event) {
        if(getDisabled())
            if(event is EntityExplodeEvent)
                event.blockList().clear()
            else if(event is BlockExplodeEvent)
                event.blockList().clear()
    }
}