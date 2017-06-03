package lib.serveroptions

import org.bukkit.event.EventHandler
import org.bukkit.event.entity.EntityDamageEvent
import java.util.*

class DamageOption : ServerOption() {

    private var damageCauses: MutableList<EntityDamageEvent.DamageCause> = ArrayList<EntityDamageEvent.DamageCause>()

    fun addDamageCause(vararg damageCauses: EntityDamageEvent.DamageCause) {
        damageCauses.forEach { cause -> this.damageCauses.add(cause) }
    }

    fun removeDamageCause(damageCause: EntityDamageEvent.DamageCause) {
        damageCauses.remove(damageCause)
    }

    @EventHandler
    fun playerDamage(event: EntityDamageEvent) {
        if(!damageCauses.isEmpty())
            cancelDamage(event)
        else
            cancelIfDisabled(event)
    }

    fun cancelDamage(event: EntityDamageEvent) {
        damageCauses.forEach { cause ->
            if(event.cause == cause)
                cancelIfDisabled(event)
        }
    }
}