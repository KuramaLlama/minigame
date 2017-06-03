package kits

import lib.utils.ItemUtils
import org.bukkit.inventory.ItemStack

open class Kit(private val itemIcon: ItemStack,
               private val name: String,
               private val kitItems: Array<ItemStack>) {

    fun getItemIcon(): ItemStack = itemIcon

    fun getName(): String = name

    fun getKitItems(): Array<ItemStack> = kitItems

    fun setItemDefaultNames() {
        kitItems.forEach { kitItem ->
           ItemUtils().changeItemName(kitItem, "$name's ${kitItem.type.name}")
        }
    }
}
