package io.github.alstn113.wallet.adapter.`in`.web

import io.github.alstn113.wallet.adapter.`in`.web.dto.DepositRequestDto
import io.github.alstn113.wallet.adapter.`in`.web.dto.WithdrawRequestDto
import io.github.alstn113.wallet.application.port.`in`.DepositUseCase
import io.github.alstn113.wallet.application.port.`in`.GetTransactionUseCase
import io.github.alstn113.wallet.application.port.`in`.WithdrawUseCase
import io.github.alstn113.wallet.application.port.`in`.dto.DepositResultDto
import io.github.alstn113.wallet.application.port.`in`.dto.TransactionResultDto
import io.github.alstn113.wallet.application.port.`in`.dto.WithdrawResultDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class WalletController(
    private val withdrawUseCase: WithdrawUseCase,
    private val depositUseCase: DepositUseCase,
    private val getTransactionUseCase: GetTransactionUseCase,
) {

    @PostMapping("/v1/wallets/{walletId}/withdraw")
    fun withdraw(
        @PathVariable walletId: Long,
        @RequestBody request: WithdrawRequestDto,
    ): ResponseEntity<WithdrawResultDto> {
        val command = request.toCommand(walletId)
        val result = withdrawUseCase(command)

        return ResponseEntity.ok(result)
    }

    @PostMapping("/v1/wallets/{walletId}/deposit")
    fun deposit(
        @PathVariable walletId: Long,
        @RequestBody request: DepositRequestDto,
    ): ResponseEntity<DepositResultDto> {
        val command = request.toCommand(walletId)
        val result = depositUseCase(command)

        return ResponseEntity.ok(result)
    }

    @GetMapping("/v1/wallets/{walletId}/transactions/{transactionId}")
    fun getTransaction(
        @PathVariable walletId: Long,
        @PathVariable transactionId: String,
    ): ResponseEntity<TransactionResultDto> {
        val result = getTransactionUseCase(walletId, transactionId)

        return ResponseEntity.ok(result)
    }
}