package com.hexarchbootdemo.domain.service

import com.hexarchbootdemo.domain.model.Voter
import javax.inject.Named

/**
 * Example of a domain service because business logic for a set of voters does not belong in the application layer.
 */
@Named
class QuorumService {
    companion object {
        const val QUORUM_MIN_PERCENT = .8
    }

    fun haveQuorum(voters: List<Voter>, membershipSize: Int): Boolean {
        val percent: Float = (voters.size / membershipSize.toFloat())
        return percent >= QUORUM_MIN_PERCENT
    }
}
