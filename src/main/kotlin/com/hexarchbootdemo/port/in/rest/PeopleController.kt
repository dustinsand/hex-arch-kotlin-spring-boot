package com.hexarchbootdemo.port.`in`.rest

import com.hexarchbootdemo.core.application.FindPeopleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono


@RestController
class PeopleController {
    @Autowired
    lateinit var findPeopleService: FindPeopleService

    @RequestMapping("/people")
    fun findPeople(): Mono<List<FindPeopleService.Response.Person>> {
        // TODO undo hardcoding find param value
        return Mono.just(findPeopleService.find(FindPeopleService.Request("dustin")).people)
    }
}