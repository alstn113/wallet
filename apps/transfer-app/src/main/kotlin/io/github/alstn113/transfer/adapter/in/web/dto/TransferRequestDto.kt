package io.github.alstn113.transfer.adapter.`in`.web.dto

import io.github.alstn113.transfer.application.port.`in`.dto.TransferCommandDto
import java.math.BigDecimal

data class TransferRequestDto(
    val fromWalletId: Long,
    val toWalletId: Long,
    val amount: BigDecimal,
    val transactionId: String,
) {

    fun toCommand(): TransferCommandDto {
        return TransferCommandDto(
            fromWalletId = fromWalletId,
            toWalletId = toWalletId,
            amount = amount,
            transactionId = transactionId
        )
    }
}