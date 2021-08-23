plugins {
    id("com.github.johnrengelman.shadow") version "7.0.0"
    `maven-publish`
    signing
}

group = rootProject.group
version = rootProject.version

tasks {
    javadoc {
        options.encoding = "UTF-8"
    }

    create<Jar>("sourcesJar") {
        archiveClassifier.set("sources")
        from(sourceSets["main"].allSource)
    }

    create<Jar>("javadocJar") {
        archiveClassifier.set("javadoc")
        dependsOn("dokkaHtml")
        from("$buildDir/dokka/html")
    }
}

publishing {
    publications {
        create<MavenPublication>("${rootProject.name}-api") {
            from(components["java"])
            artifact(tasks["sourcesJar"])
            artifact(tasks["javadocJar"])

            repositories {
                maven {
                    name = "MavenCentral"
                    val releasesRepoUrl = "https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/"
                    val snapshotsRepoUrl = "https://s01.oss.sonatype.org/content/repositories/snapshots/"
                    url = uri(if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl)

                    credentials.runCatching {
                        val MAVEN_UPLOAD_USER: String = (project.properties["ossUserName"] as String?)!!
                        val MAVEN_UPLOAD_PWD: String = (project.properties["ossPassword"] as String?)!!
                        username = MAVEN_UPLOAD_USER
                        password = MAVEN_UPLOAD_PWD
                    }
                }

                pom {
                    name.set(rootProject.name)
                    description.set("This is minecraft server move plugin api")
                    url.set("https://github.com/ProjectTL12345/move-another-server")
                    licenses {
                        license {
                            name.set("GNU GENERAL PUBLIC LICENSE Version 3")
                            url.set("https://www.gnu.org/licenses/gpl-3.0.txt")
                        }
                    }
                    developers {
                        developer {
                            id.set("ProjectTL12345")
                            name.set("Project_TL")
                            email.set("me@projecttl.net")
                        }
                    }
                    scm {
                        connection.set("scm:git:https://github.com/ProjetTL12345/move-another-server.git")
                        developerConnection.set("scm:git:https://github.com/ProjetTL12345/move-another-server.git")
                        url.set("https://github.com/ProjetTL12345/move-another-server.git")
                    }
                }
            }
        }
    }
}

signing {
    isRequired = true
    sign(publishing.publications["${rootProject.name}-api"])
}