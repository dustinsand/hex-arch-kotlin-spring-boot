package com.hexarchbootdemo.adapter.ingress.rest

import com.hexarchbootdemo.adapter.ingress.rest.data.VoterJson
import com.hexarchbootdemo.application.port.ingress.FindVotersUseCase
import com.hexarchbootdemo.application.port.ingress.FindVotersUseCase.FindByNameQuery
import com.hexarchbootdemo.application.port.ingress.FindVotersUseCase.VoterDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import reactor.core.publisher.Mono.just


@RestController
private class VotersController {
    @Autowired
    lateinit var findVotersUseCase: FindVotersUseCase

    @RequestMapping("/voters")
    fun findVoters(@RequestParam name: String): Mono<List<VoterJson>> {
        return just(toJsonList(findVotersUseCase.findByName(FindByNameQuery(name))))
    }

    fun toJsonList(people: List<VoterDto>): List<VoterJson> {
        return people.map { VoterJson(it.firstName[0], it.lastName) }
    }
}