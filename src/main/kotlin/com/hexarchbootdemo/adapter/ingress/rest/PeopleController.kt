package com.hexarchbootdemo.adapter.ingress.rest

import com.hexarchbootdemo.adapter.ingress.rest.data.PersonJson
import com.hexarchbootdemo.application.PeopleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono


@RestController
class PeopleController {
    @Autowired
    lateinit var peopleService: PeopleService

    @RequestMapping("/people")
    fun findPeople(@RequestParam name:String): Mono<List<PersonJson>> {
        return Mono.just(toJsonList(peopleService.findByName(name)))
    }

    fun toJsonList(people: List<PeopleService.PersonDto>): List<PersonJson> {
        return people.map { PersonJson(it.firstName[0], it.firstName) }
    }
}