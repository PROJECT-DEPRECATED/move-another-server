package net.projecttl.move.server.utils

import net.kyori.adventure.text.Component
import net.md_5.bungee.api.ChatColor
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
            InventoryBuilder(Component.text("${ChatColor.GREEN}SERVER SELECTOR"), InventoryType.CHEST_27).apply {
                for ((i, j) in plugin.config.getStringList("spot").withIndex()) {
                    val getServerId = plugin.config.getString("spot.${j[i]}.server_id")
                    val getDisplayName = plugin.config.getString("spot.${j[i]}.display_name")
                    val getDescription = plugin.config.getString("spot.${j[i]}.description")

                    val getCount = ServerStats(plugin, getServerId.toString()).getCount()
                    var printMessage = ""

                    printMessage += if (getCount >= 1) {
                        "Server stats: ${ChatColor.GREEN}$getCount ${ChatColor.RESET}Peoples"
                    } else {
                        "Server stats: ${ChatColor.GREEN}$getCount ${ChatColor.RESET}People"
                    }

                    val servers = ItemStack(Material.GRASS_BLOCK).let {
                        val itemMeta = it.itemMeta
                        itemMeta?.setDisplayName(getDisplayName)
                        itemMeta?.lore = mutableListOf(getDescription, printMessage)
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