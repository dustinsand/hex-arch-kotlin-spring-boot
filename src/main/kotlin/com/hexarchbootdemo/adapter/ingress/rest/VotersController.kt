package com.hexarchbootdemo.adapter.ingress.rest

import com.hexarchbootdemo.adapter.ingress.rest.data.VoterJson
import com.hexarchbootdemo.application.port.ingress.FindVotersUseCase.VoterDto
import com.hexarchbootdemo.application.service.FindVotersService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono


@RestController
class VotersController {
    @Autowired
    lateinit var findVotersService: FindVotersService

    @RequestMapping("/voters")
    fun findVoters(@RequestParam name: String): Mono<List<VoterJson>> {
        return Mono.just(toJsonList(findVotersService.findByName(name)))
    }

    fun toJsonList(people: List<VoterDto>): List<VoterJson> {
        return people.map { VoterJson(it.firstName[0], it.lastName) }
    }
}