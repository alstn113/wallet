package io.github.alstn113.wallet.adapter.`in`.web.dto

import io.github.alstn113.wallet.application.port.`in`.dto.DepositCommandDto
import java.math.BigDecimal

data class DepositRequestDto(
    val amount: BigDecimal,
    val transactionId: String,
) {

    fun toCommand(walletId: Long): DepositCommandDto {
        return DepositCommandDto(
            walletId = walletId,
            amount = amount,
            transactionId = transactionId,
        )
    }
}
