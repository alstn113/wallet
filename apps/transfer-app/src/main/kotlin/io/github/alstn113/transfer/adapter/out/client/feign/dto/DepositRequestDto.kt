package io.github.alstn113.transfer.adapter.out.client.feign.dto

import io.github.alstn113.transfer.application.port.out.dto.DepositCommandDto
import java.math.BigDecimal

data class DepositRequestDto(
    val amount: BigDecimal,
    val transactionId: String,
) {

    companion object {
        fun from(command: DepositCommandDto): DepositRequestDto {
            return DepositRequestDto(
                amount = command.amount,
                transactionId = command.transactionId,
            )
        }
    }
}
