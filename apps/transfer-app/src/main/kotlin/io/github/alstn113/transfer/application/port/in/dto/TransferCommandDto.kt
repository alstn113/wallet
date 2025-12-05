package io.github.alstn113.transfer.application.port.`in`.dto

import java.math.BigDecimal

data class TransferCommandDto(
    val fromWalletId: Long,
    val toWalletId: Long,
    val amount: BigDecimal,
    val transactionId: String,
)
