package net.projecttl.api.move.server

import net.projecttl.api.move.server.utils.MoveUtil
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin

fun Plugin.api(serverName: String, player: Player): MoveServerAPI {
    return MoveServerAPI(this, serverName, player)
}

class MoveServerAPI(private val plugin: Plugin, serverName: String, player: Player) {
    init {
        MoveUtil(plugin).move(player, serverName)
    }
}