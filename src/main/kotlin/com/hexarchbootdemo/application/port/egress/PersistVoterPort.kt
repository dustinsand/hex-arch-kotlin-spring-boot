package com.hexarchbootdemo.application.port.egress

import com.hexarchbootdemo.application.port.ingress.RegisterVotersUseCase.RegisterVoterCommand

interface PersistVoterPort {
    fun createPerson(command: RegisterVoterCommand): Long
}
