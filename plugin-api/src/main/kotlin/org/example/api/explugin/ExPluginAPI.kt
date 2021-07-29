package org.example.api.explugin

import org.bukkit.plugin.Plugin

fun Plugin.api(): ExPluginAPI {
    return ExPluginAPI(this)
}

class ExPluginAPI(private val plugin: Plugin) {
    init {
        plugin.logger.info("Hello, API!")
    }
}