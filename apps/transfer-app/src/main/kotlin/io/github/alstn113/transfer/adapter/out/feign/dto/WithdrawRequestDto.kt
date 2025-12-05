package io.github.alstn113.transfer.adapter.out.feign.dto

import io.github.alstn113.transfer.application.port.out.dto.WithdrawCommandDto
import java.math.BigDecimal

data class WithdrawRequestDto(
    val amount: BigDecimal,
    val transactionId: String,
) {

    companion object {
        fun from(command: WithdrawCommandDto): WithdrawRequestDto {
            return WithdrawRequestDto(
                amount = command.amount,
                transactionId = command.transactionId,
            )
        }
    }
}
