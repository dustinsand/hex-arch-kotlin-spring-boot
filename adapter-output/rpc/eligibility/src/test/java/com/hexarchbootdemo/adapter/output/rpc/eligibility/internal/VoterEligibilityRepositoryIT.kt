package com.hexarchbootdemo.adapter.output.rpc.eligibility.internal

import com.hexarchbootdemo.application.port.input.VoterEligibilityUseCase
import com.hexarchbootdemo.domain.model.SocialSecurityNumber
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest(classes = [EligibilityTestApplication::class])
class VoterEligibilityRepositoryIT {

    @Autowired
    private lateinit var voterEligiblityRepo: VoterEligibilityRepository

    @Test
    fun `voter is eligible`() {
        runBlocking {
            val voterIsEligible = voterEligiblityRepo.isEligible(
                    VoterEligibilityUseCase.VoterEligibilityQuery(SocialSecurityNumber("999-99-9999")))

            assertThat(voterIsEligible).isTrue()
        }
    }
}
