package com.hexarchquarkusdemo.adapter.input.lambda.data

data class FindVoterCommand(val lastName: String) {
    // Why is the JacksonObjectMapperCustomer not used forcing me to create this empty constructor??
    constructor() : this("not provided")
}
