package com.hexarchbootdemo.domain.service

import com.hexarchbootdemo.domain.model.Voter
import java.util.Collections
import java.util.UUID
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class QuorumServiceTest {
    @Test
    fun hasQuorum() {
        val quorumService = QuorumService()
        assertThat(true).isEqualTo(quorumService.haveQuorum(
                Collections.singletonList(Voter(firstName = "Adam", lastName = "West", id = UUID.randomUUID())), 1))
    }

    @Test
    fun noQuorum() {
        val quorumService = QuorumService()
        assertThat(false).isEqualTo(quorumService.haveQuorum(Collections.emptyList(), 10))
        assertThat(false).isEqualTo(quorumService.haveQuorum(
                Collections.singletonList(Voter(firstName = "Eve", lastName = "West", id = UUID.randomUUID())), 2))
    }
}
