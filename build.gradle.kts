plugins {
    kotlin("jvm") version "2.0.21"
    application
}

group = "ro.tuiasi.pp"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    testImplementation(kotlin("test-junit5"))
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.10.2")
}

application {
    mainClass.set("ro.tuiasi.pp.lab7.MainKt")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}
