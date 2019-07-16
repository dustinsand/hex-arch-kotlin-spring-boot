package com.hexarchbootdemo.application.port.egress.persistence

import com.hexarchbootdemo.application.port.ingress.RegisterVoterUseCase.RegisterVoterCommand

interface PersistVoterPort {
    fun createPerson(command: RegisterVoterCommand): Long
}
