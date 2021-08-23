package net.projecttl.move.server.commands

import net.projecttl.move.server.MoveServerPlugin
import net.projecttl.move.server.utils.MoveServerGUI
import net.projecttl.move.server.utils.ServerListUtil
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player

class MoveCommand(private val plugin: MoveServerPlugin): CommandExecutor, TabCompleter {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender is Player) {
            if (command.name == "move-server") {
                when (args.size) {
                    0 -> {
                        MoveServerGUI(plugin).openInventory(sender)
                        return true
                    }

                    1 -> {
                        if (args[0] == "list") {
                            sender.sendMessage(ServerListUtil(plugin).listServer().toString())
                            return true
                        }
                    }

                    2 -> {
                        val serverName = args[1]
                        when (args[0]) {
                            "add" -> sender.sendMessage(ServerListUtil(plugin).addServer(serverName))
                            "remove" -> sender.sendMessage(ServerListUtil(plugin).removeServer(serverName))
                        }

                        return true
                    }
                }
            }
        }

        return false
    }

    override fun onTabComplete(sender: CommandSender, command: Command, alias: String, args: Array<out String>): MutableList<String>? {
        if (command.name == "move-server") {
            val commandList = mutableListOf<String>()
            if (args.size == 1) {
                commandList.add("add")
                commandList.add("remove")

                return commandList
            }
        }
        return null
    }
}