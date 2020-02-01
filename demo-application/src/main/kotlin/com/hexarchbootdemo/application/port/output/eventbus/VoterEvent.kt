package com.hexarchbootdemo.application.port.output.eventbus

import com.hexarchbootdemo.domain.model.Voter

// Used for emitting to an event stream.
//
// TODO Use Protobuf for this class
class VoterEvent {
    data class VoterRegistered(val voter: Voter)
}
