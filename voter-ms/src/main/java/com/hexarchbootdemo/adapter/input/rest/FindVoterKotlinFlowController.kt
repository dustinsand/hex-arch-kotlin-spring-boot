package com.hexarchbootdemo.adapter.input.rest

import com.hexarchbootdemo.adapter.input.rest.data.VoterJson
import com.hexarchbootdemo.application.port.input.FindVoterUseCase
import com.hexarchbootdemo.application.port.input.FindVoterUseCase.FindByLastNameQuery
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class FindVoterKotlinFlowController(
    private val findVotersUseCase: FindVoterUseCase
) {

    @GetMapping("kotlin-reactive-flow/voters")
    suspend fun findVoters(@RequestParam lastName: String): Flow<VoterJson> {
        return findVotersUseCase.findByLastNameReactive(FindByLastNameQuery(lastName))
                .map { VoterJson(firstInitial = it.firstName[0],
                        lastName = it.lastName,
                        socialSecurityNumber = it.socialSecurityNumber.toString()) }
    }
}
