package com.hexarchbootdemo.domain.service

import com.hexarchbootdemo.domain.model.SocialSecurityNumber
import com.hexarchbootdemo.domain.model.Voter
import java.util.Collections
import java.util.UUID
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class QuorumServiceTest {
    @Test
    fun hasQuorum() {
        val quorumService = QuorumService()
        assertThat(true).isEqualTo(quorumService.haveQuorum(
                Collections.singletonList(Voter(firstName = "Adam", lastName = "West", id = UUID.randomUUID(), socialSecurityNumber = SocialSecurityNumber("888-99-9999"))), 1))
    }

    @Test
    fun noQuorum() {
        val quorumService = QuorumService()
        assertThat(false).isEqualTo(quorumService.haveQuorum(Collections.emptyList(), 10))
        assertThat(false).isEqualTo(quorumService.haveQuorum(
                Collections.singletonList(Voter(firstName = "Eve", lastName = "West", id = UUID.randomUUID(), socialSecurityNumber = SocialSecurityNumber("999-99-9999"))), 2))
    }
}
