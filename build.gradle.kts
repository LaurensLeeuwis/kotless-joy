import io.kotless.plugin.gradle.dsl.kotless
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.serialization") version "1.6.10"
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
    implementation(group = "org.jetbrains.kotlinx", name = "kotlinx-serialization-json", version = "1.4.1")
    implementation(group = "org.jetbrains.kotlinx", name = "kotlinx-html-jvm", version = "0.8.0")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}

// import io.kotless.plugin.gradle.dsl.kotless
kotless {
    config {
        aws {
            storage {
                bucket = "kotless-rainy-day-s3-bucket"
            }

            prefix = "rainy"

            profile = "kotless-rainy-day-user"
            region = "eu-central-1"
        }
    }
    webapp {
        lambda {
            kotless {
                packages = setOf("nl.joyofcoding.kotless")
            }
        }
    }
}
