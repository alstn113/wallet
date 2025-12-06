package io.github.alstn113.wallet.adapter.out.persistence.jpa.entity

import io.github.alstn113.wallet.domain.TransactionType
import io.github.alstn113.wallet.domain.WalletTransaction
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

    fun toDomain(): WalletTransaction {
        return WalletTransaction.reconstruct(
            id = this.id,
            walletId = this.walletId,
            type = this.type,
            amount = this.amount,
            balanceSnapshot = this.balanceSnapshot,
            transactionId = this.transactionId,
        )
    }
}
