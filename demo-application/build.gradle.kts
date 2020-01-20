description = "demo-application"

plugins {
    //    id("org.springframework.boot")

    kotlin("jvm")
    kotlin("plugin.spring")
}

val junitJupiterVersion: String by project
val restAssuredVersion: String by project
val junitPlatformRunnerVersion: String by project
val assertjVersion: String by project
val archunitVersion: String by project

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-jooq:2.2.3.RELEASE")
    implementation("org.springframework.boot:spring-boot-starter-webflux:2.2.3.RELEASE")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.10.1")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    testImplementation(project(":demo-main"))
    testImplementation("org.springframework.boot:spring-boot-starter-test:2.2.3.RELEASE") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
    testImplementation("io.projectreactor:reactor-test:3.3.2.RELEASE")
    testImplementation("io.rest-assured:rest-assured:$restAssuredVersion")
    testImplementation("io.rest-assured:json-path:$restAssuredVersion")
    testImplementation("io.rest-assured:xml-path:$restAssuredVersion")
    testImplementation("io.rest-assured:json-schema-validator:$restAssuredVersion")
    testImplementation("io.rest-assured:spring-mock-mvc:$restAssuredVersion")
    testImplementation("io.rest-assured:kotlin-extensions:$restAssuredVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitJupiterVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:$junitJupiterVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-params:$junitJupiterVersion")
    testImplementation("org.junit.platform:junit-platform-runner:$junitPlatformRunnerVersion")
    testImplementation("org.assertj:assertj-core:$assertjVersion")
    testImplementation("com.tngtech.archunit:archunit-junit5-api:$archunitVersion")
    testRuntimeOnly("com.tngtech.archunit:archunit-junit5-engine:$archunitVersion")
}