package lib.messages.titletext

import net.minecraft.server.v1_8_R3.IChatBaseComponent
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer

class TitleText(private val title: String?,
                private val subTitle: String?,
                private val fadeInTicks: Int,
                private val stayOnScreenTicks: Int,
                private val fadeOutTicks: Int) {

    fun sendTitle(player: CraftPlayer) {
        if(title != null)
            sendTitlePacket(player, title, PacketPlayOutTitle.EnumTitleAction.TITLE)
    }

    fun sendSubTitle(player: CraftPlayer) {
        if(subTitle != null)
            sendTitlePacket(player, subTitle, PacketPlayOutTitle.EnumTitleAction.SUBTITLE)
    }

    private fun sendTitlePacket(player: CraftPlayer, text: String, titleAction: PacketPlayOutTitle.EnumTitleAction) {
        val titlePacket = PacketPlayOutTitle(titleAction, IChatBaseComponent.ChatSerializer.a(text), fadeInTicks, stayOnScreenTicks, fadeOutTicks)
        val playerConnection = player.handle.playerConnection
        playerConnection.sendPacket(titlePacket)
    }
}