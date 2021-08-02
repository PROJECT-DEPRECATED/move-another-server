package net.projecttl.move.server.utils

import net.projecttl.move.server.MoveServerPlugin
import org.bukkit.ChatColor

class ServerListUtil(private val plugin: MoveServerPlugin) {

    fun addServer(spotName: String): String {
        return if (!MoveServerPlugin.spotList.contains(spotName)) {
            MoveServerPlugin.spotList.add(spotName)
            plugin.config.set("list", MoveServerPlugin.spotList)

            "[MoveServer] ${ChatColor.GREEN}$spotName is successful added"
        } else {
            "[MoveServer] ${ChatColor.RED}$spotName is already exist"
        }
    }

    fun removeServer(spotName: String): String {
        return if (MoveServerPlugin.spotList.contains(spotName)) {
            MoveServerPlugin.spotList.remove(spotName)
            plugin.config.set("list", MoveServerPlugin.spotList)

            "[MoveServer] ${ChatColor.GREEN}$spotName is successful removed"
        } else {
            "[MoveServer] ${ChatColor.RED}$spotName is not exist"
        }
    }

    fun listServer(): MutableList<String> {
        return MoveServerPlugin.spotList
    }
}