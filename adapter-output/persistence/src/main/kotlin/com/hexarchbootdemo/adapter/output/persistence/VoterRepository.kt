package com.hexarchbootdemo.adapter.output.persistence

import com.hexarchbootdemo.domain.model.Voter
import java.util.UUID
import org.springframework.stereotype.Repository

/**
 * An actual database was not used intentionally to minimize the complexity of this project so concepts of architecture were clearer.
 * This repository is simply to demonstrate component responsibilities in this architecture.
 *
 * Notice the use of the Kotlin keyword 'internal' so this class is only visible in this module. See https://kotlinlang.org/docs/reference/visibility-modifiers.html#modules for a detailed explanation.
 *
 * TODO use TestContainers with Postgres and jOOQ.
 */
@Repository
internal class VoterRepository {
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

    fun findAll(): List<Voter> {
        return databaseMap.values.toList()
    }

    fun save(voter: Voter) {
        // Assume validation logic performed here

        databaseMap[voter.id] = voter
    }
}
