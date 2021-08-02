plugins {
    id("com.github.johnrengelman.shadow") version "7.0.0"
}

group = rootProject.group
version = rootProject.version

val pluginMain = "net.projecttl.move.server.MoveServerPlugin"

repositories {
}

dependencies {
    implementation(project(":moveserver-api"))
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "16"
    }

    processResources {
        filesMatching("*.yml") {
            expand(project.properties)
        }
    }

    shadowJar {
        archiveBaseName.set(rootProject.name)
        archiveClassifier.set("")
        archiveVersion.set("")
    }
}