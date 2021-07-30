package net.projecttl.move.server.commands

import net.projecttl.move.server.MoveServerPlugin
import net.projecttl.move.server.utils.MoveServerGUI
import net.projecttl.move.server.utils.ServerListUtil
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class MoveCommand(private val plugin: MoveServerPlugin): CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender is Player) {
            if (command.name == "move-server") {
                when (args.size) {
                    0 -> {
                        return if (plugin.spotList.isNotEmpty()) {
                            MoveServerGUI(plugin).openInventory(sender)
                            true
                        } else {
                            sender.sendMessage("[MoveServer] Server list is none")
                            true
                        }
                    }

                    2 -> {
                        val spotName = args[1]
                        when (args[0]) {
                            "add" -> ServerListUtil(plugin).addSpot(spotName)
                            "remove" -> ServerListUtil(plugin).removeSpot(spotName)
                            "list" -> {
                                ServerListUtil(plugin).getSpot()
                            }
                        }


                        return true
                    }
                }
            }
        }

        return false
    }
}