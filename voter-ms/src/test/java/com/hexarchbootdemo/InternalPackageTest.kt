package com.hexarchbootdemo

import com.hexarchbootdemo.common.archunit.InternalPackage
import com.tngtech.archunit.core.importer.ClassFileImporter
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses
import java.io.IOException
import java.util.stream.Collectors
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.reflections.Reflections

/**
 * Evaluates [InternalPackage] annotations and checks that those packages are not accessed from the outside.
 */
internal class InternalPackageTest {
    private val analyzedClasses = ClassFileImporter().importPackages(BASE_PACKAGE)

    @Test
    @Throws(IOException::class)
    fun `internal packages are not accessed from outside`() {

        // so that the test will break when the base package is re-named
        assertPackageExists(BASE_PACKAGE)
        val internalPackages = internalPackages(BASE_PACKAGE)
        for (internalPackage in internalPackages) {
            assertPackageExists(internalPackage)
            assertPackageIsNotAccessedFromOutside(internalPackage)
        }
    }

    /**
     * Finds all packages annotated with @[InternalPackage].
     */
    private fun internalPackages(basePackage: String): List<String> {
        val reflections = Reflections(basePackage)
        return reflections.getTypesAnnotatedWith(InternalPackage::class.java).stream()
                .map({ c -> c.getPackage().getName() })
                .collect(Collectors.toList())
    }

    fun assertPackageIsNotAccessedFromOutside(internalPackage: String) {
        noClasses().that().resideOutsideOfPackage(packageMatcher(internalPackage))
                .should().dependOnClassesThat().resideInAPackage(packageMatcher(internalPackage))
                .check(analyzedClasses)
    }

    fun assertPackageExists(packageName: String?) {
        assertThat(analyzedClasses.containPackage(packageName))
                .`as`("package %s exists", packageName)
                .isTrue()
    }

    private fun packageMatcher(fullyQualifiedPackage: String): String {
        return "$fullyQualifiedPackage.."
    }

    companion object {
        private val BASE_PACKAGE = InternalPackageTest::class.java.`package`.name
    }
}
