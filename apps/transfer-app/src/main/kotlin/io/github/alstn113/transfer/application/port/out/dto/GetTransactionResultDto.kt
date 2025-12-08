package io.github.alstn113.transfer.application.port.out.dto

import java.math.BigDecimal

data class GetTransactionResultDto(
    val id: Long,
    val walletId: Long,
    val amount: BigDecimal,
    val balance: BigDecimal,
    val transactionId: String,
    val type: String,
)
