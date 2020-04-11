description = "adapter-output-persistence-memory"

plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
}

dependencies {
    implementation(project(":voter-application-core"))
    implementation(project(":voter-common"))
}
