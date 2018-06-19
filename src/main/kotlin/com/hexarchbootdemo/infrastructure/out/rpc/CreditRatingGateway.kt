package com.hexarchbootdemo.infrastructure.out.rpc

// Example of a REST Gateway

interface CreditRatingGateway {
    fun findByName(firstName: String, lastName: String): CreditReport
}

class CreditReportGatewayExperian: CreditRatingGateway {
    override fun findByName(firstName: String, lastName: String): CreditReport {
        // Picture a REST call was made to Experian
        return CreditReport(725)
    }
}

data class CreditReport(val fica: Int)