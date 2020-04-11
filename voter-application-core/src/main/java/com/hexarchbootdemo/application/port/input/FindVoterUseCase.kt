package com.hexarchbootdemo.application.port.input

import com.hexarchbootdemo.domain.model.Voter
import org.valiktor.functions.isNotEmpty
import org.valiktor.validate

interface FindVoterUseCase {

    fun findByLastName(query: FindByLastNameQuery): List<Voter>

    data class FindByLastNameQuery(val lastNameContains: String) {
        init {
            validate(this) {
                validate(FindByLastNameQuery::lastNameContains).isNotEmpty()
            }
        }
    }
}
