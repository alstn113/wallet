package io.github.alstn113.transfer.adapter.out.feign

import io.github.alstn113.transfer.adapter.out.feign.dto.DepositRequestDto
import io.github.alstn113.transfer.adapter.out.feign.dto.WithdrawRequestDto
import io.github.alstn113.transfer.application.port.out.dto.DepositResultDto
import io.github.alstn113.transfer.application.port.out.dto.GetTransactionResultDto
import io.github.alstn113.transfer.application.port.out.dto.WithdrawResultDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(
    name = "walletClient",
    url = "http://localhost:8080" // TODO: 설정으로 대체
)
interface WalletFeignClient {

    @PostMapping("/v1/wallets/{walletId}/withdraw")
    fun withdraw(
        @PathVariable walletId: Long,
        @RequestBody request: WithdrawRequestDto,
    ): WithdrawResultDto

    @PostMapping("/v1/wallets/{walletId}/deposit")
    fun deposit(
        @PathVariable walletId: Long,
        @RequestBody request: DepositRequestDto,
    ): DepositResultDto

    @GetMapping("/v1/wallets/{walletId}/transactions/{transactionId}")
    fun getTransaction(
        @PathVariable walletId: Long,
        @PathVariable transactionId: String,
    ): GetTransactionResultDto
}