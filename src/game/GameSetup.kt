package game

import listeners.playerdeath.SpectateBeforeRespawn
import game.gameenums.GameSpawn
import gui.spectate.SpectateGui
import gui.spectate.controllers.SpectateController
import kits.Kit
import kits.KitsEnum
import lib.gui.OpenInventoryGui
import lib.registers.EventsRegister
import lib.serveroptions.DamageOption
import lib.serveroptions.ServerOptions
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.plugin.java.JavaPlugin
import java.util.*

class GameSetup(private val plugin: JavaPlugin,
                private val eventsRegister: EventsRegister,
                private val playerKitMap: HashMap<Player, Kit>) {

    private val alivePlayers: MutableList<Player> = ArrayList<Player>()

    fun spawnOnlinePlayersInGame() {
        val spawnPoints = GameSpawn.values()
        val onlinePlayers = Bukkit.getOnlinePlayers() as MutableList

        for(i in 0..onlinePlayers.size-1) {
            val player = onlinePlayers[i]
            val spawnPoint = spawnPoints[i]
            player.teleport(Location(player.world, spawnPoint.getX(), spawnPoint.getY(), spawnPoint.getZ(),
                    spawnPoint.getYaw(), spawnPoint.getPitch()))

            alivePlayers.add(onlinePlayers[i])
        }
    }

    fun spawnPlayerRandomSpawn(player: Player) {
        val spawnPoints = GameSpawn.values()
        val randomSpawn = spawnPoints[(Math.random()*spawnPoints.size).toInt()]
        player.teleport(Location(player.world, randomSpawn.getX(), randomSpawn.getY(), randomSpawn.getZ(), randomSpawn.getYaw(),
                randomSpawn.getPitch()))
    }

    fun setServerOptions() {
        ServerOptions.MUTABLEINVENTORYOPTION.toggle()
        (ServerOptions.DAMAGEOPTION.getServerOption() as DamageOption).addDamageCause(EntityDamageEvent.DamageCause.FALL)
    }

    fun givePlayersKits() {
        Bukkit.getOnlinePlayers().forEach { player ->
            val playerKit = playerKitMap[player]

            if(playerKit != null)
                givePlayerKit(player, playerKit)
            else {
                playerKitMap.put(player, KitsEnum.JUGGERNAUT.kit())
                givePlayerKit(player, KitsEnum.JUGGERNAUT.kit())
            }
        }
    }

    fun registerEvents() {
        eventsRegister.registerEvents(SpectateBeforeRespawn(plugin, alivePlayers as ArrayList<Player>))
    }

    fun registerGuis() {
        val spectateGui = SpectateGui(alivePlayers)
        spectateGui.setPlayerHeadIcons()
        val spectateInventory = spectateGui.build()
        eventsRegister.registerEvents(spectateGui, OpenInventoryGui("Spectate", spectateInventory),
                SpectateController(spectateInventory))
    }

    private fun givePlayerKit(player: Player, kit: Kit) {
        player.inventory.clear()
        kit.setItemDefaultNames()
        kit.getKitItems().forEach { item -> player.inventory.addItem(item) }
    }
}
