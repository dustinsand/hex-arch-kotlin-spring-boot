description = "demo-main"

plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
    id("org.springframework.boot")
}

dependencies {
    implementation(project(":demo-application"))
    implementation(project(":adapter:input:rest"))
}