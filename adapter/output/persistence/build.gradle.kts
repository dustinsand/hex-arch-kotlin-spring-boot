description = "adapter-output-persistence"

plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
}

dependencies {
    implementation(project(":demo-application"))
}
