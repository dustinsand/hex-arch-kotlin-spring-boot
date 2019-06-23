package com.hexarchbootdemo.adapter.egress.persistence.memory

import com.hexarchbootdemo.application.CreatePerson
import com.hexarchbootdemo.application.FindByNameQuery
import com.hexarchbootdemo.application.PeopleRepository
import com.hexarchbootdemo.domain.model.person.Person
import org.springframework.stereotype.Repository


@Repository
class PersonRepositoryMemory : PeopleRepository {
    override fun createPerson(command: CreatePerson): Long {
        TODO("not implemented")
    }

    override fun findPeople(query: FindByNameQuery): List<Person> {
        return listOf(Person("Dustin"))
    }
}
