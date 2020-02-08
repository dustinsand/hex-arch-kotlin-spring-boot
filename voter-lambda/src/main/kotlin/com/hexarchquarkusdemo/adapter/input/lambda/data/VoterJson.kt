package com.hexarchquarkusdemo.adapter.input.lambda.data

import java.util.UUID

data class VoterJson(val id: UUID, val firstName: String, val lastName: String) {
    // Why is the JacksonObjectMapperCustomer not used forcing me to create this empty constructor??
    constructor() : this(UUID.randomUUID(), "not provided", "not provided")
}
