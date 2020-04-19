package com.hexarchbootdemo.application.port.output.repository

import com.hexarchbootdemo.application.port.input.RegisterVoterUseCase.RegisterVoterCommand
import java.util.UUID

interface RegisterVoterPort {
    fun save(command: RegisterVoterCommand): UUID
    suspend fun saveReactive(command: RegisterVoterCommand): UUID
}
