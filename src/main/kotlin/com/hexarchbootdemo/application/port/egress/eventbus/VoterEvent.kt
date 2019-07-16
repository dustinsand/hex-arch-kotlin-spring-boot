package com.hexarchbootdemo.application.port.egress.eventbus

import com.hexarchbootdemo.application.port.ingress.FindVoterUseCase.VoterDto

// Used for emitting to an event stream.
//
// TODO Use Protobuf for this class
class VoterEvent {
    data class VoterRegistered(val person: VoterDto)
}