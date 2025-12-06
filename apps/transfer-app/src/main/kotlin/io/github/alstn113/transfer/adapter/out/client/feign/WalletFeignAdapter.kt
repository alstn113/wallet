package io.github.alstn113.transfer.adapter.out.client.feign

import io.github.alstn113.transfer.adapter.out.client.feign.dto.DepositRequestDto
import io.github.alstn113.transfer.adapter.out.client.feign.dto.WithdrawRequestDto
import io.github.alstn113.transfer.application.port.out.WalletPort
import io.github.alstn113.transfer.application.port.out.dto.*
import org.springframework.stereotype.Component

@Component
class WalletFeignAdapter(
    private val client: WalletFeignClient,
) : WalletPort {

    override fun withdraw(command: WithdrawCommandDto): WithdrawResultDto {
        return client.withdraw(
            walletId = command.walletId,
            request = WithdrawRequestDto.from(command)
        )
    }

    override fun deposit(command: DepositCommandDto): DepositResultDto {
        return client.deposit(
            walletId = command.walletId,
            request = DepositRequestDto.from(command)
        )
    }

    override fun getTransaction(walletId: Long, transactionId: String): GetTransactionResultDto {
        return client.getTransaction(
            walletId = walletId,
            transactionId = transactionId
        )
    }
}