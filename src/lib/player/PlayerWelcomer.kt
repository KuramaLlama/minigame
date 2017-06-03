package lib.player

import lib.messages.titletext.TitleText
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer
import org.bukkit.entity.Player

class PlayerWelcomer(private val welcomeTitle: TitleText) {
    fun welcomePlayer(player: Player) {
        if(player is CraftPlayer) {
            welcomeTitle.sendTitle(player)
            welcomeTitle.sendSubTitle(player)
        }
    }
}