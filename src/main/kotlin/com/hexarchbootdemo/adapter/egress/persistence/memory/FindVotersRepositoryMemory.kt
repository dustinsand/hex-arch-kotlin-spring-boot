package com.hexarchbootdemo.adapter.egress.persistence.memory

import com.hexarchbootdemo.application.port.egress.FindVoterPort
import com.hexarchbootdemo.application.port.ingress.FindVotersUseCase.FindByNameQuery
import com.hexarchbootdemo.domain.model.Voter
import org.springframework.stereotype.Repository

@Repository
class FindVotersRepositoryMemory : FindVoterPort {

    override fun findVoters(query: FindByNameQuery): List<Voter> {
        return listOf(Voter(firstName = "Dustin", lastName = "Shimono"))
    }
}
