package com.hexarchbootdemo.application.service

import com.hexarchbootdemo.application.port.egress.FindVoterPort
import com.hexarchbootdemo.application.port.ingress.FindVotersUseCase
import com.hexarchbootdemo.application.port.ingress.FindVotersUseCase.FindByNameQuery
import com.hexarchbootdemo.application.port.ingress.FindVotersUseCase.VoterDto
import com.hexarchbootdemo.domain.model.Voter
import org.springframework.stereotype.Service

@Service
class FindVotersService(val findVoterRepository: FindVoterPort) : FindVotersUseCase {
    override fun findByName(name: String): List<VoterDto> {
        return findVoterRepository.findVoters(FindByNameQuery(name)).map { it.toDto() }
    }
}

fun Voter.toDto(): VoterDto {
    return VoterDto(firstName, lastName)
}