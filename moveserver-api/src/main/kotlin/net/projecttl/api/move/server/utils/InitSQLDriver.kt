package net.projecttl.api.move.server.utils

import org.bukkit.plugin.Plugin
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import java.sql.Statement

class InitSQLDriver(private val plugin: Plugin) {
    companion object {
        lateinit var sqlConnection: Connection

        var database: String? = null
        var table: String? = null
    }

    private val inlineDatabase = plugin.config.getString("DATABASE")
    private val inlineTable = plugin.config.getString("TABLE")

    init {
        database = inlineDatabase
        table = inlineTable
    }

    fun loadSQLModule() {
        val logger = plugin.logger
        val url = plugin.config.getString("SQL_IP")
        val username = plugin.config.getString("SQL_USERNAME")
        val password = plugin.config.getString("SQL_PASSWORD")
        val port = plugin.config.getInt("SQL_PORT")

        logger.info("Loading driver...")

        Class.forName("com.mysql.cj.jdbc.Driver")
        plugin.logger.info("Connecting to SQL...")

        try {
            sqlConnection = DriverManager.getConnection("jdbc:mysql://${url}:${port}/", username, password)
            logger.info("Connected to ${url}:${port}")
        } catch (exception: SQLException) {
            exception.printStackTrace()
        } catch (exception: Exception) {
            exception.printStackTrace()
        }


        val statement: Statement = sqlConnection.createStatement()
        statement.executeUpdate("create database if not exists $database default character set utf8;")
        statement.executeUpdate("use ${database};")
        statement.executeUpdate(
            "create table if not exists ${table}(" +
                    "`id` int auto_increment," +
                    "`server_name` varchar(25)  not null," +
                    "`display_name` int not null," +
                    "primary key (id)," +
                    "unique index (server_name)" +
                    ");"
        )
    }

    fun closeConnection() {
        try {
            if (!sqlConnection.isClosed) {
                sqlConnection.close()
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }
}