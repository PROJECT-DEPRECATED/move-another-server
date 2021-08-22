package net.projecttl.api.move.server.utils

import org.bukkit.entity.Player

class ServerUtil(private val server: Server, private val player: Player) {

    private val statement = InitSQLDriver.sqlConnection.createStatement()

    fun query() {
        val sql = "select * from ${InitSQLDriver.database}.${InitSQLDriver.table}"
        val result = statement.executeQuery(sql)

        while (result.next()) {
            player.sendMessage("${result.getInt("id")}: " +
                    "server_name -> ${result.getString("server_name")}" +
                    "display_name -> ${result.getString("display_name")}")
        }
    }

    fun queryServerList(): MutableList<String> {
        val serverList = mutableListOf<String>()
        val sql = "select * from ${InitSQLDriver.database}.${InitSQLDriver.table}"
        val result = statement.executeQuery(sql)

        while (result.next()) {
            serverList.add(result.getString("server_name"))
        }

        return serverList
    }

    fun queryDisplayList(): MutableList<String> {
        val displayList = mutableListOf<String>()
        val sql = "select * from ${InitSQLDriver.database}.${InitSQLDriver.table}"
        val result = statement.executeQuery(sql)

        while (result.next()) {
            displayList.add(result.getString("display_name"))
        }

        return displayList
    }
}