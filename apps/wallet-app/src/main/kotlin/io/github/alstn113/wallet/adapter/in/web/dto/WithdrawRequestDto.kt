package io.github.alstn113.wallet.adapter.`in`.web.dto

import io.github.alstn113.wallet.application.port.`in`.dto.WithdrawCommandDto
import java.math.BigDecimal

data class WithdrawRequestDto(
    val amount: BigDecimal,
    val transactionId: String,
) {

    fun toCommand(walletId: Long): WithdrawCommandDto {
        return WithdrawCommandDto(
            walletId = walletId,
            amount = amount,
            transactionId = transactionId,
        )
    }
}