description = "demo-main"

plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
    id("org.springframework.boot")
}

dependencies {
    val archunitVersion: String by project

    implementation(project(":demo-application"))
    implementation(project(":adapter:input:rest"))
    implementation(project(":adapter:output:persistence"))
    implementation(project(":adapter:output:rpc"))

    testImplementation("com.tngtech.archunit:archunit-junit5-api:$archunitVersion")
    testRuntimeOnly("com.tngtech.archunit:archunit-junit5-engine:$archunitVersion")
}