package io.github.alstn113.wallet.adapter.out.jpa.entity

import io.github.alstn113.wallet.domain.TransactionType
import jakarta.persistence.*
import java.math.BigDecimal

@Entity
class WalletTransactionEntity(
    @Column(nullable = false)
    val walletId: Long,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    val type: TransactionType,

    @Column(nullable = false)
    val amount: BigDecimal,

    @Column(nullable = false)
    val balanceSnapshot: BigDecimal,

    @Column(nullable = false)
    val transactionId: String,
) : AuditableEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0
}
