package com.hexarchbootdemo.application.service

import com.hexarchbootdemo.application.port.input.FindVoterUseCase
import com.hexarchbootdemo.application.port.input.FindVoterUseCase.FindByLastNameQuery
import com.hexarchbootdemo.application.port.output.persistence.FindVoterPort
import com.hexarchbootdemo.domain.model.Voter
import org.springframework.stereotype.Service

@Service
class FindVoterService(val findVoterRepository: FindVoterPort) : FindVoterUseCase {
    override fun findByLastName(query: FindByLastNameQuery): List<Voter> {
        return findVoterRepository.findVotersByLastName(query)
    }
}
