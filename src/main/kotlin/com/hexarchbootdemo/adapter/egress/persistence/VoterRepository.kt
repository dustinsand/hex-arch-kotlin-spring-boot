package com.hexarchbootdemo.adapter.egress.persistence

import com.hexarchbootdemo.domain.model.Voter
import org.springframework.stereotype.Repository

/**
 * In-memory trivial "database" meant to demonstrate component responsibilities.
 *
 * TODO use TestContainers with Postgres and jOOQ.
 */
@Repository
internal class VoterRepository {
    // This is where you would use jOOQ or Spring Data for example depending on your type of repository.

    fun findAll(): List<Voter> {
        return listOf(Voter(firstName = "Dustin", lastName = "Shimono"))
    }
}
