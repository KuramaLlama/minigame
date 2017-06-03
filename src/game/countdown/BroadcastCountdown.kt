package game.countdown

import lib.messages.titletext.TitleText
import lib.timers.TimeRunnable
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Sound
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer

class BroadcastCountdown: TimeRunnable {

    override fun run(ticks: Long) {
        val seconds = (ticks / 20).toInt()
        when {
            seconds == 30 || seconds == 10 || seconds <= 5 -> broadcastTime((ticks / 20).toInt())
        }
    }

    private fun broadcastTime(time: Int) {
        val countdownTitle = TitleText("{\"text\":\"$time\",\"color\":\"red\"}",
                "{\"text\":\"Prepare to fight!\",\"color\":\"gold\"}", 0, 20, 0)
        val message = "${ChatColor.YELLOW}Game starts in ${ChatColor.GREEN}$time ${ChatColor.YELLOW}seconds!"

        Bukkit.broadcastMessage(message)
        Bukkit.getOnlinePlayers().forEach { player ->
            countdownTitle.sendTitle(player as CraftPlayer)
            countdownTitle.sendSubTitle(player)
            player.playSound(player.location, Sound.NOTE_STICKS, 10F, 10F)
        }
    }
}