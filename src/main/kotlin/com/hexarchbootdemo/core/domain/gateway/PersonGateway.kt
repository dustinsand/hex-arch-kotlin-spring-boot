package com.hexarchbootdemo.core.domain.gateway

import com.hexarchbootdemo.core.domain.entity.Person

internal interface PersonGateway {
    fun findPeople(query: FindPeopleQuery): List<Person>
}

internal data class FindPeopleQuery(val nameContains: String?)