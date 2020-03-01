import io.quarkus.gradle.tasks.QuarkusNative

plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
    kotlin("plugin.allopen")

    id("io.quarkus") version "1.2.1.Final"
    id("nu.studer.jooq") version "4.1"
}

dependencies {
    val quarkusPlatformGroupId: String by project
    val quarkusPlatformArtifactId: String by project
    val quarkusPlatformVersion: String by project

    implementation(project(":voter-application-core"))
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
    //jooqRuntime("com.h2database:h2:1.4.200")
    //jooqRuntime("io.quarkus:quarkus-jdbc-h2")

    testImplementation("io.quarkus:quarkus-junit5")
    testImplementation("io.rest-assured:rest-assured")
}

tasks {
    named<QuarkusNative>("buildNative") {
        setEnableHttpUrlHandler(true)
    }
}

quarkus {
    setSourceDir("$projectDir/src/main/kotlin")
    setOutputDirectory("$projectDir/build/classes/kotlin/main")
}

allOpen {
    annotation("javax.ws.rs.Path")
    annotation("javax.enterprise.context.ApplicationScoped")
    annotation("io.quarkus.test.junit.QuarkusTest")
}
