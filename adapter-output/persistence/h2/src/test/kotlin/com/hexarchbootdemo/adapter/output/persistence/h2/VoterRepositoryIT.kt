package com.hexarchbootdemo.adapter.output.persistence.h2

import com.hexarchbootdemo.JooqApplication
import com.hexarchbootdemo.application.port.input.FindVoterUseCase
import org.assertj.core.api.Assertions.assertThat
import org.flywaydb.test.annotation.FlywayTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import reactor.test.StepVerifier

@ExtendWith(SpringExtension::class)
@SpringBootTest(classes = [JooqApplication::class])
class VoterRepositoryIT {

    @BeforeEach
    @FlywayTest(locationsForMigrate = ["/db/test/h2"])
    fun beforeClass() {
    }

    @Autowired
    @Qualifier("VoterPersistenceH2Adapter")
    private lateinit var voterRepo: VoterPersistenceH2Adapter

    @Test
    fun `should find voters by last name`() {
        val voters = voterRepo.findVotersByLastName(FindVoterUseCase.FindByLastNameQuery("shimono"))

        assertThat(voters).hasSize(2)
        assertThat(voters).extracting("firstName").contains("Dustin", "Sandy")
    }

    @Test
    fun `should find voters by last name using NIO`() {
        val voters = voterRepo.findVotersByLastNameNIO(FindVoterUseCase.FindByLastNameQuery("shimono"))

        println("before")
        voters.subscribe { v ->
            println(v.firstName)
        }
        println("after")

        Thread.sleep(100)
//        StepVerifier.create(voters)
//                .thenConsumeWhile { v ->
//                    assertThat(v.firstName).isEqualTo()
//                }
//                .verifyComplete()
//        assertThat(voters).hasSize(2)
//        assertThat(voters).extracting("firstName").contains("Dustin", "Sandy")
    }
}
