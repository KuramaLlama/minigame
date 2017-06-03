package lib.serveroptions

import org.bukkit.event.Cancellable
import org.bukkit.event.Listener

open class ServerOption : Listener {

    private var disabled = true

    fun getDisabled(): Boolean = disabled

    fun setDisabled(disabled: Boolean) {
        this.disabled = disabled
    }

    protected fun cancelIfDisabled(cancellable: Cancellable) {
        cancellable.isCancelled = disabled
    }
}