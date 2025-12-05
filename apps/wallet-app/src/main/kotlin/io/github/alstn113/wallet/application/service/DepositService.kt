package io.github.alstn113.wallet.application.service

import io.github.alstn113.wallet.application.port.`in`.DepositUseCase
import io.github.alstn113.wallet.application.port.`in`.dto.DepositCommandDto
import io.github.alstn113.wallet.application.port.`in`.dto.DepositResultDto
import io.github.alstn113.wallet.application.port.out.WalletRepository
import io.github.alstn113.wallet.domain.TransactionType
import io.github.alstn113.wallet.domain.WalletTransaction
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class DepositService(
    private val walletRepository: WalletRepository,
) : DepositUseCase {

    @Transactional
    override fun invoke(command: DepositCommandDto): DepositResultDto {
        val wallet = walletRepository.findByIdWithLock(command.walletId)
            ?: throw IllegalArgumentException("Wallet not found: ${command.walletId}")

        val depositedWallet = wallet.deposit(command.amount)
        val depositTransaction = createDepositTransaction(command, depositedWallet.balance)
        val savedTransaction = walletRepository.saveWithTransaction(depositedWallet, depositTransaction)

        return DepositResultDto(
            id = savedTransaction.id,
            walletId = savedTransaction.walletId,
            amount = savedTransaction.amount,
            balance = savedTransaction.balanceSnapshot,
            transactionId = savedTransaction.transactionId,
        )
    }

    private fun createDepositTransaction(
        command: DepositCommandDto,
        balanceAfterDeposit: BigDecimal,
    ): WalletTransaction {
        return WalletTransaction.create(
            walletId = command.walletId,
            amount = command.amount,
            type = TransactionType.DEPOSIT,
            balanceSnapshot = balanceAfterDeposit,
            transactionId = command.transactionId
        )
    }
}