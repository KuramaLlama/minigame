package listeners.playerdeath

import lib.utils.ItemUtils
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.player.PlayerRespawnEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import org.bukkit.scheduler.BukkitRunnable
import java.util.*

class SpectateBeforeRespawn(private val plugin: JavaPlugin,
                            private val alivePlayers: ArrayList<Player>): Listener {

    private val spectatorList: MutableList<Player> = ArrayList<Player>()

    @EventHandler
    fun playerDeath(event: PlayerRespawnEvent) {
        setupSpectator(event.player)
        spectatorList.add(event.player)
    }

    @EventHandler
    fun cancelSpectatorDamage(event: EntityDamageByEntityEvent) {
        if(spectatorList.contains(event.entity) || spectatorList.contains(event.damager))
            event.isCancelled = true
    }

    private fun setupSpectator(player: Player) {
        object: BukkitRunnable() {
            override fun run() {
                player.addPotionEffect(PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 2))
                setInventory(player)
            }
        }.runTaskLater(plugin, 1L)

        player.allowFlight = true
        Bukkit.getOnlinePlayers().forEach { onlinePlayer -> onlinePlayer.hidePlayer(player) }
    }

    private fun setInventory(player: Player) {
        val playerInventory = player.inventory
        playerInventory.clear()

        val spectateSelector = ItemStack(Material.COMPASS)
        ItemUtils().changeItemName(spectateSelector, "${ChatColor.GREEN}Spectate")
        playerInventory.addItem(spectateSelector)
    }
}
