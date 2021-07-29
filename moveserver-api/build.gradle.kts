plugins {
    id("com.github.johnrengelman.shadow") version "7.0.0"
    `maven-publish`
}

group = rootProject.group
version = rootProject.version

repositories {
}

dependencies {
}

tasks {
    create<Jar>("sourcesJar") {
        archiveClassifier.set("sources")
        from(sourceSets["main"].allSource)
    }
}

// Add publish code with jitpack
publishing {
    publications {
        create<MavenPublication>(project.name) {
            artifact(tasks["sourcesJar"])
            from(components["java"])
        }
    }
}