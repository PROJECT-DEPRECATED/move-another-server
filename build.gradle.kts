plugins {
    kotlin("jvm") version "1.5.21"
}

group = properties["pluginGroup"]!!
version = properties["pluginVersion"]!!

var type = properties["BukkitAPI"]!!

allprojects {
    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")

    repositories {
        google()
        maven("https://jitpack.io")
        maven("https://oss.sonatype.org/content/repositories/snapshots")
        maven("https://repo.codemc.org/repository/maven-public/")
        // maven("https://repo.dmulloy2.net/repository/public/") ProtocolLib

        when (type) {
            "paper" -> {
                maven("https://papermc.io/repo/repository/maven-public/")
                maven(url = "https://oss.sonatype.org/content/repositories/snapshots/") { // Adventure API
                    name = "sonatype-oss-snapshots"
                }
            }

            "spigot" -> {
                maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
            }
        }
    }

    dependencies {
        implementation(kotlin("stdlib"))
        implementation("com.google.code.gson:gson:2.8.7")
        // implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.0") This is coroutines code
        when (type) {
            "paper" -> {
                implementation("net.kyori:adventure-api:4.7.0")
                compileOnly("com.destroystokyo.paper:paper-api:1.17.1-R0.1-SNAPSHOT")
            }

            "spigot" -> {
                implementation("org.spigotmc:spigot-api:1.17.1-R0.1-SNAPSHOT")
            }
        }

        implementation("net.md-5:bungeecord-api:1.17-R0.1-SNAPSHOT")
        implementation("io.github.leonardosnt:bungeechannelapi:1.0.0-SNAPSHOT")
        // compileOnly("com.comphenix.protocol:ProtocolLib:4.6.0") ProtocolLib

        compileOnly("com.github.ProjectTL12345:InventoryGUI:3.1.1")
    }
}