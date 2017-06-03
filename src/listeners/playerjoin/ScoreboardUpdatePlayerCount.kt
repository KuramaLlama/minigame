package listeners.playerjoin

import scoreboard.WaitingScoreboardBuilder
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import scoreboard.PlayerScoreboardHandler

class ScoreboardUpdatePlayerCount(private val playerScoreboardHandler: PlayerScoreboardHandler) : Listener{

    @EventHandler
    fun playerJoin(event: PlayerJoinEvent) {
        playerScoreboardHandler.addEntry(event.player, WaitingScoreboardBuilder())
        updatePlayerCountScoreboard(Bukkit.getOnlinePlayers().size)
    }

    @EventHandler
    fun playerQuit(event: PlayerQuitEvent) {
        playerScoreboardHandler.removeEntry(event.player)
        updatePlayerCountScoreboard(Bukkit.getOnlinePlayers().size - 1)
    }

    private fun updatePlayerCountScoreboard(playerCount: Int) {
        playerScoreboardHandler.getPlayerScoreboardMap().values.forEach { scoreboardBuilder ->
            if(scoreboardBuilder is WaitingScoreboardBuilder)
                scoreboardBuilder.setPlayerCount(playerCount)
        }
        playerScoreboardHandler.refreshBoards()
    }
}