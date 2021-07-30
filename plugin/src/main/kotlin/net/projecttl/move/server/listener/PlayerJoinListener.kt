package net.projecttl.move.server.listener

import net.projecttl.move.server.MoveServerPlugin
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.inventory.ItemStack

class PlayerJoinListener(private val plugin: MoveServerPlugin): Listener {

    @EventHandler
    fun join(event: PlayerJoinEvent) {
        val player = event.player
        if (!plugin.playerList().getStringList("list").contains(player.name)) {
            val mutableList: MutableList<String> = plugin.playerList().getStringList("list")
            mutableList.add(player.name)

            player.inventory.addItem(ItemStack(Material.CLOCK))
            player.sendMessage("[MoveServer] If you right click this clock, You'll open server list.")
            plugin.playerList().set("list", mutableList)
        }
    }
}