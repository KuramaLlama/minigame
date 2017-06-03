package gui.kit

import kits.KitsEnum
import lib.utils.ItemUtils
import org.bukkit.ChatColor
import org.bukkit.enchantments.Enchantment
import lib.gui.UneditableInventoryGui

class KitSelectorGui : UneditableInventoryGui("Kit Selector"){
    fun setKitIcons() {
        KitsEnum.values().forEach { kitEnum ->
            addItemIcon(ItemUtils().addEnchantment(ItemUtils().changeItemName(kitEnum.kit().getItemIcon(),
                    "${ChatColor.LIGHT_PURPLE}${kitEnum.kit().getName()}"), Enchantment.DURABILITY, 10))
        }
    }
}