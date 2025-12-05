package io.github.alstn113.wallet.application.service

import io.github.alstn113.wallet.application.port.`in`.GetTransactionUseCase
import io.github.alstn113.wallet.application.port.`in`.dto.TransactionResultDto
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class GetTransactionService : GetTransactionUseCase {

    @Transactional(readOnly = true)
    override fun invoke(
        walletId: Long,
        transactionId: String,
    ): TransactionResultDto {
        TODO("Not yet implemented")
    }
}