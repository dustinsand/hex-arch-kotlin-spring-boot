package org.acme

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.hexarchbootdemo.application.port.input.FindVoterUseCase.FindByLastNameQuery
import com.hexarchbootdemo.application.port.output.persistence.FindVoterPort
import javax.inject.Inject
import javax.inject.Named

@Named("test")
class TestLambda : RequestHandler<InputObject, OutputObject> {
    @Inject
    lateinit var findVoterPort: FindVoterPort

    override fun handleRequest(input: InputObject, context: Context): OutputObject {

        println("Voter[0] firstname = " + findVoterPort.findVotersByLastName(FindByLastNameQuery("Shimono"))[0].firstName)

        val result: String = input.greeting + " " + input.name
        return OutputObject(result, context.awsRequestId)
    }
}