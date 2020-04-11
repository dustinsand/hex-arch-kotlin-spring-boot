package com.hexarchbootdemo.adapter.output.persistence.h2

import com.hexarchbootdemo.adapter.output.persistence.h2.internal.VoterPersistenceH2Adapter
import com.hexarchbootdemo.application.port.input.FindVoterUseCase
import com.hexarchbootdemo.application.port.input.RegisterVoterUseCase.RegisterVoterCommand
import com.hexarchbootdemo.domain.model.SocialSecurityNumber
import org.assertj.core.api.Assertions.assertThat
import org.flywaydb.core.Flyway
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import reactor.test.StepVerifier

@ExtendWith(SpringExtension::class)
@SpringBootTest(classes = [JooqTestApplication::class])
class VoterRepositoryIT {
    @Autowired
    lateinit var flyway: Flyway

    @BeforeEach
    fun beforeEach() {
        flyway.clean()
        flyway.migrate()
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
    fun `should find voters by last name using Reactive driver`() {
        val votersFlux = voterRepo.findVotersByLastNameReactive(FindVoterUseCase.FindByLastNameQuery("shimono"))

        StepVerifier.create(votersFlux)
                .assertNext {
                    assertThat(it.id).isNotNull()
                    assertThat(it.firstName).isEqualTo("Dustin")
                    assertThat(it.lastName).isEqualTo("Shimono")
                }
                .assertNext {
                    assertThat(it.id).isNotNull()
                    assertThat(it.firstName).isEqualTo("Sandy")
                    assertThat(it.lastName).isEqualTo("Shimono")
                }
                .verifyComplete()
    }

    @Test
    fun `should save new voter`() {
        val voterIdMono = voterRepo.saveReactive(RegisterVoterCommand(SocialSecurityNumber("555-55-5555"), "Joe", "Reactive"))

        StepVerifier.create(voterIdMono)
                .assertNext {
                    assertThat(it).isNotNull()
                }
                .verifyComplete()

        val votersFlux = voterRepo.findVotersByLastNameReactive(FindVoterUseCase.FindByLastNameQuery("Reactive"))

        StepVerifier.create(votersFlux)
                .assertNext {
                    assertThat(it.id).isNotNull()
                    assertThat(it.firstName).isEqualTo("Joe")
                    assertThat(it.lastName).isEqualTo("Reactive")
                    assertThat(it.socialSecurityNumber.toString()).isEqualTo("555-55-5555")
                }
                .verifyComplete()
    }
}
