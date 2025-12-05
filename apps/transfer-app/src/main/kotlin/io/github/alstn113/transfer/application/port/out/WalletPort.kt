package io.github.alstn113.transfer.application.port.out

import io.github.alstn113.transfer.application.port.out.dto.DepositCommandDto
import io.github.alstn113.transfer.application.port.out.dto.DepositResultDto
import io.github.alstn113.transfer.application.port.out.dto.GetTransactionResultDto
import io.github.alstn113.transfer.application.port.out.dto.WithdrawCommandDto
import io.github.alstn113.transfer.application.port.out.dto.WithdrawResultDto

interface WalletPort {

    fun withdraw(command: WithdrawCommandDto): WithdrawResultDto
    fun deposit(command: DepositCommandDto): DepositResultDto
    fun getTransaction(walletId: Long, transactionId: String): GetTransactionResultDto
}