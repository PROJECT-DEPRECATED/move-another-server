package net.projecttl.move.server.commands

import net.projecttl.move.server.MoveServerPlugin
import net.projecttl.move.server.utils.MoveServerGUI
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
                        MoveServerGUI(plugin).openInventory(sender)
                    }
                }
            }
        }

        return false
    }
}