plugins {
    kotlin("jvm") version "1.9.23"
}

group = "com.github.nejer6"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0-RC")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(11)
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "com.github.nejer6.autodownloader.MainKt"
    }
}

tasks.register<Jar>("fatJar") {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    archiveClassifier.set("all")

    from(sourceSets.main.get().output)

    dependsOn(configurations.runtimeClasspath)
    from({
        configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) }
    })

    manifest {
        attributes["Main-Class"] = "com.github.nejer6.autodownloader.MainKt"
    }
}