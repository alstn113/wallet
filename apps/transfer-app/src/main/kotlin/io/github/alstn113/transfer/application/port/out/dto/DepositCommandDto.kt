package io.github.alstn113.transfer.application.port.out.dto

import java.math.BigDecimal

data class DepositCommandDto(
    val walletId: Long,
    val amount: BigDecimal,
    val transactionId: String,
)