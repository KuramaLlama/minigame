package kits

import lib.utils.ItemUtils
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemStack

enum class KitsEnum(private val kit: Kit) {
    JUGGERNAUT(Kit(ItemStack(Material.STONE_SWORD), "Juggernaut",
            arrayOf(
                    ItemUtils().addEnchantment(ItemStack(Material.STONE_SWORD), Enchantment.KNOCKBACK, 2),
                    ItemStack(Material.DIAMOND_CHESTPLATE)
                    ))),

    CHARGER(Kit(ItemStack(Material.STONE_AXE), "Charger",
            arrayOf(
                    ItemUtils().addEnchantment(ItemStack(Material.STONE_AXE), Enchantment.DAMAGE_ALL, 2)
            ))),

    BRUTE(Kit(ItemStack(Material.FISHING_ROD), "Brute",
            arrayOf(
                    ItemStack(Material.FISHING_ROD)
            ))),

    ARCHER(Kit(ItemStack(Material.BOW), "Archer",
            arrayOf(
                    ItemUtils().addEnchantment(ItemStack(Material.BOW), Enchantment.ARROW_INFINITE, 1),
                    ItemStack(Material.ARROW, 1)
            ))),

    CASTER(Kit(ItemStack(Material.IRON_HOE), "Caster",
            arrayOf(
                    ItemUtils().addEnchantment(ItemStack(Material.IRON_HOE), Enchantment.FIRE_ASPECT, 1)
            )));

    fun kit() : Kit {
        return kit
    }
}