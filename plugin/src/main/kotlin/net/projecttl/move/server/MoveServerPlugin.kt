package net.projecttl.move.server

import org.bukkit.plugin.java.JavaPlugin
import net.projecttl.api.move.server.api
import net.projecttl.move.server.commands.MoveCommand
import net.projecttl.move.server.listener.MoveItemListener
import net.projecttl.move.server.listener.PlayerJoinListener
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.entity.Player
import java.io.File

class MoveServerPlugin : JavaPlugin() {
    companion object {
        var instance: MoveServerPlugin? = null
        var spotList: MutableList<String> = instance?.config!!.getStringList("list")
    }

    private var playerFile: File? = null
    private var configuration: FileConfiguration? = null

    override fun onEnable() {
        instance = this
        loadPlayerList()
        server.pluginManager.apply {
            registerEvents(MoveItemListener(this@MoveServerPlugin), this@MoveServerPlugin)
            registerEvents(PlayerJoinListener(this@MoveServerPlugin), this@MoveServerPlugin)
        }

        getCommand("mserver")?.setExecutor(MoveCommand(this))
    }

    override fun onDisable() {
        saveConfig()
        savePlayerList()
    }

    private fun loadPlayerList() {
        playerFile = File(dataFolder, "player-list.yml").also {
            if (!it.exists()) {
                configuration?.save(it)
            }
            configuration = YamlConfiguration.loadConfiguration(it)
        }
    }

    private fun savePlayerList() {
        configuration?.save(playerFile!!)
    }

    fun playerList(): FileConfiguration {
        return configuration!!
    }
}