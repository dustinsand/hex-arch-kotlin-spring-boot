package com.hexarchbootdemo.core.application


interface FindPeopleService {
    fun find(request: Request): Response

    data class Request(val nameContains: String?)

    data class Response(val people: List<Person>) {
        data class Person(val firstName: String)
    }
}