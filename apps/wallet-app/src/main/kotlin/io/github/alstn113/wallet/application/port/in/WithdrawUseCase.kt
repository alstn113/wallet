package io.github.alstn113.wallet.application.port.`in`

import io.github.alstn113.wallet.application.port.`in`.dto.WithdrawCommandDto
import io.github.alstn113.wallet.application.port.`in`.dto.WithdrawResultDto

fun interface WithdrawUseCase {

    operator fun invoke(command: WithdrawCommandDto): WithdrawResultDto
}