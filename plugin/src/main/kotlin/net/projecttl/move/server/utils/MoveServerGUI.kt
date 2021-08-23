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
        val spotList = plugin.spotList
        player.openInventory(plugin.gui(InventoryType.CHEST_27, Component.text("Move ServerGUI")) {
            var j = 0
            for (i in spotList) {
                slot(j, ItemStack(Material.GRASS_BLOCK)) {
                    plugin.api(spotList[j], player)
                    isCancelled = true

                    j++
                }
            }
        })
    }
}