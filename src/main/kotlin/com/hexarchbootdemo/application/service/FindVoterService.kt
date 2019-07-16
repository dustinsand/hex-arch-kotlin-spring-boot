package com.hexarchbootdemo.application.service

import com.hexarchbootdemo.application.port.egress.persistence.FindVoterPort
import com.hexarchbootdemo.application.port.ingress.FindVoterUseCase
import com.hexarchbootdemo.application.port.ingress.FindVoterUseCase.FindByCriteriaQuery
import com.hexarchbootdemo.application.port.ingress.FindVoterUseCase.VoterDto
import com.hexarchbootdemo.domain.model.Voter
import org.springframework.stereotype.Service

@Service
class FindVotersService(val findVoterRepository: FindVoterPort) : FindVoterUseCase {
    override fun findByName(command: FindByCriteriaQuery): List<VoterDto> {
        return findVoterRepository.findVoters(command).map { it.toDto() }
    }
}

fun Voter.toDto(): VoterDto {
    return VoterDto(firstName, lastName)
}