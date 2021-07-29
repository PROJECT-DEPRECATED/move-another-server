package net.projecttl.api.move.server.utils

import io.github.leonardosnt.bungeechannelapi.BungeeChannelApi
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin

class MoveUtil(private val plugin: Plugin) {
    fun move(player: Player, serverName: String) {
        player.sendMessage("[MoveServer] ${ChatColor.GREEN}Move to server for '$serverName'")

        val channelAPI = BungeeChannelApi.of(plugin)
        channelAPI.connect(player, serverName)
    }
}