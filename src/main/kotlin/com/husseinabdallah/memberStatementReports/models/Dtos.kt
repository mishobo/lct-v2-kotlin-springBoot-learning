package com.husseinabdallah.memberStatementReports.models

import java.math.BigDecimal

data class MemberStatementDTO(
    val memberName: String?,
    val memberNumber: String?,
    val benefitName: String?,
    val payerId: String?,
    val totalInvoiceAmount: BigDecimal?,
    val invoiceNumber: String?,
    val createdAt: String?,
    val hospitalProviderId: String?,
    val aggregateId:String?,
    val initialLimit: BigDecimal?
    )