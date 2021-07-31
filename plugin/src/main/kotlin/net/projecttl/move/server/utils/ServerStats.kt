package net.projecttl.move.server.utils

import io.github.leonardosnt.bungeechannelapi.BungeeChannelApi
import org.bukkit.plugin.Plugin

class ServerStats(private val plugin: Plugin, private val serverId: String) {

    private val api = BungeeChannelApi.of(plugin)

    fun getCount(): Int {
        var getCounts = 0
        api.getPlayerCount(serverId).whenComplete { result: Int, _: Throwable? ->
            getCounts = result
        }

        return getCounts
    }
}