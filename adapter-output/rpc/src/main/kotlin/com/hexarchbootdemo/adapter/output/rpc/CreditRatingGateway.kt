package com.hexarchbootdemo.adapter.output.rpc

import org.valiktor.functions.isBetween
import org.valiktor.validate

// Example of a REST Gateway

interface CreditRatingGateway {
    fun findByName(firstName: String, lastName: String): CreditReport
}

class CreditReportGatewayExperian : CreditRatingGateway {
    override fun findByName(firstName: String, lastName: String): CreditReport {
        // Imagine a REST call was made to Experian
        return CreditReport(725)
    }
}

data class CreditReport(val fica: Int) {
    init {
        validate(this) {
            validate(CreditReport::fica).isBetween(start = 300, end = 850)
        }
    }
}
