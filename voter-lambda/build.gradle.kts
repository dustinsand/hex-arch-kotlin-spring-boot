import io.quarkus.gradle.tasks.QuarkusNative

plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
    kotlin("plugin.allopen")

    id("io.quarkus") version "1.2.1.Final"
    id("nu.studer.jooq") version "4.1"
}

dependencies {
    val archunitVersion: String by project
    val quarkusPlatformGroupId: String by project
    val quarkusPlatformArtifactId: String by project
    val quarkusPlatformVersion: String by project

    implementation(platform("org.springframework.boot.experimental:spring-boot-bom-r2dbc:0.1.0.M3"))
    implementation(project(":voter-application-core"))
    implementation(project(":voter-common"))
    implementation(project(":adapter-output:persistence:h2"))
    implementation("org.jooq:jooq")
    implementation("io.quarkus:quarkus-spring-boot-properties")
    implementation("io.quarkus:quarkus-spring-di")
    implementation("io.quarkus:quarkus-resteasy-jackson")
    implementation("io.quarkus:quarkus-jackson")
    implementation("io.quarkus:quarkus-kotlin")
    implementation("io.quarkus:quarkus-amazon-lambda")
    implementation("io.quarkus:quarkus-jdbc-h2")
    implementation("io.quarkus:quarkus-agroal")
    implementation("io.quarkus:quarkus-flyway")
    implementation(enforcedPlatform("${quarkusPlatformGroupId}:${quarkusPlatformArtifactId}:${quarkusPlatformVersion}"))
    implementation("io.quarkus:quarkus-resteasy")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.3.3")

    testImplementation("io.quarkus:quarkus-junit5")
    testImplementation("io.rest-assured:rest-assured")
    testImplementation("com.tngtech.archunit:archunit-junit5-api:$archunitVersion")
    testImplementation("org.springframework.boot.experimental:spring-boot-test-autoconfigure-r2dbc")
    testRuntimeOnly("com.tngtech.archunit:archunit-junit5-engine:$archunitVersion")
}

tasks {
    named<QuarkusNative>("buildNative") {
        setEnableHttpUrlHandler(true)
    }
}

quarkus {
    setSourceDir("$projectDir/src/main/java")
    setOutputDirectory("$projectDir/build/classes/java/main")
}

allOpen {
    annotation("javax.ws.rs.Path")
    annotation("javax.enterprise.context.ApplicationScoped")
    annotation("io.quarkus.test.junit.QuarkusTest")
}
