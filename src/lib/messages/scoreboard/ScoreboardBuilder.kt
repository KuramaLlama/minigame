package lib.messages.scoreboard

import org.bukkit.scoreboard.DisplaySlot
import org.bukkit.scoreboard.Scoreboard
import java.util.*

abstract class ScoreboardBuilder(private val board: Scoreboard,
                             objectiveName: String,
                             objectiveCriteria: String){

    private val objective = board.registerNewObjective(objectiveName, objectiveCriteria)
    private val entries = HashMap<Int, String>()
    private var line = 0

    abstract fun build()
    abstract fun revalidate()

    fun setDisplayName(name: String) {
        objective.displayName = name
    }

    fun setDisplaySlot(display: DisplaySlot) {
        objective.displaySlot = display
    }

    fun append(text: String) {
        when {
            objective.getScore(text).isScoreSet -> append(text + ' ')
            else -> entries.put(line++, text)
        }
        buildScoreboard()
    }

    fun append() = append("")

    fun update(lineNumb: Int, newEntry: String) {
        val entryIndex = Math.abs(lineNumb-entries.size)
        board.resetScores(entries[entryIndex])
        entries[entryIndex] = newEntry
        buildScoreboard()
    }

    fun getScoreboard(): Scoreboard {
        return board
    }

    private fun buildScoreboard() {
        entries.forEach { lineNumb, text ->
            objective.getScore(text).score = entries.size-lineNumb
        }
    }
}
