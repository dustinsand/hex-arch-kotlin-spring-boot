package com.hexarchbootdemo.adapter.output.persistence.h2

import io.r2dbc.spi.ConnectionFactories
import io.r2dbc.spi.ConnectionFactory
import io.r2dbc.spi.ConnectionFactoryOptions
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.core.DatabaseClient

@Configuration
class R2dbcConfig {

    @Bean
    fun connectionFactory(): ConnectionFactory {
        return ConnectionFactories.get(ConnectionFactoryOptions.builder()
                .option(ConnectionFactoryOptions.DRIVER, "h2")
                .option(ConnectionFactoryOptions.PROTOCOL, "file")
                .option(ConnectionFactoryOptions.DATABASE, "~/VOTER")
                .option(ConnectionFactoryOptions.USER, "username")
                .option(ConnectionFactoryOptions.PASSWORD, "password")
                .build())
    }

    @Bean
    fun databaseClient(): DatabaseClient {
        return DatabaseClient.create(connectionFactory())
    }
}