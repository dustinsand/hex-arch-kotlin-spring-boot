package com.hexarchbootdemo.application.port.input

import com.hexarchbootdemo.domain.FIRST_NAME_MAX_LENGTH
import com.hexarchbootdemo.domain.FIRST_NAME_MIN_LENGTH
import com.hexarchbootdemo.domain.LAST_NAME_MAX_LENGTH
import com.hexarchbootdemo.domain.LAST_NAME_MIN_LENGTH
import com.hexarchbootdemo.domain.model.SocialSecurityNumber
import java.util.UUID
import org.valiktor.functions.hasSize
import org.valiktor.validate

interface RegisterVoterUseCase {

    fun registerVoter(registerVoterCommand: RegisterVoterCommand): UUID
    suspend fun registerVoterReactive(registerVoterCommand: RegisterVoterCommand): UUID

    data class RegisterVoterCommand(val socialSecurityNumber: SocialSecurityNumber, val firstName: String, val lastName: String) {
        init {
            validate(this) {
                validate(RegisterVoterCommand::firstName).hasSize(min = FIRST_NAME_MIN_LENGTH, max = FIRST_NAME_MAX_LENGTH)
                validate(RegisterVoterCommand::lastName).hasSize(min = LAST_NAME_MIN_LENGTH, max = LAST_NAME_MAX_LENGTH)
            }
        }
    }
}
