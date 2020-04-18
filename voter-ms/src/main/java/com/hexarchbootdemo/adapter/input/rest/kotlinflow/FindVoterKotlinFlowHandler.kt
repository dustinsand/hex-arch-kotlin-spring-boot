package com.hexarchbootdemo.adapter.input.rest.kotlinflow

import com.hexarchbootdemo.adapter.input.rest.data.VoterJson
import com.hexarchbootdemo.application.port.input.FindVoterUseCase
import kotlinx.coroutines.flow.map
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.bodyAndAwait
import org.springframework.web.server.ResponseStatusException

@Component
class FindVoterKotlinFlowHandler(private val findVotersUseCase: FindVoterUseCase) {

    suspend fun findVoters(request: ServerRequest): ServerResponse {
        require(request.queryParam("lastName").isPresent) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "QueryParam |lastName| is required.") }

        return ok()
                .bodyAndAwait(findVotersUseCase.findByLastNameReactive(FindVoterUseCase.FindByLastNameQuery(
                        request.queryParam("lastName").get()))
                        .map {
                            VoterJson(firstInitial = it.firstName[0],
                                    lastName = it.lastName,
                                    socialSecurityNumber = it.socialSecurityNumber.toString())
                        })
    }
}