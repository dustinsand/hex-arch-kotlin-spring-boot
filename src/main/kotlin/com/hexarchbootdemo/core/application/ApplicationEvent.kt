package com.hexarchbootdemo.core.application


class ApplicationEvent {
    data class PersonCreated(val person: PeopleService.PersonDto)
}