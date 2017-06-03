package game

import game.gameenums.GameState
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.event.player.PlayerQuitEvent

class GameHandler(private val gameRunner: GameRunner): Listener {

    @EventHandler
    fun playerJoin(event: PlayerJoinEvent) {
        changeGameStateOnPlayerCount(Bukkit.getOnlinePlayers().size)
    }

    @EventHandler
    fun playerQuit(event: PlayerQuitEvent) {
        changeGameStateOnPlayerCount(Bukkit.getOnlinePlayers().size-1)
    }

    @EventHandler
    fun freezePlayersOnPregame(event: PlayerMoveEvent) {
        if(event.from.blockX != event.to.blockX || event.from.blockZ != event.to.blockZ)
            if(gameRunner.getGameState() == GameState.PREGAME)
                event.player.teleport(event.from)
    }

    private fun changeGameStateOnPlayerCount(onlinePlayers: Int) {
        if(gameRunner.getGameState() != GameState.INGAME)
            gameRunner.setGameState(
                    if(onlinePlayers >= 2)
                        GameState.COUNTDOWN
                    else
                    GameState.WAITING
            )
    }
}
