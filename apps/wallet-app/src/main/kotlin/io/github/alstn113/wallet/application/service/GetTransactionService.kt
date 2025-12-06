package io.github.alstn113.wallet.application.service

import io.github.alstn113.wallet.application.port.`in`.GetTransactionUseCase
import io.github.alstn113.wallet.application.port.`in`.dto.TransactionResultDto
import io.github.alstn113.wallet.application.port.out.WalletTransactionRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class GetTransactionService(
    private val walletTransactionRepository: WalletTransactionRepository,
) : GetTransactionUseCase {

    @Transactional(readOnly = true)
    override fun invoke(
        walletId: Long,
        transactionId: String,
    ): TransactionResultDto {
        val transaction = walletTransactionRepository
            .findByWalletIdAndTransactionId(walletId, transactionId)
            ?: throw IllegalArgumentException("Transaction not found")

        return TransactionResultDto.from(transaction)
    }
}