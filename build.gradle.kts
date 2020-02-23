
import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        jcenter()
        mavenCentral()
        maven(url = "https://repo.spring.io/snapshot")
        maven(url = "https://repo.spring.io/milestone")
        maven(url = "https://plugins.gradle.org/m2/")
    }
    dependencies {
        classpath("org.jmailen.gradle:kotlinter-gradle:2.3.0")
    }
}

plugins {
    val kotlinVersion = "1.3.61"

    base
    java
    id("org.springframework.boot") version "2.2.4.RELEASE"
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

    tasks.withType<KotlinCompile>().configureEach {
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
    val assertjVersion: String by project
    val h2Version: String by project
    val junitJupiterVersion: String by project
    val projectReactorVersion: String by project
    val restAssuredVersion: String by project
    val junitPlatformRunnerVersion: String by project
    val jacksonModuleKotlin: String by project
    val springBootVersion: String by project

    apply(plugin = "java")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.jmailen.kotlinter")

    repositories {
        jcenter()
        mavenCentral()
    }

    // TODO move dependencies to where actually used in subproject

    dependencies {
        implementation("org.springframework.boot:spring-boot-starter-jooq:$springBootVersion")
        implementation("org.springframework.boot:spring-boot-starter-webflux:$springBootVersion")
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonModuleKotlin")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        testImplementation("org.springframework.boot:spring-boot-starter-test:$springBootVersion") {
            exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
        }
        testImplementation("io.projectreactor:reactor-test:$projectReactorVersion")
        testImplementation("io.rest-assured:rest-assured:$restAssuredVersion")
        testImplementation("io.rest-assured:json-path:$restAssuredVersion")
        testImplementation("io.rest-assured:xml-path:$restAssuredVersion")
        testImplementation("io.rest-assured:json-schema-validator:$restAssuredVersion")
        testImplementation("io.rest-assured:spring-mock-mvc:$restAssuredVersion")
        testImplementation("io.rest-assured:kotlin-extensions:$restAssuredVersion")
        testImplementation("org.junit.jupiter:junit-jupiter-api:$junitJupiterVersion")
        testImplementation("org.junit.jupiter:junit-jupiter-engine:$junitJupiterVersion")
        testImplementation("org.junit.jupiter:junit-jupiter-params:$junitJupiterVersion")
        testImplementation("org.junit.platform:junit-platform-runner:$junitPlatformRunnerVersion")
        testImplementation("org.assertj:assertj-core:$assertjVersion")
        implementation("com.h2database:h2:$h2Version")
    }
}
