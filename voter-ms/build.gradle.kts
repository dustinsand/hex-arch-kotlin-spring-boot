description = "voter-ms"

plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
    kotlin("plugin.allopen")
    id("org.springframework.boot")
}

dependencies {
    val archunitVersion: String by project

    implementation(platform("org.springframework.boot.experimental:spring-boot-bom-r2dbc:0.1.0.M3"))
    implementation(project(":voter-application-core"))
    implementation(project(":voter-common"))
    implementation(project(":adapter-output:persistence:h2"))
    implementation("org.jooq:jooq")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.3.3")

    testImplementation("com.tngtech.archunit:archunit-junit5-api:$archunitVersion")
    testImplementation("org.springframework.boot.experimental:spring-boot-test-autoconfigure-r2dbc")
    testRuntimeOnly("com.tngtech.archunit:archunit-junit5-engine:$archunitVersion")
}