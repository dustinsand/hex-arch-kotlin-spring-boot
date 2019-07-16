package com.hexarchbootdemo.application.port.ingress


interface RegisterVoterUseCase {

    fun registerVoter(registerVoterCommand: RegisterVoterCommand)

    data class RegisterVoterCommand(val firstName: String, val lastName: String)

}