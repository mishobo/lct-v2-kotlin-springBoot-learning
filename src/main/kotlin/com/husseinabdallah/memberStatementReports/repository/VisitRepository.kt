package com.husseinabdallah.memberStatementReports.repository

import com.husseinabdallah.memberStatementReports.models.MemberStatementDTO
import com.husseinabdallah.memberStatementReports.models.Visit
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface VisitRepository : JpaRepository<Visit, Long> {

    @Query(
        "SELECT new com.husseinabdallah.memberStatementReports.models.MemberStatementDTO(v.memberName, "
                + "v.memberNumber, v.benefitName, v.payerId, v.totalInvoiceAmount,v.invoiceNumber, v.createdAt, "
                + "v.hospitalProviderId, v.aggregateId, bb.initialLimit) FROM Visit v inner join BeneficiaryBenefit bb on " +
                "bb.aggregateId = v.aggregateId where v.memberNumber = :memberNumber",
        nativeQuery = true
    )
    fun findByMemberNumber(memberNumber : String) : MutableList<MemberStatementDTO>
}