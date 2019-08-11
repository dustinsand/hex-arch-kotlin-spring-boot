package com.hexarchbootdemo.application.port.input

import java.util.UUID


interface RegisterVoterUseCase {

    fun registerVoter(registerVoterCommand: RegisterVoterCommand): UUID

    data class RegisterVoterCommand(val firstName: String, val lastName: String)

}