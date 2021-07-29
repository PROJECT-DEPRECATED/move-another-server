package org.example.explugin

import org.bukkit.plugin.java.JavaPlugin
import org.example.api.explugin.api

class ExPlugin : JavaPlugin() {

    override fun onEnable() {
        super.onEnable()
        api()
    }

    override fun onDisable() {
        super.onDisable()
    }
}