package net.projecttl.move.server.utils

import net.kyori.adventure.text.Component
import net.projecttl.api.move.server.api
import net.projecttl.move.server.MoveServerPlugin
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.projecttl.api.inventorygui.InventoryBuilder
import org.projecttl.api.inventorygui.utils.InventoryType
import java.util.*

class MoveServerGUI(private val plugin: MoveServerPlugin) {

    fun openInventory(player: Player) {
        player.openInventory(
            InventoryBuilder(Component.text("${ChatColor.GREEN}Server List"), InventoryType.CHEST_27).apply {
                for ((i, j) in plugin.spotList.withIndex()) {
                    val servers = ItemStack(Material.GRASS_BLOCK).let {
                        val itemMeta = it.itemMeta
                        itemMeta?.setDisplayName("${ChatColor.GREEN}${plugin.spotList[i]}")

                        it.itemMeta = itemMeta

                        it
                    }

                    setItem(i, servers)
                    registerListener(i) {
                        plugin.api(plugin.spotList[i], player)
                        it.isCancelled = true
                    }
                }

                val exitItem = ItemStack(Material.BARRIER).let {
                    val itemMeta = it.itemMeta
                    itemMeta?.setDisplayName("${ChatColor.RED}Exit")

                    it.itemMeta = itemMeta
                    it
                }
                setItem(26, exitItem)
                registerListener(26) {
                    player.closeInventory()
                }
            }.build()
        )
    }
}