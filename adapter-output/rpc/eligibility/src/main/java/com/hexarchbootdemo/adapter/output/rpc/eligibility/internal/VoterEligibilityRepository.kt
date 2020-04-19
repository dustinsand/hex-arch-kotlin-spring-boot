package com.hexarchbootdemo.adapter.output.rpc.eligibility.internal

import com.hexarchbootdemo.application.port.input.VoterEligibilityUseCase.VoterEligibilityQuery
import com.hexarchbootdemo.application.port.output.repository.VoterEligibilityPort
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.springframework.stereotype.Repository
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import org.springframework.web.reactive.function.client.awaitExchange

@Repository
internal class VoterEligibilityRepository : VoterEligibilityPort {
    /**
     * The intent of this function is to show an example of how to asynchronously call two remote services.
     *
     * This method ALWAYS returns true because it doesn't call real voter eligibility services.
     */
    override suspend fun isEligible(query: VoterEligibilityQuery): Boolean = coroutineScope {
        val federalCheck: Deferred<String> = async {
            val webClient = WebClient.create("https://google.com")
            webClient.get()
                    .uri("/")
                    .awaitExchange()
                    .awaitBody<String>()
        }
        val stateCheck: Deferred<String> = async {
            val webClient = WebClient.create("https://kotlinlang.org")
            webClient.get()
                    .uri("/")
                    .awaitExchange()
                    .awaitBody<String>()
        }

        // Assert the web calls actually are returning a response
        assert(federalCheck.await().isNotEmpty())
        assert(stateCheck.await().isNotEmpty())

        true
    }
}
