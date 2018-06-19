package com.hexarchbootdemo.core.application

import com.hexarchbootdemo.core.domain.model.person.Person
import org.springframework.stereotype.Service


interface PeopleService {
    fun findByName(name:String): List<PersonDto>

    data class PersonDto(val firstName: String)
}

@Service
class PeopleServiceImpl(val personRepository: PersonRepository) : PeopleService {
    override fun findByName(name:String): List<PeopleService.PersonDto> {
        return personRepository.findPeople(FindByNameQuery(name)).map { it.toDto() }
    }
}

fun Person.toDto(): PeopleService.PersonDto {
    return PeopleService.PersonDto(firstName)
}