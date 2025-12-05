package io.github.alstn113.wallet.application.service

import io.github.alstn113.wallet.application.port.`in`.WithdrawUseCase
import io.github.alstn113.wallet.application.port.`in`.dto.WithdrawCommandDto
import io.github.alstn113.wallet.application.port.`in`.dto.WithdrawResultDto
import io.github.alstn113.wallet.application.port.out.WalletRepository
import io.github.alstn113.wallet.domain.TransactionType
import io.github.alstn113.wallet.domain.WalletTransaction
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class WithdrawService(
    private val walletRepository: WalletRepository,
) : WithdrawUseCase {

    @Transactional
    override fun invoke(command: WithdrawCommandDto): WithdrawResultDto {
        val wallet = walletRepository.findByIdWithLock(command.walletId)
            ?: throw IllegalArgumentException("Wallet not found: ${command.walletId}")

        val withdrawnWallet = wallet.withdraw(command.amount)
        val withdrawTransaction = createWithdrawTransaction(command, withdrawnWallet.balance)
        val savedTransaction = walletRepository.saveWithTransaction(withdrawnWallet, withdrawTransaction)

        return WithdrawResultDto(
            id = savedTransaction.id,
            walletId = savedTransaction.walletId,
            amount = savedTransaction.amount,
            balance = savedTransaction.balanceSnapshot,
            transactionId = savedTransaction.transactionId,
        )
    }

    private fun createWithdrawTransaction(
        command: WithdrawCommandDto,
        balanceAfterWithdraw: BigDecimal,
    ): WalletTransaction {
        return WalletTransaction.create(
            walletId = command.walletId,
            amount = command.amount,
            type = TransactionType.WITHDRAW,
            balanceSnapshot = balanceAfterWithdraw,
            transactionId = command.transactionId
        )
    }
}