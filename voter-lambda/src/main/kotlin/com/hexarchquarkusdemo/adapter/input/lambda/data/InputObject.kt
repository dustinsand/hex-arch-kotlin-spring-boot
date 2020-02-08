package com.hexarchquarkusdemo.adapter.input.lambda.data

data class InputObject(val name: String, val greeting: String) {
    // Why is the JacksonObjectMapperCustomer not used forcing me to create this empty constructor??
    constructor() : this("empty", "empty")
}
