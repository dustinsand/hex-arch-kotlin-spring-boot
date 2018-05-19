package com.hexarchbootdemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@SpringBootApplication
class HexArchBootDemoApplication

fun main(args: Array<String>) {
    runApplication<HexArchBootDemoApplication>(*args)
}

@Configuration
@ComponentScan
class MainConfig