package com.hexarchbootdemo.adapter.input.rest.kotlinflow

import kotlinx.coroutines.FlowPreview
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class KotlinFlowRouterConfiguration {
    @FlowPreview
    @Bean
    fun routes(
        findVoterHandler: FindVoterKotlinFlowHandler,
        registerVoterKotlinFlowHandler: RegisterVoterKotlinFlowHandler
    ) = coRouter {
        "/kotlin-reactive-flow/voters".nest {
            GET("/", findVoterHandler::findVoters)
            POST("/", registerVoterKotlinFlowHandler::save)
        }
    }
}
