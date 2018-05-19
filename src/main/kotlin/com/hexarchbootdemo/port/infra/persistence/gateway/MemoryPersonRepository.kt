package com.hexarchbootdemo.port.infra.persistence.gateway

import com.hexarchbootdemo.core.domain.entity.Person
import org.springframework.stereotype.Repository


@Repository
class MemoryPersonRepository {
    fun findByNameContains(nameContains: String?) : List<PersonMemory> {
        return listOf(PersonMemory("Dustin"))
    }
}

data class PersonMemory(val firstName: String) {
    fun toDomain(): Person {
        return Person(firstName)
    }
}