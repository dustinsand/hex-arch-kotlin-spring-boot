package com.hexarchbootdemo.application.port.ingress


interface FindVotersUseCase {

    fun findByName(name: String): List<VoterDto>

    data class FindByNameQuery(val nameContains: String)

    data class VoterDto(val firstName: String, val lastName: String)
}