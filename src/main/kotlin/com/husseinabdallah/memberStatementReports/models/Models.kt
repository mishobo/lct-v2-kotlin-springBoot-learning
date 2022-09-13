package com.husseinabdallah.memberStatementReports.models

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.*

enum class MIDDLEWARENAME {
    AVENUE, MATER, NAIROBIHOSPITAL, GETRUDES, MPSHAH, METROPOLITAN, AGAKHANKISUMU, AGAKHANMOMBASA,
    AGAKHANNAIROBI, NONE
}
enum class MiddlewareStatus {
    SENT, UNSENT
}

enum class Status {
    ACTIVE, CLOSED, LINE_ITEMS_ADDED, DIAGNOSIS_ADDED, PENDING, ABANDONED,
    TRANSMITTED, SETTLED, REJECTED
}

enum class ClaimProcessStatus {
    PROCESSED, UNPROCESSED
}

@Entity
@Table(name = "visit", uniqueConstraints = [UniqueConstraint(name = "provider_invoiceNumber_UNQ",
    columnNames = ["hospital_provider_id", "invoice_number"])])
data class Visit(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "visit_number")
    val id: Long = 0,
    @Column(name = "member_number")
    val memberNumber: String,

    @Column(name = "member_name")
    val memberName: String,

    @Column(name = "hospital_provider_id")
    val hospitalProviderId: Long? = null,

    @Column(name = "staff_id")
    val staffId: String,

    @Column(name = "staff_name")
    val staffName: String,

    @Column(name = "aggregate_id")
    val aggregateId: String,

    @Column(name = "category_id")
    val categoryId: String,

    @Column(name = "benefit_name")
    val benefitName: String,

    @Column(name = "beneficiaryId")
    val beneficiaryId: Long? = null,

    @Column(name = "benefit_id")
    val benefitId: Long? = null,

    @Column(name = "payer_id")
    val payerId: String,

    @Column(name = "policy_number")
    val policyNumber: String? = null,

    @Column(name = "balance_amount")
    var balanceAmount: BigDecimal,

    @Column(name = "beneficiary_type")
    val beneficiaryType: String? = null,

    @Column(name = "total_invoice_amount")
    var totalInvoiceAmount: BigDecimal? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "provider_middleware")
    val providerMiddleware: MIDDLEWARENAME?,

    @Column(name = "invoice_number")
    var invoiceNumber: String? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    var status: Status? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "middlewareStatus")
    var middlewareStatus: MiddlewareStatus? = MiddlewareStatus.UNSENT,

    @Enumerated(EnumType.STRING)
    @Column(name = "claim_process_status")
    var claimProcessStatus: ClaimProcessStatus? = ClaimProcessStatus.UNPROCESSED,


    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "created_at", updatable = false, nullable = false)
    val createdAt: LocalDateTime? = null,

    @UpdateTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "updated_at", insertable = false)
    val updatedAt: LocalDateTime? = null,


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "visit_end")
    var visitEnd: LocalDateTime? = null,

    @OneToMany(mappedBy = "visit", fetch = FetchType.EAGER)
    var diagnosis: List<Diagnosis>? = null,
){
    override fun toString(): String {
        return "Visit (id=$id, name=$memberNumber, hospitalProviderId=$hospitalProviderId)"
    }
}

@Entity
@Table(name = "diagnosis")
data class Diagnosis(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long = 0,
    val code: String?,
    var title: String?,

    @Column(name = "invoice_number")
    var invoiceNumber: String? = null,
    @Column(name = "claim_ref")
    var claimRef: String? = null,

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "visit_number", nullable = false)
    var visit: Visit?,
)