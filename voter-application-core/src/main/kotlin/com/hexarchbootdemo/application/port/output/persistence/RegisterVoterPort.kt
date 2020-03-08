package com.hexarchbootdemo.application.port.output.persistence

import com.hexarchbootdemo.application.port.input.RegisterVoterUseCase.RegisterVoterCommand
import reactor.core.publisher.Mono
import java.util.UUID

interface RegisterVoterPort {
    fun save(command: RegisterVoterCommand): UUID
    fun saveReactive(command: RegisterVoterCommand): Mono<UUID>
}
