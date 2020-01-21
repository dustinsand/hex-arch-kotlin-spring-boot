package com.hexarchbootdemo.application.port.output.persistence

import com.hexarchbootdemo.application.port.input.RegisterVoterUseCase.RegisterVoterCommand
import java.util.*

interface RegisterVoterPort {
    fun save(command: RegisterVoterCommand): UUID
}
