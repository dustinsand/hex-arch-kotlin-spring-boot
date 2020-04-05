package com.hexarchbootdemo.domain.model

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.valiktor.ConstraintViolationException

class SocialSecurityNumberTest {
    @Test
    fun `should pass`() {
        SocialSecurityNumber("123-45-6789")
    }

    @Test
    fun `should fail validate empty string`() {
        Assertions.assertThrows(ConstraintViolationException::class.java) { SocialSecurityNumber("") }
    }

    @Test
    fun `should fail validate letters`() {
        Assertions.assertThrows(ConstraintViolationException::class.java) { SocialSecurityNumber("abc-de-fghi") }
    }

    @Test
    fun `should fail validate no dashes`() {
        Assertions.assertThrows(ConstraintViolationException::class.java) { SocialSecurityNumber("123456789") }
    }

    @Test
    fun `should fail validate length`() {
        Assertions.assertThrows(ConstraintViolationException::class.java) { SocialSecurityNumber("23-45-6789") }
        Assertions.assertThrows(ConstraintViolationException::class.java) { SocialSecurityNumber("123-5-6789") }
        Assertions.assertThrows(ConstraintViolationException::class.java) { SocialSecurityNumber("123-45-789") }
    }
}
