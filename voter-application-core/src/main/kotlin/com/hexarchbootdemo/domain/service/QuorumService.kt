package com.hexarchbootdemo.domain.service

import com.hexarchbootdemo.domain.model.Voter
import org.springframework.stereotype.Service

/**
 * Example of a domain service because business logic for a set of voters does not belong in the application layer.
 */
@Service
class QuorumService {

    fun haveQuorum(voters: List<Voter>, membershipSize: Int): Boolean {
        val percent: Float = (voters.size / membershipSize.toFloat())
        return percent >= .8
    }
}
