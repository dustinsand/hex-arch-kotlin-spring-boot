package com.hexarchbootdemo.adapter.input.rest.kotlinflow

import com.hexarchbootdemo.application.port.input.RegisterVoterUseCase
import com.hexarchbootdemo.domain.FIRST_NAME_MAX_LENGTH
import com.hexarchbootdemo.domain.FIRST_NAME_MIN_LENGTH
import com.hexarchbootdemo.domain.LAST_NAME_MAX_LENGTH
import com.hexarchbootdemo.domain.LAST_NAME_MIN_LENGTH
import com.hexarchbootdemo.domain.model.SocialSecurityNumber
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.created
import org.springframework.web.reactive.function.server.awaitBody
import org.springframework.web.reactive.function.server.buildAndAwait
import org.valiktor.functions.hasSize
import org.valiktor.functions.matches
import org.valiktor.validate
import java.net.URI

@Component
class RegisterVoterKotlinFlowHandler(private val registerVoterUseCase: RegisterVoterUseCase) {

    suspend fun save(request: ServerRequest): ServerResponse {
        val form: RegisterVoterForm = request.awaitBody()

        val voterId = registerVoterUseCase.registerVoterReactive(
                RegisterVoterUseCase.RegisterVoterCommand(SocialSecurityNumber(form.socialSecurityNumber), form.firstName, form.lastName))
        
        return created(URI.create("kotlin-reactive-flow/voters/$voterId")).buildAndAwait()
    }

    data class RegisterVoterForm(val socialSecurityNumber: String, val firstName: String, val lastName: String) {
        init {
            validate(this) {
                validate(RegisterVoterForm::socialSecurityNumber).matches(SocialSecurityNumber.SSN_REGEX)
                validate(RegisterVoterForm::firstName).hasSize(min = FIRST_NAME_MIN_LENGTH, max = FIRST_NAME_MAX_LENGTH)
                validate(RegisterVoterForm::lastName).hasSize(min = LAST_NAME_MIN_LENGTH, max = LAST_NAME_MAX_LENGTH)
            }
        }
    }
}