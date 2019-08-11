package com.hexarchbootdemo.application.port.input

import com.hexarchbootdemo.domain.model.Voter


interface FindVoterUseCase {

    fun findByLastName(command: FindByLastNameQuery): List<Voter>

    data class FindByLastNameQuery(val lastNameContains: String)
}