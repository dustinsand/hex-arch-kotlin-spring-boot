package com.hexarchbootdemo.adapter.output.persistence.h2

import com.hexarchbootdemo.adapter.output.persistence.h2.generated_sources.jooq.tables.Voter.VOTER
import com.hexarchbootdemo.application.port.input.FindVoterUseCase.FindByLastNameQuery
import com.hexarchbootdemo.application.port.input.RegisterVoterUseCase
import com.hexarchbootdemo.application.port.output.persistence.FindVoterPort
import com.hexarchbootdemo.application.port.output.persistence.RegisterVoterPort
import com.hexarchbootdemo.domain.model.Voter
import org.jooq.DSLContext
import java.util.UUID
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import java.util.stream.Collectors

@Repository("VoterPersistenceH2Adapter")
class VoterPersistenceH2Adapter @Autowired constructor(val dslContext: DSLContext) : FindVoterPort, RegisterVoterPort {

    override fun findVotersByLastName(query: FindByLastNameQuery): List<Voter> {
        return dslContext.select().from(VOTER)
                .where(VOTER.LAST_NAME.equalIgnoreCase(query.lastNameContains))
                .fetch()
                .stream()
                .map { it -> Voter(it.getValue(VOTER.ID), it.getValue(VOTER.FIRST_NAME), it.getValue(VOTER.LAST_NAME)) }
                .collect(Collectors.toList())
    }

    override fun save(command: RegisterVoterUseCase.RegisterVoterCommand): UUID {
        // Assume validation logic of command performed here

//        val id = UUID.randomUUID()
//        voterRepo.save(Voter(id, command.firstName, command.lastName))
//        return id
        return UUID.randomUUID()
    }
}
