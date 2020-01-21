description = "adapter-input-rest"

plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
}

dependencies {
    implementation(project(":demo-application"))
}
