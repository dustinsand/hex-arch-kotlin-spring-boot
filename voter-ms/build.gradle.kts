description = "voter-ms"

plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
    id("org.springframework.boot")
}

dependencies {
    val archunitVersion: String by project

    implementation(project(":voter-application-core"))
    implementation(project(":voter-common"))
    implementation(project(":adapter-output:persistence"))

    testImplementation("com.tngtech.archunit:archunit-junit5-api:$archunitVersion")
    testRuntimeOnly("com.tngtech.archunit:archunit-junit5-engine:$archunitVersion")
}