package com.hexarchbootdemo.core.domain.model.person

interface PersonRepository {
    fun findPeople(query: FindByNameQuery): List<Person>
    fun createPerson(command: CreatePerson): Long
}

data class FindByNameQuery(val nameContains: String?)