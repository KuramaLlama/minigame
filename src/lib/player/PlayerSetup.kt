package lib.player

import org.bukkit.GameMode
import org.bukkit.Location
import org.bukkit.entity.Player
import org.bukkit.potion.PotionEffect

class PlayerSetup(private val player: Player) {

    fun welcomePlayer(playerWelcomer: PlayerWelcomer) {
        playerWelcomer.welcomePlayer(player)
    }

    fun teleportPlayer(x: Double, y: Double, z: Double) {
        player.teleport(Location(player.world, x, y, z))
    }

    fun setPlayerGameMode(gameMode: GameMode) {
        player.gameMode = gameMode
    }

    fun setupInventory(inventorySetup: (Player) -> Unit) {
        inventorySetup(player)
    }

    fun setEffects(vararg potionEffects: PotionEffect) {
        for(potionEffect in potionEffects)
            player.addPotionEffect(potionEffect)
    }

    fun removeAllEffects() {
        player.activePotionEffects.forEach { potionEffect ->
            player.removePotionEffect(potionEffect.type)
        }
    }
}
