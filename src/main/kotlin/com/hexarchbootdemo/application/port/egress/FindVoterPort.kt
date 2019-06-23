package com.hexarchbootdemo.application.port.egress

import com.hexarchbootdemo.application.port.ingress.FindVotersUseCase.FindByNameQuery
import com.hexarchbootdemo.domain.model.Voter

interface FindVoterPort {
    fun findVoters(query: FindByNameQuery): List<Voter>
}