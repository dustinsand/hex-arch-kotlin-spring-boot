package com.hexarchbootdemo.application.service

import com.hexarchbootdemo.application.port.input.RegisterVoterUseCase
import com.hexarchbootdemo.application.port.output.persistence.RegisterVoterPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*


@Service
@Transactional
class RegisterVoterService(val registerVoterRepository: RegisterVoterPort) : RegisterVoterUseCase {

    override fun registerVoter(registerVoterCommand: RegisterVoterUseCase.RegisterVoterCommand): UUID {
        return registerVoterRepository.save(registerVoterCommand)
    }
}
