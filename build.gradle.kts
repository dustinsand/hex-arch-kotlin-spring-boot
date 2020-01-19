import org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED
import org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED
import org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


val javaVersion = JavaVersion.VERSION_1_8
val kotlinVersion: String by project
val junitJupiterVersion: String by project
val restAssuredVersion: String by project
val junitPlatformRunnerVersion: String by project
val assertjVersion: String by project
val archunitVersion: String by project
extra["kotlin.version"] = kotlinVersion
extra["junit-jupiter.version"] = junitJupiterVersion

plugins {
    idea
    java
    kotlin("jvm") version "1.3.61"
    kotlin("plugin.spring") version "1.3.61"
    id("io.spring.dependency-management") version "1.0.8.RELEASE"
    id("org.springframework.boot") version "2.2.3.RELEASE"
    id("com.github.ben-manes.versions") version "0.27.0"
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "$javaVersion"
    }
}

java {
    sourceCompatibility = javaVersion
    targetCompatibility = javaVersion
}

sourceSets {
    main {
        java.srcDir("src/main/kotlin")
    }
    test {
        java.srcDir("src/test/kotlin")
    }
}

dependencyManagement {
    imports {
        val springBootVersion: String by project
        mavenBom("org.springframework.boot:spring-boot-dependencies:$springBootVersion")
    }
}

repositories {
    mavenCentral()
    maven(url = "https://repo.spring.io/snapshot")
    maven(url = "https://repo.spring.io/milestone")
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        showExceptions = true
        showStandardStreams = true
        events(PASSED, SKIPPED, FAILED)
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-jooq")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    runtimeOnly("com.h2database:h2")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
    testImplementation("io.projectreactor:reactor-test")
    testImplementation("io.rest-assured:rest-assured:$restAssuredVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitJupiterVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:$junitJupiterVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-params:$junitJupiterVersion")
    testImplementation("org.junit.platform:junit-platform-runner:$junitPlatformRunnerVersion")
    testImplementation("org.assertj:assertj-core:$assertjVersion")
    testImplementation("com.tngtech.archunit:archunit-junit5-api:$archunitVersion")
    testRuntimeOnly("com.tngtech.archunit:archunit-junit5-engine:$archunitVersion")
}
