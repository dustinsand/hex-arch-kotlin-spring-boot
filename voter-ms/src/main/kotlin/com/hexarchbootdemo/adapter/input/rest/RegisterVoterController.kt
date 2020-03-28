package com.hexarchbootdemo.adapter.input.rest

import com.hexarchbootdemo.application.port.input.RegisterVoterUseCase
import com.hexarchbootdemo.application.port.input.RegisterVoterUseCase.RegisterVoterCommand
import com.hexarchbootdemo.domain.FIRST_NAME_MAX_LENGTH
import com.hexarchbootdemo.domain.FIRST_NAME_MIN_LENGTH
import com.hexarchbootdemo.domain.LAST_NAME_MAX_LENGTH
import com.hexarchbootdemo.domain.LAST_NAME_MIN_LENGTH
import java.net.URI
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.http.server.reactive.ServerHttpResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.valiktor.functions.hasSize
import org.valiktor.validate

@RestController
private class RegisterVoterController {
    @Autowired
    lateinit var registerVoterUseCase: RegisterVoterUseCase

    @PostMapping("/voters")
    fun save(response: ServerHttpResponse, @RequestBody form: RegisterVoterForm): ResponseEntity<Unit> {
        val voterId = registerVoterUseCase.registerVoter(RegisterVoterCommand(form.firstName, form.lastName))

        return ResponseEntity
                .created(URI.create("/voters/$voterId"))
                .build()
    }

    private data class RegisterVoterForm(val firstName: String, val lastName: String) {
        init {
            validate(this) {
                validate(RegisterVoterForm::firstName).hasSize(min = FIRST_NAME_MIN_LENGTH, max = FIRST_NAME_MAX_LENGTH)
                validate(RegisterVoterForm::lastName).hasSize(min = LAST_NAME_MIN_LENGTH, max = LAST_NAME_MAX_LENGTH)
            }
        }
    }
}
