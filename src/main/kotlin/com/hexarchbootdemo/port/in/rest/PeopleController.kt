package com.hexarchbootdemo.port.`in`.rest

import com.hexarchbootdemo.core.application.PeopleService
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
    fun findPeople(@RequestParam name:String): Mono<List<PeopleService.PersonDto>> {
        return Mono.just(peopleService.findByName(name))
    }
}