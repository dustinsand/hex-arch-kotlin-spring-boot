package com.hexarchbootdemo.domain.model

import org.valiktor.functions.matches
import org.valiktor.validate

// Unfortunately I can't use the 'inline' modifier here because inline classes can not have init blocks.
/**
 * Social Security Number in the format ddd-dd-dddd.
 */
data class SocialSecurityNumber(val value: String) {
    init {
        validate(this) {
            validate(SocialSecurityNumber::value).matches(SSN_REGEX)
        }
    }

    companion object {
        val SSN_REGEX = """^\d{3}-\d{2}-\d{4}${'$'}""".toRegex()
    }

    override fun toString(): String {
        return value
    }
}
