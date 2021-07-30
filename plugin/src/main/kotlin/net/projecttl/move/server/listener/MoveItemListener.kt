package net.projecttl.move.server.listener

import net.projecttl.move.server.MoveServerPlugin
import net.projecttl.move.server.utils.MoveServerGUI
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack

class MoveItemListener(private val plugin: MoveServerPlugin): Listener {

    @EventHandler
    fun onClickEvent(event: PlayerInteractEvent) {
        val player = event.player
        if (player.inventory.itemInMainHand != ItemStack(Material.CLOCK)) {
            return
        }

        MoveServerGUI(plugin).openInventory(player)
    }
}