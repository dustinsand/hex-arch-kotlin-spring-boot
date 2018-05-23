package com.hexarchbootdemo.port.out.persistence.memory

import com.hexarchbootdemo.core.domain.model.person.CreatePerson
import com.hexarchbootdemo.core.domain.model.person.FindByNameQuery
import com.hexarchbootdemo.core.domain.model.person.Person
import com.hexarchbootdemo.core.domain.model.person.PersonRepository
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
