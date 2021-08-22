package net.projecttl.move.server.listener

import net.citizensnpcs.api.event.NPCRightClickEvent
import net.projecttl.api.move.server.api
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.plugin.Plugin

class NpcInteractionListener(private val plugin: Plugin): Listener {

    @EventHandler
    fun clickNpc(event: NPCRightClickEvent) {
        val player = event.clicker
        val npc = event.npc

        if (npc.name == "") {
            plugin.api("", player)
        }
    }
}