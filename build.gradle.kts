
import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

buildscript {
    repositories {
        mavenCentral()
        maven(url = "https://repo.spring.io/snapshot")
        maven(url = "https://repo.spring.io/milestone")
    }
}

plugins {
    val kotlinVersion = "1.3.61"

    id("io.spring.dependency-management") version "1.0.9.RELEASE"
    id("org.springframework.boot") version "2.2.3.RELEASE"
    id("com.github.ben-manes.versions") version "0.27.0"
    kotlin("jvm") version kotlinVersion apply false
    kotlin("plugin.spring") version kotlinVersion apply false
}

allprojects {

    val javaVersion = "1.8"

    group = "com.dustinsand"
    version = "1.0-SNAPSHOT"

    tasks.withType<JavaCompile> {
        sourceCompatibility = javaVersion
        targetCompatibility = javaVersion
    }

    tasks.withType<Test> {
        useJUnitPlatform()
        testLogging {
            showExceptions = true
            showStandardStreams = true
            events(org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED, org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED, org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED)
        }
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions {
            freeCompilerArgs += "-Xjsr305=strict"
            jvmTarget = javaVersion
        }
    }

    tasks.withType<DependencyUpdatesTask> {
        // optional parameters
        checkForGradleUpdate = true
        outputFormatter = "json"
        outputDir = "build/dependencyUpdates"
        revision = "release"
        reportfileName = "report"
    }
}

subprojects {

    //    apply(plugin = "kotlin")
//    apply(plugin = "org.springframework.boot")
//    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "io.spring.dependency-management")
//    the<io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension>().apply {
//        imports {
//            val springBootVersion: String by project
//            mavenBom("org.springframework.boot:spring-boot-dependencies:$springBootVersion")
//        }
//    }

    repositories {
        mavenCentral()
    }
}
