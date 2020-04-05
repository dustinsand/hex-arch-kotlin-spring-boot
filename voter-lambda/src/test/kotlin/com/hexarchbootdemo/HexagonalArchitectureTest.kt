package com.hexarchbootdemo

import com.tngtech.archunit.core.domain.JavaClasses
import com.tngtech.archunit.core.importer.ClassFileImporter
import com.tngtech.archunit.junit.AnalyzeClasses
import com.tngtech.archunit.junit.ArchTest
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses
import com.tngtech.archunit.library.Architectures.layeredArchitecture
import com.tngtech.archunit.library.dependencies.SlicesRuleDefinition
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AnalyzeClasses(packagesOf = [HexagonalArchitectureTest::class])
internal class HexagonalArchitectureTest {
    @ArchTest
    val `there are no package cycles` =
            SlicesRuleDefinition.slices()
                    .matching("$BASE_PACKAGE.(*)..")
                    .should()
                    .beFreeOfCycles()

    @ArchTest
    val `should honor the layered architecture` = layeredArchitecture()
            .layer("Adapter").definedBy("$ADAPTER_PACKAGE..")
            .layer("Application").definedBy("$APPLICATION_PACKAGE..")
            .layer("Domain").definedBy("$DOMAIN_PACKAGE..")

            .whereLayer("Application").mayOnlyBeAccessedByLayers("Adapter")

    @ArchTest
    val `the domain model does not have outgoing dependencies` =
            noClasses()
                    .that()
                    .resideInAPackage("$DOMAIN_MODEL_PACKAGE..")
                    .should()
                    .dependOnClassesThat()
                    .resideInAnyPackage("$DOMAIN_SERVICE_PACKAGE..", "$APPLICATION_PACKAGE..", "$ADAPTER_PACKAGE..")

    @ArchTest
    val `the domain does not access the application and adapters` =
            noClasses()
                    .that()
                    .resideInAPackage("$DOMAIN_PACKAGE..")
                    .should()
                    .dependOnClassesThat()
                    .resideInAnyPackage("$APPLICATION_PACKAGE..", "$ADAPTER_PACKAGE..")

    @ArchTest
    val `the application does not access the adapters` =
            noClasses()
                    .that()
                    .resideInAPackage("$APPLICATION_PACKAGE..")
                    .should()
                    .accessClassesThat()
                    .resideInAPackage("$ADAPTER_PACKAGE..")

    @ParameterizedTest(name = "adapter \"{1}\" does not access another adapter")
    @MethodSource("adapterParametersProvider")
    fun `one adapter should not access another adapter`(classes: JavaClasses, adapterPackage: String) {
        val otherAdapterPackages = ADAPTER_SUBPACKAGES
                .filter { it != adapterPackage }
                .toTypedArray()
        noClasses()
                .that()
                .resideInAPackage(adapterPackage)
                .should()
                .accessClassesThat()
                .resideInAnyPackage(*otherAdapterPackages)
                .check(classes)
    }

    

    private fun adapterParametersProvider(): List<Arguments> {
        return ADAPTER_SUBPACKAGES.map { Arguments.of(CLASSES, it) }
    }

    companion object {
        private val BASE_PACKAGE = HexagonalArchitectureTest::class.java.`package`.name
        private val CLASSES = ClassFileImporter().importPackages(BASE_PACKAGE)

        private val DOMAIN_PACKAGE = "$BASE_PACKAGE.domain"
        private val DOMAIN_MODEL_PACKAGE = "$DOMAIN_PACKAGE.model"
        private val DOMAIN_SERVICE_PACKAGE = "$DOMAIN_PACKAGE.service"

        private val APPLICATION_PACKAGE = "$BASE_PACKAGE.application"

        private val ADAPTER_PACKAGE = "$BASE_PACKAGE.adapter"
        private val ADAPTER_SUBPACKAGE_REGEX = "($ADAPTER_PACKAGE\\.[^.]+).*".toRegex()
        private val ADAPTER_SUBPACKAGES = CLASSES
                .map { ADAPTER_SUBPACKAGE_REGEX.find(it.name)?.groups?.get(1)?.value }
                .filterNotNull()
                .distinct()
    }
}
