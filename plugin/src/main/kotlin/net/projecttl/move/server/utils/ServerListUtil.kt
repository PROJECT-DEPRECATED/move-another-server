package net.projecttl.move.server.utils

import net.projecttl.move.server.MoveServerPlugin
import org.bukkit.ChatColor

class ServerListUtil(private val plugin: MoveServerPlugin) {

    fun addSpot(spotName: String): String {
        return if (!plugin.spotList.contains(spotName)) {
            plugin.spotList.add(spotName)
            plugin.config.set("spot", plugin.spotList)

            "[MoveServer] ${ChatColor.GREEN}$spotName is successful added"
        } else {
            "[MoveServer] ${ChatColor.RED}$spotName is already exist"
        }
    }

    fun removeSpot(spotName: String): String {
        return if (plugin.spotList.contains(spotName)) {
            plugin.spotList.remove(spotName)
            plugin.config.set("spot", plugin.spotList)

            "[MoveServer] ${ChatColor.GREEN}$spotName is successful removed"
        } else {
            "[MoveServer] ${ChatColor.RED}$spotName is not exist"
        }
    }

    fun getSpot(): MutableList<String> {
        return plugin.spotList
    }
}