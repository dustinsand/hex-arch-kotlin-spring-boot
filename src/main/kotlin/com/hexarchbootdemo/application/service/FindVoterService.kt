package com.hexarchbootdemo.application.service

import com.hexarchbootdemo.application.port.input.FindVoterUseCase
import com.hexarchbootdemo.application.port.input.FindVoterUseCase.FindByLastNameQuery
import com.hexarchbootdemo.application.port.output.persistence.FindVoterPort
import com.hexarchbootdemo.domain.model.Voter
import org.springframework.stereotype.Service

@Service
class FindVotersService(val findVoterRepository: FindVoterPort) : FindVoterUseCase {
    override fun findByLastName(command: FindByLastNameQuery): List<Voter> {
        return findVoterRepository.findVotersByLastName(command)
    }
}