package com.hexarchbootdemo.application

import com.hexarchbootdemo.domain.model.person.Person

interface PeopleRepository {
    fun findPeople(query: FindByNameQuery): List<Person>
    fun createPerson(command: CreatePerson): Long
}

data class FindByNameQuery(val nameContains: String?)