import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.10"
    id("io.kotless") version "0.2.0"
}

group = "nl.joyofcoding"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven(url = uri("https://packages.jetbrains.team/maven/p/ktls/maven"))
}

dependencies {
    testImplementation(kotlin("test"))
    implementation(group = "io.kotless", name = "kotless-lang", version = "0.2.0")
    implementation(group = "io.kotless", name = "kotless-lang-aws", version = "0.2.0")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}
