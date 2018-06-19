package com.hexarchbootdemo.core.application

import com.hexarchbootdemo.core.domain.model.person.CreatePerson
import com.hexarchbootdemo.core.domain.model.person.Person

interface PersonRepository {
    fun findPeople(query: FindByNameQuery): List<Person>
    fun createPerson(command: CreatePerson): Long
}

data class FindByNameQuery(val nameContains: String?)