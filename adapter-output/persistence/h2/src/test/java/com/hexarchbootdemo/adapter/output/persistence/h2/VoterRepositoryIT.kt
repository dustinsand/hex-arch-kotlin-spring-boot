package com.hexarchbootdemo.adapter.output.persistence.h2

import com.hexarchbootdemo.adapter.output.persistence.h2.internal.VoterPersistenceH2Adapter
import com.hexarchbootdemo.application.port.input.FindVoterUseCase
import com.hexarchbootdemo.application.port.input.RegisterVoterUseCase.RegisterVoterCommand
import com.hexarchbootdemo.domain.model.SocialSecurityNumber
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runBlockingTest
import org.assertj.core.api.Assertions.assertThat
import org.flywaydb.core.Flyway
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension

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
        runBlockingTest {
            val voters = voterRepo.findVotersByLastNameReactive(
                    FindVoterUseCase.FindByLastNameQuery("shimono")).toList(mutableListOf())

            assertThat(voters.size).isEqualTo(2)

            assertThat(voters[0].id).isNotNull()
            assertThat(voters[0].firstName).isEqualTo("Dustin")
            assertThat(voters[0].lastName).isEqualTo("Shimono")
            assertThat(voters[0].socialSecurityNumber.toString()).isEqualTo("111-11-1111")

            assertThat(voters[1].id).isNotNull()
            assertThat(voters[1].firstName).isEqualTo("Sandy")
            assertThat(voters[1].lastName).isEqualTo("Shimono")
            assertThat(voters[1].socialSecurityNumber.toString()).isEqualTo("222-22-2222")
        }
    }

    @Test
    fun `should save new voter`() {
        runBlockingTest {
            val voterId = voterRepo.saveReactive(RegisterVoterCommand(SocialSecurityNumber("555-55-5555"), "Joe", "Reactive"))
            assertThat(voterId).isNotNull()

            val voters = voterRepo.findVotersByLastNameReactive(
                    FindVoterUseCase.FindByLastNameQuery("Reactive")).toList(mutableListOf())

            assertThat(voters.size).isEqualTo(1)
            assertThat(voters[0].id).isEqualTo(voterId)
            assertThat(voters[0].firstName).isEqualTo("Joe")
            assertThat(voters[0].lastName).isEqualTo("Reactive")
            assertThat(voters[0].socialSecurityNumber.toString()).isEqualTo("555-55-5555")
        }
    }
}
