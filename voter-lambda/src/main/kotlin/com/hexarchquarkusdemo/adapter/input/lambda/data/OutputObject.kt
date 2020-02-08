package com.hexarchquarkusdemo.adapter.input.lambda.data

data class OutputObject(val result: String, val requestId: String) {
    // Why is the JacksonObjectMapperCustomer not used forcing me to create this empty constructor??
    constructor() : this("empty", "empty")
}
