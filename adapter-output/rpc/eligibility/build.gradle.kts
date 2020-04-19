description = "adapter-output-rpc-eligibility"

plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
}

dependencies {
    implementation(project(":voter-application-core"))
    implementation(project(":voter-common"))
}
