package io.github.alstn113.transfer.application.service

import io.github.alstn113.transfer.application.port.`in`.dto.TransferResultDto
import io.github.alstn113.transfer.domain.Transfer
import org.springframework.stereotype.Component

@Component
class DepositStep {

    fun process(transfer: Transfer): TransferResultDto {
        // Deposit logic here
    }
}