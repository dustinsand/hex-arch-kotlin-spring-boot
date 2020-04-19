package com.hexarchbootdemo.application.port.output.repository

import com.hexarchbootdemo.application.port.input.VoterEligibilityUseCase.VoterEligibilityQuery

interface VoterEligibilityPort {
    suspend fun isEligible(query: VoterEligibilityQuery): Boolean
}
