description = "voter-application"

plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
}

dependencies {
    testImplementation(project(":voter-ms"))
}