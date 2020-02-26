package com.hexarchbootdemo.adapter.output.persistence.h2

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class JooqApplication

fun main(args: Array<String>) {
    runApplication<JooqApplication>(*args)
}

// @Configuration
// class DataConfig {
//    @Bean
//    fun dataSource(): DataSource {
//        return DataSourceBuilder.create()
//                .driverClassName("org.postgresql.Driver")
//                .username("username")
//                .password("password")
//                .url("jdbc:postgresql://localhost:54321/voter")
//                .build()
//    }
// }
