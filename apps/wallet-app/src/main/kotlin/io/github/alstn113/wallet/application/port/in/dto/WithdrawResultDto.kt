package io.github.alstn113.wallet.application.port.`in`.dto

import java.math.BigDecimal

data class WithdrawResultDto(
    val id: Long,
    val walletId: Long,
    val amount: BigDecimal,
    val balance: BigDecimal,
    val transactionId: String,
)
