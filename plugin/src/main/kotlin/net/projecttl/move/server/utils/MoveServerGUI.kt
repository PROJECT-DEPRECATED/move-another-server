package net.projecttl.move.server.utils

import net.kyori.adventure.text.Component
import net.projecttl.api.move.server.api
import net.projecttl.inventory.gui.gui
import net.projecttl.inventory.gui.utils.InventoryType
import net.projecttl.move.server.MoveServerPlugin
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class MoveServerGUI(private val plugin: MoveServerPlugin) {

    fun openInventory(player: Player) {
        player.openInventory(
            gui(InventoryType.CHEST_27, Component.text("${ChatColor.GREEN}Server List"), plugin) {
                val exitItem = ItemStack(Material.BARRIER).let {
                    val itemMeta = it.itemMeta
                    itemMeta?.setDisplayName("${ChatColor.RED}Exit")

                    it.itemMeta = itemMeta
                    it
                }

                slot(26, exitItem) {
                    player.closeInventory()
                }

                for ((i, j) in MoveServerPlugin.spotList.withIndex()) {
                    val servers = ItemStack(Material.GRASS_BLOCK).let {
                        val itemMeta = it.itemMeta
                        itemMeta?.setDisplayName("${ChatColor.GREEN}${j[i]}")

                        it.itemMeta = itemMeta

                        it
                    }

                    slot(i, servers) {
                        plugin.api(j[i].toString(), player)
                        this.isCancelled = true
                    }
                }
            }
        )
    }
}