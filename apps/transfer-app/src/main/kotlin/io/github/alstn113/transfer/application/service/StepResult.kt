package io.github.alstn113.transfer.application.service

import io.github.alstn113.transfer.application.port.`in`.dto.TransferResultDto
import io.github.alstn113.transfer.domain.Transfer

sealed interface StepResult {
    data class Next(val transfer: Transfer) : StepResult
    data class Stop(val result: TransferResultDto) : StepResult
}