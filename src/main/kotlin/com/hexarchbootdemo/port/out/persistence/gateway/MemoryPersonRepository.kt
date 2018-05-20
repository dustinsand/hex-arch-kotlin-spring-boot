package com.hexarchbootdemo.port.out.persistence.gateway

import com.hexarchbootdemo.core.domain.entity.Person
import org.springframework.stereotype.Repository


@Repository
internal class MemoryPersonRepository {
    fun findByNameContains(nameContains: String?) : List<PersonMemory> {
        return listOf(PersonMemory("Dustin"))
    }
}

internal data class PersonMemory(val firstName: String) {
    fun toDomain(): Person {
        return Person(firstName)
    }
}