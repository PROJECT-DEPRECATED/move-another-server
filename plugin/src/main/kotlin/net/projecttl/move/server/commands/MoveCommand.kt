package net.projecttl.move.server.commands

import net.projecttl.move.server.MoveServerPlugin
import net.projecttl.move.server.utils.MoveServerGUI
import net.projecttl.move.server.utils.ServerInfo
import net.projecttl.move.server.utils.ServerListUtil
import org.bukkit.Material
import org.bukkit.Server
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class MoveCommand(private val plugin: MoveServerPlugin): CommandExecutor, TabCompleter {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender is Player) {
            if (command.name == "move-server") {
                when {
                    args.isEmpty() -> {
                        return if (plugin.spotList.isNotEmpty()) {
                            MoveServerGUI(plugin).openInventory(sender)
                            true
                        } else {
                            sender.sendMessage("[MoveServer] Server list is none")
                            true
                        }
                    }

                    args.isNotEmpty() -> {
                        when (args[0]) {
                            "add" -> {
                                val serverId = args[1]
                                val serverName = args[2]
                                val description = args[3]

                                val serverInfo = ServerInfo(serverId, serverName, description)

                                val runResult = ServerListUtil(plugin).addServer(serverInfo)
                                sender.sendMessage(runResult)
                            }
                            "remove" -> {
                                val serverId = args[1]

                                val runResult = ServerListUtil(plugin).removeServer(serverId)
                                sender.sendMessage(runResult)
                            }
                            "list" -> {
                                val serverId = args[1]
                                val runResult = ServerListUtil(plugin).getServerList(serverId)
                                sender.sendMessage(runResult)
                            }
                        }


                        return true
                    }
                }
            }
        }

        return false
    }

    override fun onTabComplete(sender: CommandSender, command: Command, alias: String, args: Array<out String>): MutableList<String>? {
        if (sender is Player) {
            when (args.size) {
                1 -> {
                    val name = mutableListOf<String>()
                    name.add("add")
                    name.add("remove")
                    name.add("list")

                    return name
                }
            }
        }
        return null
    }
}