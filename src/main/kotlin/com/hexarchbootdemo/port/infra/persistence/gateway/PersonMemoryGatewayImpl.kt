package com.hexarchbootdemo.port.infra.persistence.gateway

import com.hexarchbootdemo.core.domain.entity.Person
import com.hexarchbootdemo.core.domain.gateway.FindPeopleQuery
import com.hexarchbootdemo.core.domain.gateway.PersonGateway
import org.springframework.stereotype.Component


@Component
class PersonMemoryGatewayImpl(private val productRepository: MemoryPersonRepository? = null) : PersonGateway {

    override fun findPeople(query: FindPeopleQuery): List<Person> {
        return productRepository!!.findByNameContains(query.nameContains).map { it.toDomain() }
    }
}
