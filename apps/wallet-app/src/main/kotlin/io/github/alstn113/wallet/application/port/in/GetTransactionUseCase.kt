package io.github.alstn113.wallet.application.port.`in`

import io.github.alstn113.wallet.application.port.`in`.dto.TransactionResultDto

fun interface GetTransactionUseCase {

    operator fun invoke(walletId: Long, transactionId: String): TransactionResultDto
}