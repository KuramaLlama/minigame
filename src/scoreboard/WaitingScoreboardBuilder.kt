package scoreboard

import lib.messages.scoreboard.ScoreboardBuilder
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.scoreboard.DisplaySlot

class WaitingScoreboardBuilder : ScoreboardBuilder(Bukkit.getScoreboardManager().newScoreboard, "Weaboo Wars", "dummy") {

    private var status = "Waiting..."
    private var playerCount = 0
    private var kit = "None"

    fun setStatus(status: String) {
        this.status = status
    }

    fun setPlayerCount(playerCount: Int) {
        this.playerCount = playerCount
    }

    fun setKitSelected(kitName: String) {
        kit = kitName
    }

    override fun revalidate() {
        update(1, "Kit: ${ChatColor.LIGHT_PURPLE}$kit")
        update(3, "Players: ${ChatColor.GREEN}$playerCount${ChatColor.WHITE}/8")
        update(5, "Status: ${ChatColor.GREEN}$status")
    }

    override fun build() {
        setDisplaySlot(DisplaySlot.SIDEBAR)
        setDisplayName("${ChatColor.YELLOW}WEABOO WARS")
        append()
        append("Status: ${ChatColor.GREEN}$status")
        append()
        append("Players: ${ChatColor.GREEN}$playerCount${ChatColor.WHITE}/8")
        append()
        append("Kit: ${ChatColor.LIGHT_PURPLE}$kit")
    }
}
