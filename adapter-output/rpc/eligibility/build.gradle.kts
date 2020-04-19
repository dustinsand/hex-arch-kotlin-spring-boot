description = "adapter-output-rpc-eligibility"

plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
}

dependencies {
    val resilience4jVersion: String by project

    implementation(project(":voter-application-core"))
    implementation(project(":voter-common"))
    implementation("io.github.resilience4j:resilience4j-spring-boot2:${resilience4jVersion}")
    implementation("io.github.resilience4j:resilience4j-circuitbreaker:${resilience4jVersion}")
    implementation("io.github.resilience4j:resilience4j-kotlin:${resilience4jVersion}")
}
