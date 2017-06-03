package kits

import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import java.util.*

class KitAssigner(private val playerKitMap: HashMap<Player, Kit>) {

    fun assignPlayerKit(player: Player, kit: Kit) {
        if(playerKitMap.contains(player))
            playerKitMap[player] = kit
        else
            playerKitMap.put(player, kit)
    }

    fun getKitByIcon(itemIcon: ItemStack): Kit? {
        KitsEnum.values().forEach { kitEnum ->
            if(kitEnum.kit().getItemIcon().equals(itemIcon))
                return kitEnum.kit()
        }
        return null
    }
}
