package com.hexarchbootdemo.application.port.egress

import com.hexarchbootdemo.application.port.ingress.FindVotersUseCase.VoterDto

// Picture used to publish to an event stream. Protobuf would be used though, not java or json serialization.
class VoterEvent {
    data class VoterRegistered(val person: VoterDto)
}