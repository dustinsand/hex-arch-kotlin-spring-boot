package com.hexarchquarkusdemo.adapter.input.lambda

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import io.quarkus.jackson.ObjectMapperCustomizer
import javax.inject.Singleton

// TODO why is this not being recognized?
@Singleton
class JacksonObjectMapperCustomizer : ObjectMapperCustomizer {
    override fun customize(mapper: ObjectMapper) {
        // register kotlin module for data classes
        mapper.registerModule(KotlinModule())
    }
}
