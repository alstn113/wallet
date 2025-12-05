package io.github.alstn113.transfer.adapter.out.jpa.entity

import io.github.alstn113.transfer.domain.TransferState
import jakarta.persistence.*

@Entity
class TransferLogEntity(
    @Column(nullable = false)
    val transactionId: String,

    @Column(nullable = false)
    val state: TransferState,
) : AuditableEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0
}