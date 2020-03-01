package com.hexarchbootdemo.adapter.output.persistence.h2

import com.hexarchbootdemo.JooqApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class JooqApplication

fun main(args: Array<String>) {
    runApplication<JooqApplication>(*args)
}
