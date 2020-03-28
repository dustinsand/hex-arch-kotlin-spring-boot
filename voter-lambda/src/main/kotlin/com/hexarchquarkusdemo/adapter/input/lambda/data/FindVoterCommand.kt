package com.hexarchquarkusdemo.adapter.input.lambda.data

import com.hexarchbootdemo.domain.LAST_NAME_MAX_LENGTH
import com.hexarchbootdemo.domain.LAST_NAME_MIN_LENGTH
import org.valiktor.functions.hasSize
import org.valiktor.validate

internal data class FindVoterCommand(val lastName: String) {
    // Why is the JacksonObjectMapperCustomer not used forcing me to create this empty constructor??
    constructor() : this("not provided")

    init {
        validate(this) {
            validate(FindVoterCommand::lastName).hasSize(min = LAST_NAME_MIN_LENGTH, max = LAST_NAME_MAX_LENGTH)
        }
    }
}
