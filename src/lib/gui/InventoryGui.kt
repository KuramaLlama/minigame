package lib.gui

import org.bukkit.Bukkit
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import java.util.*

open class InventoryGui(private var title: String){
    private val itemList: MutableList<ItemStack> = ArrayList<ItemStack>()

    private var rows = 3

    fun addItemIcon(vararg itemIcon: ItemStack) = itemIcon.forEach { itemIcon -> itemList.add(itemIcon) }

    fun build() : Inventory{
        rows = calculateRows()
        val inventoryGui = Bukkit.createInventory(null, 9*rows, title)
        addItemsToGui(inventoryGui)
        return inventoryGui
    }


    private fun calculateRows() : Int = Math.ceil(itemList.size.toDouble() / 7.0).toInt() + 2

    private fun addItemsToGui(inventory: Inventory) {
        var rowNumb = 1
        var placementIndex = rowStartIndex(if(itemList.size >= 7) 7 else itemList.size, rowNumb)

        itemList.forEach { item ->
            inventory.setItem(placementIndex, item)

            if ((placementIndex - 7) % 9 == 0)
                placementIndex = rowStartIndex(numbItemsInRow(++rowNumb), rowNumb)
            else
                placementIndex++
        }
    }

    private fun numbItemsInRow(rowNumb: Int): Int {
        if(rowNumb * 7 < itemList.size)
            return 7
        else
            return 7 - ((rowNumb*7) - itemList.size)
    }

    private fun rowStartIndex(rowSize: Int, rowNumb: Int) : Int {
        var size = rowSize

        if(size == 0 || size == 1)
            return 4 + (rowNumb * 9)
        else if((size%2) != 0)
            size -= 1

        return Math.ceil(5.0/size.toDouble()).toInt() + (rowNumb * 9)
    }
}
