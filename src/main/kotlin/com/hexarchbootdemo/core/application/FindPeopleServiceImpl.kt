package com.hexarchbootdemo.core.application

import com.hexarchbootdemo.core.domain.entity.Person
import com.hexarchbootdemo.core.domain.gateway.FindPeopleQuery
import com.hexarchbootdemo.core.domain.gateway.PersonGateway
import org.springframework.stereotype.Component


@Component
internal class FindPeopleImpl(val personGateway: PersonGateway?) : FindPeopleService {
    override fun find(request: FindPeopleService.Request): FindPeopleService.Response {
        val result = personGateway!!.findPeople(FindPeopleQuery(request.nameContains))
                .map { it.toUseCaseResponseModel() }
        return FindPeopleService.Response(result)
    }
}

internal fun Person.toUseCaseResponseModel(): FindPeopleService.Response.Person {
    return FindPeopleService.Response.Person(firstName)
}