package scoreboard

import lib.messages.scoreboard.ScoreboardBuilder
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.scoreboard.DisplaySlot

class InGameScoreboardBuilder : ScoreboardBuilder(Bukkit.getScoreboardManager().newScoreboard, "Weaboo Wars", "dummy") {

    private var topScore = 0
    private var kills = 0
    private var kit = "None"

    private fun setTopScore(topScore: Int) {
        this.topScore = topScore
    }

    private fun setKills(kills: Int) {
        this.kills = kills
    }

    private fun setKit(kitName: String) {
        kit = kitName
    }

    override fun revalidate() {
        update(5, "Top Score: ${ChatColor.GREEN}$topScore")
        update(3, "Kills: ${ChatColor.GREEN}$kills")
        update(1, "Kit: ${ChatColor.LIGHT_PURPLE}$kit")
    }

    override fun build() {
        setDisplaySlot(DisplaySlot.SIDEBAR)
        setDisplayName("${ChatColor.YELLOW}WEABOO WARS")
        append()
        append("Top Score: ${ChatColor.GREEN}$topScore")
        append()
        append("Kills: ${ChatColor.GREEN}$kills")
        append()
        append("Kit: ${ChatColor.LIGHT_PURPLE}$kit")
    }
}
