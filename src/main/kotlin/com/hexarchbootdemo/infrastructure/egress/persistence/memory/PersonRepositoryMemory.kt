package com.hexarchbootdemo.infrastructure.egress.persistence.memory

import com.hexarchbootdemo.core.application.FindByNameQuery
import com.hexarchbootdemo.core.application.PersonRepository
import com.hexarchbootdemo.core.domain.model.person.CreatePerson
import com.hexarchbootdemo.core.domain.model.person.Person
import org.springframework.stereotype.Repository


@Repository
class PersonRepositoryMemory : PersonRepository {
    override fun createPerson(command: CreatePerson): Long {
        TODO("not implemented")
    }

    override fun findPeople(query: FindByNameQuery): List<Person> {
        return listOf(Person("Dustin"))
    }
}
