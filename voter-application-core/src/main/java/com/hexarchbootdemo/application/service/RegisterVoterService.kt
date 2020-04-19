package com.hexarchbootdemo.application.service

import com.hexarchbootdemo.application.port.input.RegisterVoterUseCase
import com.hexarchbootdemo.application.port.output.repository.RegisterVoterPort
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class RegisterVoterService(val registerVoterRepository: RegisterVoterPort) : RegisterVoterUseCase {

    override fun registerVoter(registerVoterCommand: RegisterVoterUseCase.RegisterVoterCommand): UUID {
        return registerVoterRepository.save(registerVoterCommand)
    }

    override suspend fun registerVoterReactive(registerVoterCommand: RegisterVoterUseCase.RegisterVoterCommand): UUID {
        return registerVoterRepository.saveReactive(registerVoterCommand)
    }
}
