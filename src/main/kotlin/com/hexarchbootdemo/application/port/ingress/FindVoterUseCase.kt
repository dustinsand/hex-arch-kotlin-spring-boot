package com.hexarchbootdemo.application.port.ingress


interface FindVoterUseCase {

    fun findByName(command: FindByCriteriaQuery): List<VoterDto>

    data class FindByCriteriaQuery(val nameContains: String)

    data class VoterDto(val firstName: String, val lastName: String) {
        fun firstInitial(): Char {
            return firstName[0]
        }
    }
}