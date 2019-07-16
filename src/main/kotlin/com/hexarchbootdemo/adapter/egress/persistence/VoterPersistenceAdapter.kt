package com.hexarchbootdemo.adapter.egress.persistence

import com.hexarchbootdemo.application.port.egress.persistence.FindVoterPort
import com.hexarchbootdemo.application.port.ingress.FindVoterUseCase.FindByCriteriaQuery
import com.hexarchbootdemo.domain.model.Voter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class VoterPersistenceAdapter : FindVoterPort {

    @Autowired
    private lateinit var voterRepo: VoterRepository

    override fun findVoters(query: FindByCriteriaQuery): List<Voter> {
        return voterRepo.findAll()
    }
}
