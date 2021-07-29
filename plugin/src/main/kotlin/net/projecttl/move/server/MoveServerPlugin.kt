package net.projecttl.move.server

import org.bukkit.plugin.java.JavaPlugin
import net.projecttl.api.move.server.api
import org.bukkit.entity.Player

class MoveServerPlugin : JavaPlugin() {

    fun loadModule(serverName: String, player: Player) {
        api(serverName, player)
    }

    override fun onEnable() {

    }

    override fun onDisable() {
        saveConfig()
    }
}