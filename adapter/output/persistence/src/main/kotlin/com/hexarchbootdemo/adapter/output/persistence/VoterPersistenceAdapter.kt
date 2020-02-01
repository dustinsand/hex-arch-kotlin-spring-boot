package com.hexarchbootdemo.adapter.output.persistence

import com.hexarchbootdemo.application.port.input.FindVoterUseCase.FindByLastNameQuery
import com.hexarchbootdemo.application.port.input.RegisterVoterUseCase
import com.hexarchbootdemo.application.port.output.persistence.FindVoterPort
import com.hexarchbootdemo.application.port.output.persistence.RegisterVoterPort
import com.hexarchbootdemo.domain.model.Voter
import java.util.UUID
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class VoterPersistenceAdapter : FindVoterPort, RegisterVoterPort {

    @Autowired
    private lateinit var voterRepo: VoterRepository

    override fun findVotersByLastName(query: FindByLastNameQuery): List<Voter> {
        return voterRepo.findAll().filter { it.lastName.contains(query.lastNameContains, true) }
    }

    override fun save(command: RegisterVoterUseCase.RegisterVoterCommand): UUID {
        // Assume validation logic of command performed here

        val id = UUID.randomUUID()
        voterRepo.save(Voter(id, command.firstName, command.lastName))
        return id
    }
}
