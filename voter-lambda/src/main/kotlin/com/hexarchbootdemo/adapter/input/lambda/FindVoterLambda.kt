package com.hexarchbootdemo.adapter.input.lambda

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.hexarchbootdemo.adapter.input.lambda.data.FindVoterCommand
import com.hexarchbootdemo.adapter.input.lambda.data.VoterJson
import com.hexarchbootdemo.application.port.input.FindVoterUseCase.FindByLastNameQuery
import com.hexarchbootdemo.application.port.output.repository.FindVoterPort
import javax.inject.Inject
import javax.inject.Named

@Named("findVoter")
private class FindVoterLambda : RequestHandler<FindVoterCommand, List<VoterJson>> {
    @Inject
    lateinit var findVoterPort: FindVoterPort

    override fun handleRequest(command: FindVoterCommand, context: Context): List<VoterJson> {

        val voters = findVoterPort.findVotersByLastName(FindByLastNameQuery(command.lastName))
                .map {
                    VoterJson(id = it.id,
                            socialSecurityNumber = it.socialSecurityNumber.toString(),
                            firstName = it.firstName,
                            lastName = it.lastName)
                }
        println("Found Voters: $voters")
        return voters
    }
}
