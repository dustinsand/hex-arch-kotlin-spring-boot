package com.hexarchbootdemo.core.domain.gateway

import com.hexarchbootdemo.core.domain.entity.Person

interface PersonGateway {
    fun findPeople(query: FindPeopleQuery): List<Person>
}

data class FindPeopleQuery(val nameContains: String?)