package com.hexarchbootdemo.adapter.output.persistence.h2

import com.hexarchbootdemo.adapter.output.persistence.h2.generated_sources.jooq.tables.Voter.VOTER
import com.hexarchbootdemo.application.port.input.FindVoterUseCase.FindByLastNameQuery
import com.hexarchbootdemo.application.port.input.RegisterVoterUseCase
import com.hexarchbootdemo.application.port.output.persistence.FindVoterPort
import com.hexarchbootdemo.application.port.output.persistence.RegisterVoterPort
import com.hexarchbootdemo.domain.model.Voter
import io.r2dbc.spi.ConnectionFactories
import io.r2dbc.spi.ConnectionFactoryOptions
import io.r2dbc.spi.ConnectionFactoryOptions.DATABASE
import io.r2dbc.spi.ConnectionFactoryOptions.DRIVER
import io.r2dbc.spi.ConnectionFactoryOptions.PASSWORD
import io.r2dbc.spi.ConnectionFactoryOptions.PROTOCOL
import io.r2dbc.spi.ConnectionFactoryOptions.USER
import org.jooq.DSLContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import java.util.UUID
import java.util.stream.Collectors

@Repository("VoterPersistenceH2Adapter")
class VoterPersistenceH2Adapter @Autowired constructor(val dslContext: DSLContext) : FindVoterPort, RegisterVoterPort {

    override fun findVotersByLastName(query: FindByLastNameQuery): List<Voter> {
        return dslContext.select().from(VOTER)
                .where(VOTER.LAST_NAME.equalIgnoreCase(query.lastNameContains))
                .fetch()
                .stream()
                .map { Voter(it.getValue(VOTER.ID), it.getValue(VOTER.FIRST_NAME), it.getValue(VOTER.LAST_NAME)) }
                .collect(Collectors.toList())
    }

    fun findVotersByLastNameNIO(query: FindByLastNameQuery): Flux<Voter> {
        // val connectionFactory: ConnectionFactory = ConnectionFactories.get("r2dbc:h2:~/VOTER;SCHEMA=VOTER")
        val connectionFactory = ConnectionFactories.get(ConnectionFactoryOptions.builder()
                .option(DRIVER, "h2")
                .option(PROTOCOL, "file")
                .option(DATABASE, "~/VOTER")
                .option(USER, "username")
                .option(PASSWORD, "password")
                .build())

        val databaseClient = DatabaseClient.create(connectionFactory)

        return databaseClient.execute(
                dslContext.select().from(VOTER)
                        .where(VOTER.LAST_NAME.equalIgnoreCase(query.lastNameContains)).getSQL(true))
                .map { source, _ ->
                    Voter(source.get(VOTER.ID.name, VOTER.ID.type)!!,
                            source.get(VOTER.FIRST_NAME.name, VOTER.FIRST_NAME.type)!!,
                            source.get(VOTER.LAST_NAME.name, VOTER.LAST_NAME.type)!!)
                }
                .all()
    }

    override fun save(command: RegisterVoterUseCase.RegisterVoterCommand): UUID {
        // Assume validation logic of command performed here

//        val id = UUID.randomUUID()
//        voterRepo.save(Voter(id, command.firstName, command.lastName))
//        return id
        return UUID.randomUUID()
    }
}
