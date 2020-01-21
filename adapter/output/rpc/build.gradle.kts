description = "adapter-output-rpc"

plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
}

dependencies {
    implementation(project(":demo-application"))
}
