package io.github.alstn113.transfer.application.port.out

import io.github.alstn113.transfer.application.port.out.dto.*

interface WalletPort {

    fun withdraw(command: WithdrawCommandDto): WithdrawResultDto
    fun deposit(command: DepositCommandDto): DepositResultDto
    fun getTransaction(walletId: Long, transactionId: String): GetTransactionResultDto
}