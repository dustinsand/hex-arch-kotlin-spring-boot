package com.hexarchbootdemo.application.service

import com.hexarchbootdemo.application.port.input.FindVoterUseCase
import com.hexarchbootdemo.application.port.input.FindVoterUseCase.FindByLastNameQuery
import com.hexarchbootdemo.application.port.output.persistence.FindVoterPort
import com.hexarchbootdemo.domain.model.Voter
import kotlinx.coroutines.flow.Flow
import org.springframework.stereotype.Service

@Service
class FindVoterService(val findVoterRepository: FindVoterPort) : FindVoterUseCase {
    override fun findByLastName(query: FindByLastNameQuery): List<Voter> {
        return findVoterRepository.findVotersByLastName(query)
    }

    override suspend fun findByLastNameReactive(query: FindByLastNameQuery): Flow<Voter> {
        return findVoterRepository.findVotersByLastNameReactive(query)
    }
}
