package lib.utils

import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemStack

class ItemUtils {

    fun addEnchantment(itemStack: ItemStack, enchantment: Enchantment, lvl: Int): ItemStack {
        val meta = itemStack.itemMeta
        meta.addEnchant(enchantment, lvl, true)
        itemStack.itemMeta = meta
        return itemStack
    }

    fun changeItemName(itemStack: ItemStack, name: String): ItemStack {
        val meta = itemStack.itemMeta
        meta.displayName = name
        itemStack.itemMeta = meta
        return itemStack
    }
}