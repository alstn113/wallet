package io.github.alstn113.wallet.application.port.`in`

import io.github.alstn113.wallet.application.port.`in`.dto.DepositCommandDto
import io.github.alstn113.wallet.application.port.`in`.dto.DepositResultDto

fun interface DepositUseCase {

    operator fun invoke(command: DepositCommandDto): DepositResultDto
}
