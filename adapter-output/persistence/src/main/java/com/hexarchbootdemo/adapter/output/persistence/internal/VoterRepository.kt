package com.hexarchbootdemo.adapter.output.persistence.internal

import com.hexarchbootdemo.application.port.input.FindVoterUseCase
import com.hexarchbootdemo.application.port.input.RegisterVoterUseCase
import com.hexarchbootdemo.application.port.output.persistence.FindVoterPort
import com.hexarchbootdemo.application.port.output.persistence.RegisterVoterPort
import com.hexarchbootdemo.domain.model.Voter
import java.util.UUID
import org.springframework.stereotype.Repository

/**
 * An actual database was not used intentionally to minimize the complexity of this project so concepts of architecture were clearer.
 * This repository is simply to demonstrate component responsibilities in this architecture.
 *
 * Notice the use of the Kotlin keyword 'internal' so this class is only visible in this module. See https://kotlinlang.org/docs/reference/visibility-modifiers.html#modules for a detailed explanation.
 *
 */
@Repository
internal class VoterRepository : FindVoterPort, RegisterVoterPort {
    // This is where you would use jOOQ or Spring Data for example depending on your type of repository.

    private final val databaseMap = mutableMapOf<UUID, Voter>()

    init {
        // Init voter map
        val voter1 = Voter(id = UUID.randomUUID(), firstName = "Dustin", lastName = "Shimono")
        val voter2 = Voter(id = UUID.randomUUID(), firstName = "Sandy", lastName = "Shimono")
        val voter3 = Voter(id = UUID.randomUUID(), firstName = "Tim", lastName = "Coocha")
        databaseMap[voter1.id] = voter1
        databaseMap[voter2.id] = voter2
        databaseMap[voter3.id] = voter3
    }

    override fun findVotersByLastName(query: FindVoterUseCase.FindByLastNameQuery): List<Voter> {
        return findAll().filter { it.lastName.contains(query.lastNameContains, true) }
    }

    override fun save(command: RegisterVoterUseCase.RegisterVoterCommand): UUID {
        // Assume validation logic of command performed here

        val id = UUID.randomUUID()
        save(Voter(id, command.firstName, command.lastName))
        return id
    }

    private fun findAll(): List<Voter> {
        return databaseMap.values.toList()
    }

    private fun save(voter: Voter) {
        // Assume validation logic performed here

        databaseMap[voter.id] = voter
    }
}
