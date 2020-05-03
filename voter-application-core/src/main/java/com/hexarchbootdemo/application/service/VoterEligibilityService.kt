package com.hexarchbootdemo.application.service

import com.hexarchbootdemo.application.port.input.VoterEligibilityUseCase
import com.hexarchbootdemo.application.port.output.repository.VoterEligibilityPort
import javax.inject.Named

@Named
class VoterEligibilityService(val voterEligibilityPort: VoterEligibilityPort) : VoterEligibilityUseCase {
    override suspend fun isEligible(query: VoterEligibilityUseCase.VoterEligibilityQuery): Boolean {
        return voterEligibilityPort.isEligible(query)
    }
}
