package com.hexarchbootdemo.adapter.ingress.rest

import com.hexarchbootdemo.adapter.ingress.rest.data.VoterJson
import com.hexarchbootdemo.application.port.ingress.FindVoterUseCase
import com.hexarchbootdemo.application.port.ingress.FindVoterUseCase.FindByCriteriaQuery
import com.hexarchbootdemo.application.port.ingress.FindVoterUseCase.VoterDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import reactor.core.publisher.Mono.just


@RestController
private class VoterController {
    @Autowired
    lateinit var findVotersUseCase: FindVoterUseCase

    @RequestMapping("/voters")
    fun findVoters(@RequestParam name: String): Mono<List<VoterJson>> {
        return just(toJsonList(findVotersUseCase.findByName(FindByCriteriaQuery(name))))
    }

    private fun toJsonList(people: List<VoterDto>): List<VoterJson> {
        return people.map { VoterJson(firstInitial = it.firstInitial(), lastName = it.lastName) }
    }
}