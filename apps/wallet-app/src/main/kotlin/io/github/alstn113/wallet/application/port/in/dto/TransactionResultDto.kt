package io.github.alstn113.wallet.application.port.`in`.dto

import io.github.alstn113.wallet.domain.TransactionType
import java.math.BigDecimal

data class TransactionResultDto(
    val id: Long,
    val walletId: Long,
    val amount: BigDecimal,
    val balance: BigDecimal,
    val transactionId: String,
    val type: TransactionType,
)
