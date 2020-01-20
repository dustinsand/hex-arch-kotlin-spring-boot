package com.hexarchbootdemo.adapter.input.rest

import com.hexarchbootdemo.application.port.input.RegisterVoterUseCase
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.http.server.reactive.ServerHttpResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
private class RegisterVoterController {
    @Autowired
    lateinit var registerVoterUseCase: RegisterVoterUseCase

    @PostMapping("/voters")
    fun save(response: ServerHttpResponse, @RequestBody form: RegisterVoterForm): ResponseEntity<Unit> {
        val voterId = registerVoterUseCase.registerVoter(RegisterVoterUseCase.RegisterVoterCommand(form.firstName, form.lastName))

        // TODO what is best way to create the location URI the Spring way?
        return ResponseEntity
                .created(URI.create("/voters/$voterId"))
                .build()
    }

    private data class RegisterVoterForm(val firstName: String, val lastName: String)
}