package com.hexarchbootdemo.application.port.output.repository

import com.hexarchbootdemo.application.port.input.FindVoterUseCase.FindByLastNameQuery
import com.hexarchbootdemo.domain.model.Voter
import kotlinx.coroutines.flow.Flow

interface FindVoterPort {
    fun findVotersByLastName(query: FindByLastNameQuery): List<Voter>

    suspend fun findVotersByLastNameReactive(query: FindByLastNameQuery): Flow<Voter>
}
