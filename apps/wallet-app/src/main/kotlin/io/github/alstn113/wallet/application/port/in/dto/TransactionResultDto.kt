package io.github.alstn113.wallet.application.port.`in`.dto

import io.github.alstn113.wallet.domain.TransactionType
import io.github.alstn113.wallet.domain.WalletTransaction
import java.math.BigDecimal

data class TransactionResultDto(
    val id: Long,
    val walletId: Long,
    val amount: BigDecimal,
    val balance: BigDecimal,
    val transactionId: String,
    val type: TransactionType,
) {

    companion object {
        fun from(transaction: WalletTransaction): TransactionResultDto {
            return TransactionResultDto(
                id = transaction.id,
                walletId = transaction.walletId,
                amount = transaction.amount,
                balance = transaction.balanceSnapshot,
                transactionId = transaction.transactionId,
                type = transaction.type,
            )
        }
    }
}