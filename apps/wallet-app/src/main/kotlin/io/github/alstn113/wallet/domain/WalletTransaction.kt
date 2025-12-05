package io.github.alstn113.wallet.domain

import java.math.BigDecimal

data class WalletTransaction private constructor(
    val id: Long,
    val walletId: Long,
    val amount: BigDecimal,
    val type: TransactionType,
    val balanceSnapshot: BigDecimal,
    val transactionId: String,
) {

    companion object {
        fun create(
            walletId: Long,
            amount: BigDecimal,
            type: TransactionType,
            balanceSnapshot: BigDecimal,
            transactionId: String,
        ): WalletTransaction {
            return WalletTransaction(
                id = 0,
                walletId = walletId,
                amount = amount,
                type = type,
                balanceSnapshot = balanceSnapshot,
                transactionId = transactionId,
            )
        }
    }
}