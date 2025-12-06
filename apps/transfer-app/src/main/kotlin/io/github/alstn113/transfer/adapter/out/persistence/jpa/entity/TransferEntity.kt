package io.github.alstn113.transfer.adapter.out.persistence.jpa.entity

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
class TransferEntity(
    @Column(nullable = false)
    val fromWalletId: Long,

    @Column(nullable = false)
    val toWalletId: Long,

    @Column(nullable = false)
    val amount: BigDecimal,

    @Column(nullable = false, unique = true)
    val transactionId: String,
) : AuditableEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0
}