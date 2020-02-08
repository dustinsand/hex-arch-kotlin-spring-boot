description = "voter-application-core"

plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
}

dependencies {
    testImplementation(project(":voter-ms"))
}