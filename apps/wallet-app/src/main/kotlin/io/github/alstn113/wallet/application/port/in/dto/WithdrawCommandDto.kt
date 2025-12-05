package io.github.alstn113.wallet.application.port.`in`.dto

import java.math.BigDecimal

data class WithdrawCommandDto(
    val walletId: Long,
    val amount: BigDecimal,
    val transactionId: String,
)
