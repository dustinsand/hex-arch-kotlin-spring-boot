package com.hexarchbootdemo.adapter.output.persistence.h2

import com.hexarchbootdemo.adapter.output.persistence.h2.generated_sources.jooq.tables.Voter.VOTER
import com.hexarchbootdemo.application.port.input.FindVoterUseCase.FindByLastNameQuery
import com.hexarchbootdemo.application.port.input.RegisterVoterUseCase.RegisterVoterCommand
import com.hexarchbootdemo.application.port.output.persistence.FindVoterPort
import com.hexarchbootdemo.application.port.output.persistence.RegisterVoterPort
import com.hexarchbootdemo.domain.model.Voter
import org.jooq.DSLContext
import org.jooq.conf.ParamType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Repository
import org.springframework.transaction.ReactiveTransactionManager
import org.springframework.transaction.reactive.TransactionalOperator
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.UUID
import java.util.stream.Collectors

@Repository("VoterPersistenceH2Adapter")
class VoterPersistenceH2Adapter @Autowired constructor(val dslContext: DSLContext, val databaseClient: DatabaseClient) : FindVoterPort, RegisterVoterPort {

    override fun findVotersByLastName(query: FindByLastNameQuery): List<Voter> {
        return dslContext.select().from(VOTER)
                .where(VOTER.LAST_NAME.equalIgnoreCase(query.lastNameContains))
                .fetch()
                .stream()
                .map { Voter(it.getValue(VOTER.ID), it.getValue(VOTER.FIRST_NAME), it.getValue(VOTER.LAST_NAME)) }
                .collect(Collectors.toList())
    }

    fun findVotersByLastNameReactive(query: FindByLastNameQuery): Flux<Voter> {
        return databaseClient.execute(
                        dslContext.select().from(VOTER)
                                .where(VOTER.LAST_NAME.equalIgnoreCase(query.lastNameContains))
                                .getSQL(ParamType.INLINED))
                .map { source, _ ->
                    Voter(source.get(VOTER.ID.name, VOTER.ID.type)!!,
                            source.get(VOTER.FIRST_NAME.name, VOTER.FIRST_NAME.type)!!,
                            source.get(VOTER.LAST_NAME.name, VOTER.LAST_NAME.type)!!)
                }
                .all()
    }

    override fun save(command: RegisterVoterCommand): UUID {
        throw NotImplementedError()
    }

    override fun saveReactive(command: RegisterVoterCommand): Mono<UUID> {
        // Assume validation logic of command performed here

        val id = UUID.randomUUID()
        return databaseClient.execute(
                dslContext.insertInto(VOTER, VOTER.ID, VOTER.FIRST_NAME, VOTER.LAST_NAME)
                        .values(id, command.firstName, command.lastName)
                        .getSQL(ParamType.INLINED))
                .then()
                .thenReturn(id)
    }
}
