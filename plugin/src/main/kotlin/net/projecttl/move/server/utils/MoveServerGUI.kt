package net.projecttl.move.server.utils

import net.kyori.adventure.text.Component
import net.projecttl.api.move.server.api
import net.projecttl.move.server.MoveServerPlugin
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.projecttl.api.inventorygui.InventoryBuilder
import org.projecttl.api.inventorygui.utils.InventoryType

class MoveServerGUI(private val plugin: MoveServerPlugin) {

    fun openInventory(player: Player) {
        player.openInventory(
            InventoryBuilder(Component.text("Inventory"), InventoryType.CHEST_27).apply {
                for ((i, j) in plugin.spotList.withIndex()) {
                    val servers = ItemStack(Material.GRASS_BLOCK).let {
                        val itemMeta = it.itemMeta
                        itemMeta?.setDisplayName(plugin.spotList[i])
                        it.itemMeta = itemMeta

                        it
                    }
                    setItem(i, servers)
                    registerListener(i) {
                        plugin.api(plugin.spotList[i], player)
                        it.isCancelled = true
                    }
                }
            }.build()
        )
    }
}