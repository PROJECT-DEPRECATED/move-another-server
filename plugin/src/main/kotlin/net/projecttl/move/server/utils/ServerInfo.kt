package net.projecttl.move.server.utils

import org.bukkit.ChatColor
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.Plugin

data class ServerInfo(val server_id: String, val display_name: String, val description: String)

class ServerListUtil(private val plugin: Plugin) {
    fun addServer(serverInfo: ServerInfo): String {
        return if (!plugin.config.contains("spot.${serverInfo.server_id}")) {
            plugin.config.set("spot.${serverInfo.server_id}.server_id", serverInfo.server_id)
            plugin.config.set("spot.${serverInfo.server_id}.display_name", serverInfo.display_name)
            plugin.config.set("spot.${serverInfo.server_id}.description", serverInfo.description)
            "[MoveServer] ${ChatColor.GREEN}${serverInfo.server_id} is successful added."
        } else {
            "[MoveServer] ${ChatColor.RED}${serverInfo.server_id} is already exist."
        }
    }

    fun removeServer(server_id: String): String {
        return if (!plugin.config.contains("spot.${server_id}")) {
            "[MoveServer] ${ChatColor.RED} $server_id is not exist."
        } else {
            plugin.config.set("spot.${server_id}", null)
            "[MoveServer] ${ChatColor.GREEN} $server_id is successful removed."
        }
    }

    fun getServerList(server_id: String): String {
        val spot = "spot.$server_id"

        val getId = plugin.config.getString("$spot.server_id")
        val getDisplayName = plugin.config.getString("$spot.display_name")
        val getDescription = plugin.config.getString("$spot.description")

        return "server_id: $getId\nserver_name: $getDisplayName\ndescription: $getDescription"
    }
}