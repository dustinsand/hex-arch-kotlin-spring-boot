package com.hexarchbootdemo.application.port.egress.persistence

import com.hexarchbootdemo.application.port.ingress.FindVoterUseCase.FindByCriteriaQuery
import com.hexarchbootdemo.domain.model.Voter

interface FindVoterPort {
    fun findVoters(query: FindByCriteriaQuery): List<Voter>
}