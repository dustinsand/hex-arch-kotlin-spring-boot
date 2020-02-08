package com.hexarchbootdemo.application.port.output.persistence

import com.hexarchbootdemo.application.port.input.FindVoterUseCase.FindByLastNameQuery
import com.hexarchbootdemo.domain.model.Voter

interface FindVoterPort {
    fun findVotersByLastName(query: FindByLastNameQuery): List<Voter>
}
