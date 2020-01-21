description = "demo-main"

plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
    id("org.springframework.boot")
}

val junitJupiterVersion: String by project
val restAssuredVersion: String by project
val junitPlatformRunnerVersion: String by project
val assertjVersion: String by project

dependencies {
    implementation(project(":demo-application"))
    implementation(project(":adapter:input:rest"))
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.10.1")
    implementation("org.springframework.boot:spring-boot-starter-webflux:2.2.3.RELEASE")
    implementation("com.h2database:h2:1.4.200")
}