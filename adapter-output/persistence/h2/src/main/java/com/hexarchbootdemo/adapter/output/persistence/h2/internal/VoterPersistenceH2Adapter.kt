package com.hexarchbootdemo.adapter.output.persistence.h2.internal

import com.hexarchbootdemo.adapter.output.persistence.h2.generated_sources.jooq.tables.Voter.VOTER
import com.hexarchbootdemo.application.port.input.FindVoterUseCase.FindByLastNameQuery
import com.hexarchbootdemo.application.port.input.RegisterVoterUseCase.RegisterVoterCommand
import com.hexarchbootdemo.application.port.output.repository.FindVoterPort
import com.hexarchbootdemo.application.port.output.repository.RegisterVoterPort
import com.hexarchbootdemo.domain.model.SocialSecurityNumber
import com.hexarchbootdemo.domain.model.Voter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow
import org.jooq.DSLContext
import org.jooq.conf.ParamType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.data.r2dbc.core.await
import org.springframework.stereotype.Repository
import java.util.UUID
import java.util.stream.Collectors

@Repository("VoterPersistenceH2Adapter")
internal class VoterPersistenceH2Adapter @Autowired constructor(val dslContext: DSLContext, val databaseClient: DatabaseClient) : FindVoterPort, RegisterVoterPort {

    override fun findVotersByLastName(query: FindByLastNameQuery): List<Voter> {
        return dslContext.select().from(VOTER)
                .where(VOTER.LAST_NAME.equalIgnoreCase(query.lastNameContains))
                .fetch()
                .stream()
                .map {
                    Voter(
                            id = it.getValue(VOTER.ID),
                            socialSecurityNumber = SocialSecurityNumber(it.getValue(VOTER.SOCIAL_SECURITY_NUMBER)),
                            firstName = it.getValue(VOTER.FIRST_NAME),
                            lastName = it.getValue(VOTER.LAST_NAME))
                }
                .collect(Collectors.toList())
    }

    override suspend fun findVotersByLastNameReactive(query: FindByLastNameQuery): Flow<Voter> {
        return databaseClient.execute(
                dslContext.select().from(VOTER)
                        .where(VOTER.LAST_NAME.equalIgnoreCase(query.lastNameContains))
                        .getSQL(ParamType.INLINED))
                .map { source, _ ->
                    Voter(id = source.get(VOTER.ID.name, VOTER.ID.type)!!,
                            socialSecurityNumber =
                            SocialSecurityNumber(source.get(VOTER.SOCIAL_SECURITY_NUMBER.name, VOTER.SOCIAL_SECURITY_NUMBER.type)!!),
                            firstName = source.get(VOTER.FIRST_NAME.name, VOTER.FIRST_NAME.type)!!,
                            lastName = source.get(VOTER.LAST_NAME.name, VOTER.LAST_NAME.type)!!)
                }
                .all()
                .asFlow()
    }

    override fun save(command: RegisterVoterCommand): UUID {
        val id = UUID.randomUUID()
        dslContext.insertInto(VOTER).columns(VOTER.ID, VOTER.SOCIAL_SECURITY_NUMBER, VOTER.FIRST_NAME, VOTER.LAST_NAME)
                .values(id, command.socialSecurityNumber.toString(), command.firstName, command.lastName)
                .execute()
        return id
    }

    override suspend fun saveReactive(command: RegisterVoterCommand): UUID {
        // Assume validation logic of command performed here

        val id = UUID.randomUUID()
        databaseClient.execute(
                dslContext.insertInto(VOTER, VOTER.ID, VOTER.SOCIAL_SECURITY_NUMBER, VOTER.FIRST_NAME, VOTER.LAST_NAME)
                        .values(id, command.socialSecurityNumber.toString(), command.firstName, command.lastName)
                        .getSQL(ParamType.INLINED))
                .await()

        return id
    }
}
