package com.hexarchbootdemo.adapter.output.rpc.eligibility.internal

import com.hexarchbootdemo.application.port.input.VoterEligibilityUseCase.VoterEligibilityQuery
import com.hexarchbootdemo.application.port.output.repository.VoterEligibilityPort
import io.github.resilience4j.circuitbreaker.CircuitBreaker
import io.github.resilience4j.kotlin.circuitbreaker.executeSuspendFunction
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
     * Also, shows basic usage of Resilicen4j library for managing fault tolerance. The fault tolerance
     * could be greatly enhanced here to take advantage of all the features in the library.
     *
     * This method ALWAYS returns true because it doesn't call real voter eligibility services.
     */
    override suspend fun isEligible(query: VoterEligibilityQuery): Boolean = coroutineScope {
        var callCountGoogle = 0
        val circuitBreakerGoogle = CircuitBreaker.ofDefaults("google")
        circuitBreakerGoogle.eventPublisher.onSuccess {
            println("Successfully called Google.")
            callCountGoogle++
        }
        val federalCheck: Deferred<String> = circuitBreakerGoogle.executeSuspendFunction {
            async {
                val webClient = WebClient.create("https://google.com")
                webClient.get()
                        .uri("/")
                        .awaitExchange()
                        .awaitBody<String>()
            }
        }

        var callCountKotlinlang = 0
        val circuitBreakerKotlinlang = CircuitBreaker.ofDefaults("kotlinlang")
        circuitBreakerKotlinlang.eventPublisher.onSuccess {
            println("Successfully called Kotlinlang.")
            callCountKotlinlang++
        }
        val stateCheck: Deferred<String> = circuitBreakerKotlinlang.executeSuspendFunction {
            async {
                val webClient = WebClient.create("https://kotlinlang.org")
                webClient.get()
                        .uri("/")
                        .awaitExchange()
                        .awaitBody<String>()
            }
        }

        // Assert the web calls actually are returning a response
        assert(callCountGoogle == 1)
        assert(callCountKotlinlang == 1)
        assert(federalCheck.await().isNotEmpty())
        assert(stateCheck.await().isNotEmpty())

        true
    }
}
