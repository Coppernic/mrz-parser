plugins {
    val kotlinVersion = "1.5.31"

    id("net.nemerosa.versioning") version "2.14.0"
    id("com.github.johnrengelman.shadow") version "6.1.0"
    id("jacoco")
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.serialization") version kotlinVersion
    id("org.jetbrains.dokka") version kotlinVersion
}

apply(from = rootProject.file("gradle/ktlint.gradle"))

repositories {
    jcenter()
    mavenCentral()
    mavenLocal()
}

versioning {
    releaseMode = "snapshot"
    displayMode = "full"
}

group = "fr.coppernic.lib"
version = versioning.info.display

jacoco {
    toolVersion = "0.8.3"
    reportsDir = file("$buildDir/reports/jacoco")
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.5.31")

    implementation("org.slf4j:slf4j-api:1.7.30")

    testImplementation("junit:junit:4.13.1")
    testImplementation("org.amshove.kluent:kluent:1.65")
    testImplementation("ch.qos.logback:logback-classic:1.2.6")
}

apply(from = rootProject.file("gradle/gradle-publish.gradle"))
