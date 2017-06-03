package scoreboard

import lib.messages.scoreboard.ScoreboardBuilder
import org.bukkit.entity.Player
import java.util.*

class PlayerScoreboardHandler(private val playerScoreboardMap: HashMap<Player, ScoreboardBuilder>) {

    fun addEntry(player: Player, scoreboardBuilder: ScoreboardBuilder) {
        playerScoreboardMap.put(player, scoreboardBuilder)
        scoreboardBuilder.build()
    }

    fun removeEntry(player: Player) {
        playerScoreboardMap.remove(player)
    }

    fun updateAll(lineNumb: Int, entry: String) {
        runAndRevalidate { player, scoreboardBuilder -> scoreboardBuilder.update(lineNumb, entry) }
    }

    fun refreshBoards() {
        runAndRevalidate { player, scoreboardBuilder -> player.scoreboard = scoreboardBuilder.getScoreboard() }
    }

    fun getPlayerScoreboardMap() : HashMap<Player, ScoreboardBuilder> {
        return playerScoreboardMap
    }

    fun runAndRevalidate(run : (player: Player, scoreboardB: ScoreboardBuilder) -> Unit) {
        playerScoreboardMap.forEach { player, scoreboardBuilder ->
            run(player, scoreboardBuilder)
            scoreboardBuilder.revalidate()
        }
    }
}
