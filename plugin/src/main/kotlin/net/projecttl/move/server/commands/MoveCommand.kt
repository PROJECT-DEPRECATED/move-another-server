package net.projecttl.move.server.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class MoveCommand: CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender is Player) {
            if (command.name == "move-server") {
                when (args.size) {
                    0 -> {
                        
                    }
                }
            }
        }

        return false
    }
}