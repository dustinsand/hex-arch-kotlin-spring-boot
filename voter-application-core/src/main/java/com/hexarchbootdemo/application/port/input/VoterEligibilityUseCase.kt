package com.hexarchbootdemo.application.port.input

import com.hexarchbootdemo.domain.model.SocialSecurityNumber

interface VoterEligibilityUseCase {

    suspend fun isEligible(query: VoterEligibilityQuery): Boolean

    data class VoterEligibilityQuery(val ssn: SocialSecurityNumber)
}
