package io.github.alstn113.wallet.application.port.`in`.dto

import java.math.BigDecimal

data class DepositResultDto(
    val id: Long,
    val walletId: Long,
    val amount: BigDecimal,
    val balance: BigDecimal,
    val transactionId: String,
)
