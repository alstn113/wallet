package io.github.alstn113.transfer.application.port.`in`

import io.github.alstn113.transfer.application.port.`in`.dto.TransferCommandDto
import io.github.alstn113.transfer.application.port.`in`.dto.TransferResultDto

fun interface TransferUseCase {

    operator fun invoke(command: TransferCommandDto): TransferResultDto
}